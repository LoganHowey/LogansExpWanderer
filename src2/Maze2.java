import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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

        Stack<Direction> order = new Stack<>();
        ArrayList<Tile> visited = new ArrayList<>();

        @Override
        public Direction move(Sensor sensor) {

            Tile north = sensor.getNorth();
            Tile south = sensor.getSouth();
            Tile east = sensor.getEast();
            Tile west = sensor.getWest();

            if (north.getSymbol() == 'E') {
                return Direction.NORTH;
            } else if (south.getSymbol() == 'E') {
                return Direction.SOUTH;
            } else if (east.getSymbol() == 'E') {
                return Direction.EAST;
            } else if (west.getSymbol() == 'E') {
                return Direction.WEST;
            } else if (north.isPassable() && !visited.contains(north)) {
                visited.add(north);
                order.add(Direction.NORTH);
                return Direction.NORTH;
            } else if (south.isPassable() && !visited.contains(south)) {
                visited.add(south);
                order.add(Direction.SOUTH);
                return Direction.SOUTH;
            } else if (east.isPassable() && !visited.contains(east)) {
                visited.add(east);
                order.add(Direction.EAST);
                return Direction.EAST;
            } else if (west.isPassable() && !visited.contains(west)) {
                visited.add(west);
                order.add(Direction.WEST);
                return Direction.WEST;
            }
            Direction previous = order.pop();
            Direction backtrack = null;
            if (previous == Direction.NORTH){
                backtrack = Direction.SOUTH;
            }else if (previous == Direction.SOUTH){
                backtrack = Direction.NORTH;
            }else if (previous == Direction.EAST){
                backtrack = Direction.WEST;
            }else if (previous == Direction.WEST){
                backtrack = Direction.EAST;
            }
            return backtrack;
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