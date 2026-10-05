import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrickBreaker extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;

    private int paddleX = 310;

    private int ballX = 120;
    private int ballY = 350;
    private int ballDirX = -2;
    private int ballDirY = -3;

    private int speedLevel = 1;
    private final int MAX_SPEED = 6;

    private Timer timer;
    private int delay = 8;

    private BrickMap map;
    private boolean winSoundPlayed = false;
    private boolean gameOverSoundPlayed = false;

    private void playTone(float frequency, int durationMillis) {
        try {
            float sampleRate = 44100f;
            int totalSamples = (int) (sampleRate * durationMillis / 1000f);
            byte[] buffer = new byte[totalSamples * 2];

            for (int i = 0; i < totalSamples; i++) {
                double time = i / sampleRate;
                double wave = Math.sin(2 * Math.PI * frequency * time);
                short sample = (short) (wave * 32767 * 0.18);

                buffer[i * 2] = (byte) (sample & 0xFF);
                buffer[i * 2 + 1] = (byte) ((sample >> 8) & 0xFF);
            }

            AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);
            SourceDataLine line = AudioSystem.getSourceDataLine(format);
            line.open(format);
            line.start();
            line.write(buffer, 0, buffer.length);
            line.drain();
            line.stop();
            line.close();
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public BrickBreaker() {

        map = new BrickMap(3, 7);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // background
        g.setColor(Color.black);
        g.fillRect(0, 0, panelWidth, panelHeight);

        // draw bricks
        map.draw((Graphics2D) g);

        // borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, panelHeight);
        g.fillRect(0, 0, panelWidth, 3);
        g.fillRect(panelWidth - 3, 0, 3, panelHeight);

        // paddle
        int paddleY = panelHeight - 50;

        g.setColor(Color.green);
        g.fillRect(paddleX, paddleY, 100, 8);

        // ball
        g.setColor(Color.red);
        g.fillOval(ballX, ballY, 20, 20);

        // score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score: " + score, panelWidth - 180, 30);

        // WIN
        if (totalBricks == 0) {

            if (!winSoundPlayed) {
                playTone(220f, 250);
                winSoundPlayed = true;
            }
            play = false;
            ballDirX = 0;
            ballDirY = 0;

            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU WON!", panelWidth / 2 - 90, panelHeight / 2);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press ENTER to Restart",
                    panelWidth / 2 - 120,
                    panelHeight / 2 + 40);
        }

        // GAME OVER
        if (ballY > panelHeight - 30) {

            if (!gameOverSoundPlayed) {
                playTone(160f, 300);
                gameOverSoundPlayed = true;
            }
            play = false;
            ballDirX = 0;
            ballDirY = 0;

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("GAME OVER", panelWidth / 2 - 100, panelHeight / 2);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press ENTER to Restart",
                    panelWidth / 2 - 120,
                    panelHeight / 2 + 40);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();

        if (play) {

            int paddleY = getHeight() - 50;

            // paddle collision
            if (new Rectangle(ballX, ballY, 20, 20)
                    .intersects(new Rectangle(paddleX, paddleY, 100, 8))) {

                playTone(520f, 60);
                ballDirY = -ballDirY;
            }

            // brick collision
            A:
            for (int i = 0; i < map.map.length; i++) {

                for (int j = 0; j < map.map[0].length; j++) {

                    if (map.map[i][j] > 0) {

                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickW = map.brickWidth;
                        int brickH = map.brickHeight;

                        Rectangle brickRect =
                                new Rectangle(brickX, brickY, brickW, brickH);

                        Rectangle ballRect =
                                new Rectangle(ballX, ballY, 20, 20);

                        if (ballRect.intersects(brickRect)) {

                            map.setBrickValue(0, i, j);

                            totalBricks--;
                            score += 5;
                            playTone(760f, 70);

                            if (speedLevel < MAX_SPEED) {

                                speedLevel++;

                                ballDirX =
                                        (ballDirX > 0) ? speedLevel : -speedLevel;

                                ballDirY =
                                        (ballDirY > 0) ? speedLevel : -speedLevel;
                            }

                            if (ballX + 19 <= brickRect.x ||
                                    ballX + 1 >= brickRect.x + brickRect.width)

                                ballDirX = -ballDirX;
                            else
                                ballDirY = -ballDirY;

                            break A;
                        }
                    }
                }
            }

            ballX += ballDirX;
            ballY += ballDirY;

            // wall collision
            if (ballX < 0 || ballX > getWidth() - 20)
                ballDirX = -ballDirX;

            if (ballY < 0)
                ballDirY = -ballDirY;
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            play = true;

            if (paddleX >= getWidth() - 120)
                paddleX = getWidth() - 120;
            else
                paddleX += 20;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            play = true;

            if (paddleX <= 10)
                paddleX = 10;
            else
                paddleX -= 20;
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!play) {

                playTone(420f, 120);
                play = true;

                ballX = 120;
                ballY = 350;

                ballDirX = -1;
                ballDirY = -2;

                paddleX = getWidth() / 2 - 50;

                score = 0;
                totalBricks = 21;
                winSoundPlayed = false;
                gameOverSoundPlayed = false;

                map = new BrickMap(3, 7);

                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}