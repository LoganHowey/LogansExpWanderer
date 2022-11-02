import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze2 {
    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    public interface Sensor {
        Tile getNorth();

        Tile getEast();

        Tile getSouth();

        Tile getWest();
    }

    public interface MazeWanderer {
        Direction move(Sensor sensor);
    }

    public enum Tile {
        WATER('W', false),
        LAND('=', true),
        STARTING_LOCATION('S', true),
        ENDING_LOCATION('E', true);
        private char symbol;

        private boolean passable;

        Tile(char symbol, boolean passable) {
            this.symbol = symbol;
            this.passable = passable;
        }

        public static Tile bySymbol(char symbol) {
            for (Tile title : Tile.values())
                if (title.symbol == symbol)
                    return title;
            return null;
        }

        public char getSymbol() {
            return symbol;
        }

        public boolean isPassable() {
            return passable;
        }
    }

    public class Robot implements MazeWanderer {
        Sensor sensor = new Sensor() {
            @Override
            public Tile getNorth() {
                return null;
            }

            @Override
            public Tile getEast() {
                return null;
            }

            @Override
            public Tile getSouth() {
                return null;
            }

            @Override
            public Tile getWest() {
                return null;
            }
        };

        public boolean mazeSolver(Tile[][] maze, int rowStart, int colStart){
            int row = maze.length;
            int col = maze[0].length;
            int [][] path = new int [row][col];

            if (!solveMaze(maze, rowStart, colStart, path)){
                return false;
            }
            for(int i = 0; i < rowStart; i++){
                for (int j = 0; j < colStart; j++)
                    System.out.print(" " + path[i][j] + ' ');
                System.out.println();
            }
            return true;
        }
        public boolean solveMaze(Tile[][] maze, int x, int y, int[][] visited) {
            if (maze[x][y].getSymbol() == 'E') {
                return true;
            }

            if (move(sensor) == Direction.NORTH && visited[x][y] != 1) {
                visited[y][x] = 1;
                if (solveMaze(maze, x, --y, visited)){
                    return true;
                }
                visited[x][y] = 0;
                return false;
            } else if (move(sensor) == Direction.EAST && visited[x][y] != 1) {
                visited[x][y] = 1;
                if (solveMaze(maze, ++x, y, visited)){
                    return true;
                }
                visited[x][y] = 0;
                return false;
            } else if (move(sensor) == Direction.SOUTH && visited[x][y] != 1) {
                visited[x][y] = 1;
                if(solveMaze(maze, x, ++y, visited)){
                    return true;
                }
                visited[x][y] = 0;
                return false;
            } else if (move(sensor) == Direction.EAST && visited[x][y] != 1) {
                visited[x][y] = 1;
                if(solveMaze(maze, --x, y, visited)){
                    return true;
                }
                visited[x][y] = 0;
                return false;
            }
            return false;
        }

        @Override
        public Direction move(Sensor sensor) {
            if (sensor.getNorth().isPassable()) {
                return Direction.NORTH;
            } else if (sensor.getEast().isPassable()) {
                return Direction.EAST;
            } else if (sensor.getSouth().isPassable()) {
                return Direction.SOUTH;
            } else if (sensor.getWest().isPassable()) {
                return Direction.WEST;
            }
            return null;
        }
    }

    public void main(String[] args) {
        Robot robot = new Robot();
        Tile[][] maze = {{}};
        robot.mazeSolver(maze, 0, 0);
    }
}