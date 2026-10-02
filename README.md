# Brick Breaker

A simple Brick Breaker game built with Java Swing. Move the paddle to keep the ball in play, break all the bricks, and try to get the highest score.

## Requirements

- Java Development Kit (JDK) installed, including `javac`

## Run the game

Open a terminal in the project folder and compile the source files:

```bash
javac Main.java BrickBreaker.java BrickMap.java
```

Then start the game:

```bash
java Main
```

The game opens in a maximized window.

## Controls

| Key | Action |
| --- | --- |
| Left Arrow | Move the paddle left and start playing |
| Right Arrow | Move the paddle right and start playing |
| Enter | Restart after winning or losing |

## Gameplay

- Use the paddle to bounce the ball into the bricks.
- Each brick cleared adds 5 points.
- Clear all 21 bricks to win.
- If the ball falls below the paddle, the game ends.
- The ball speeds up as bricks are cleared.
