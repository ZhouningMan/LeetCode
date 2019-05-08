package design;

import java.util.*;

public class SnakeGame {
    private final int width;
    private final int height;
    private final Queue<Position> food = new ArrayDeque<>();
    private final Deque<Position> snake = new ArrayDeque<>();
    private final Set<Position> snakePos = new HashSet<>(); //for fast lookup
    private int foodEaten = 0;

    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        for (int[] f : food) {
            this.food.offer(new Position(f[0], f[1]));
        }
        Position start = new Position(0, 0);
        this.snake.offer(start);
        this.snakePos.add(start);
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        Position next = nextPos(direction, snake.peek());
        //hit border
        if (next.r < 0 || next.c < 0 || next.r >= height || next.c >= width) return -1;
        //hit snake itself
        if (!food.isEmpty() && food.peek().equals(next)) {
            food.poll();
            foodEaten++;
        } else {
            Position tail = snake.pollLast();
            snakePos.remove(tail);
        }
        //You want to check snake after removing the tail!
        if (snakePos.contains(next)) return -1;
        snake.push(next);
        snakePos.add(next);
        return foodEaten;
    }

    private Position nextPos(String direction, Position pos) {
        Position next = new Position(pos.r, pos.c);
        switch (direction) {
            case "U": next.r = pos.r - 1; break;
            case "D": next.r = pos.r + 1; break;
            case "L": next.c = pos.c - 1; break;
            case "R": next.c = pos.c + 1; break;
        }
        return next;
    }

    private static class Position {
        int r;
        int c;

        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) return true;
            if (!(other instanceof Position)) return false;
            Position o = (Position) other;
            return o.r == this.r && o.c == this.c;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    public static void test() {
        SnakeGame snakeGame = new SnakeGame(3, 3, new int[][]{{2,0},{0,0},{0,2},{2,2}});
        //["D"],["D"],["R"],["U"],["U"],["L"],["D"],["R"],["R"],["U"],["L"],["D"]
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("D"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("R"));
        System.out.println(snakeGame.move("U"));
        System.out.println(snakeGame.move("L"));
        System.out.println(snakeGame.move("D"));


    }
}
