# SnakeGame

A turn-based terminal Snake game in Java, built around a custom singly linked list. Move the snake, collect targets, and keep growing without hitting the walls or your own body.

## Run

Use a JDK with `javac` and `java` available. JDK 17 or newer is suitable; the source has been compiled and exercised with JDK 19.0.2. No external libraries or build tool are required.

```bash
git clone https://github.com/sudhanvad18/SnakeGame.git
cd SnakeGame
mkdir -p build
javac -d build *.java
java -cp build SnakeGame
```

The game prints a 20-by-25 character board. Enter **one lowercase command and press Enter** for each turn; it does not use real-time key capture.

## Controls

| Command | Action |
| --- | --- |
| `w`, `a`, `s`, `d` | Move north, west, south, or east. |
| `h` | Show help, then press Enter to continue. |
| `f` | Write `snakeGameSave.txt`; confirming `y` also ends the current game. |
| `r` | Restore an existing `snakeGameSave.txt` from the current directory. |
| `q` | Quit after confirming with `y`. |

`@` marks the snake’s head, `*` its body, and `+` a target inside the border. Eating a target increases the score by one and adds a body segment. A collision ends the game. The save file is a plain-text snapshot of the score, target, and snake coordinates; restoring requires that file to exist and contain valid saved data.

## Board preview

The board class includes a small deterministic example:

```bash
java -cp build SnakeBoard
```

Its actual console output is:

```text
 + - - - - - - - - - - - - - +
 |             +             |
 |                           |
 |     @                     |
 |     *                     |
 |     *                     |
 |     *                     |
 |     *                     |
 |                           |
 + - - - - - - - - - - - - - +
```

This preview uses a smaller 10-by-15 board; the main game uses the larger board described above.

## Code map

| File | Responsibility |
| --- | --- |
| `SnakeGame.java` | Game loop, commands, movement, collision checks, and save/restore. |
| `SnakeBoard.java` | Character-grid drawing and occupancy lookup. |
| `Snake.java` | Snake coordinate sequence and initial body. |
| `SinglyLinkedList.java`, `ListNode.java` | Custom linked-list storage. |
| `Coordinate.java` | Row/column values and coordinate equality. |
| `Prompt.java`, `FileUtils.java` | Terminal input and save-file access. |

## Author

[Sudhanva Deshpande](https://github.com/sudhanvad18)
