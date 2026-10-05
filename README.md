# 🧱 Brick Breaker Game

A simple **Brick Breaker game built with Java Swing**. Move the paddle, bounce the ball, break all the bricks, and try to get the highest score.

## 🎮 Features

- Move the paddle using the arrow keys
- Bounce the ball and break bricks
- **21 bricks** to clear
- **5 points** for each brick
- Ball speed increases as bricks are cleared
- Sound effects during gameplay
- Win and lose conditions
- Restart the game after winning or losing
- Maximized game window

## 🛠️ Technologies Used

- Java
- Java Swing
- Java AWT
- Object-Oriented Programming
- Event Handling
- Collision Detection

## 🎮 Controls

| Key | Action |
|---|---|
| ⬅️ Left Arrow | Move paddle left / Start game |
| ➡️ Right Arrow | Move paddle right / Start game |
| ↵ Enter | Restart after winning or losing |

## 🕹️ Gameplay

- Use the paddle to bounce the ball into the bricks.
- Each brick cleared gives **5 points**.
- Clear all **21 bricks** to win.
- The ball becomes faster as you clear more bricks.
- If the ball falls below the paddle, the game ends.
- Press **Enter** to restart after winning or losing.

### 🏆 Scoring

**1 Brick = 5 Points**

**21 Bricks = 105 Points Maximum**

## 📁 Project Structure

```text
Brick-Breaker/
│
├── Main.java
├── BrickBreaker.java
├── BrickMap.java
├── sound/
│   └── ...
└── README.md
```

- **Main.java** – Starts the game.
- **BrickBreaker.java** – Handles the main game, paddle, ball, controls, scoring, and gameplay.
- **BrickMap.java** – Creates and manages the bricks.
- **sound/** – Contains the sound effects used in the game.

## ⚙️ Requirements

Make sure **JDK** is installed on your system.

Check your Java installation:

```bash
java --version
javac --version
```

## 🚀 How to Run

### 1. Clone the repository

```bash
git clone <YOUR-REPOSITORY-URL>
```

### 2. Open the project folder

```bash
cd Brick-Breaker
```

### 3. Compile the Java files

```bash
javac Main.java BrickBreaker.java BrickMap.java
```

### 4. Run the game

```bash
java Main
```

The game will open in a maximized window.

## 🎯 Game Objective

Break all **21 bricks** using the ball while keeping it in play with the paddle.

**Break the bricks. Score points. Don't let the ball fall. 🧱🏓**

## 🔮 Future Improvements

- Multiple levels
- Different brick patterns
- Power-ups
- Lives system
- High-score saving
- More sound effects and background music

## 👩‍💻 Author

**Bhoomi Tiple**

Made with Java Swing as a simple game development project.
