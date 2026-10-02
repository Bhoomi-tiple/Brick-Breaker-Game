import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Brick Breaker");

        BrickBreaker game = new BrickBreaker();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(game);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setResizable(true);

        frame.setVisible(true);
    }
}