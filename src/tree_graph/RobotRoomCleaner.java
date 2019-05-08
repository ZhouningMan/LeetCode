package tree_graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static tree_graph.RobotRoomCleaner.Direction.*;

public class RobotRoomCleaner {
    interface Robot {
        boolean move();
        void turnLeft();
        void turnRight();
        void clean();
    }

    static class Position {
        final int x;
        final int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Position nextPosition(Direction direction) {
            switch (direction) {
                case TOP:  return new Position(x, y+1);
                case LEFT: return new Position(x - 1, y);
                case BOTTOM: return new Position(x, y-1);
                case RIGHT: return new Position(x + 1, y);
            }
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    enum Direction {
        TOP,
        LEFT,
        BOTTOM,
        RIGHT;

        static Direction nextLeft(Direction direction) {
            switch (direction) {
                case TOP: return LEFT;
                case LEFT: return  BOTTOM;
                case BOTTOM: return RIGHT;
                case RIGHT: return TOP;
            }
            return null;
        }
    }

    public void cleanRoom(Robot robot) {
        dfs(robot, new Position(0, 0), Direction.TOP, new HashSet<>());
    }
    private void dfs(Robot robot, Position toClean, Direction direction, Set<Position> cleaned) {
        if(cleaned.contains(toClean)) return;
        robot.clean();
        cleaned.add(toClean);

        for(int i = 0; i < 4; i++) {
            if(robot.move()) {
                dfs(robot, toClean.nextPosition(direction), direction, cleaned);
                backout(robot);
            }
            direction = Direction.nextLeft(direction);
            robot.turnLeft();
        }
    }

    //return to previous position
    private void backout(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
    }
}
