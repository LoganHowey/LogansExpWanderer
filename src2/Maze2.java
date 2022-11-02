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

    public static class Robot implements MazeWanderer {

        Tile[][] maze = new Tile[100][100];

        public boolean solve(Direction direction){
            if (move()
        }
        @Override
        public Direction move(Sensor sensor) {
            if (sensor.getNorth().isPassable()) {
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

    public static void main(String[] args) {
        System.out.println(Tile.bySymbol('E'));
    }
}

/*
picks unused direction
if all tiles N,S,E,W are visited begin backtracking until an unvisited tile exists.
 */