import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogansExpWanderer {

    static Tile[][] testMaze = new Tile[][]{
            {Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER},
            {Tile.WATER, Tile.STARTING_LOCATION, Tile.LAND, Tile.WATER, Tile.WATER, Tile.WATER},
            {Tile.WATER, Tile.WATER, Tile.LAND, Tile.WATER, Tile.WATER, Tile.WATER},
            {Tile.WATER, Tile.WATER, Tile.LAND, Tile.WATER, Tile.WATER, Tile.WATER},
            {Tile.WATER, Tile.WATER, Tile.LAND, Tile.LAND, Tile.ENDING_LOCATION, Tile.WATER},
            {Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER}};

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

    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST,
        STUCK,
        START
    }

    List<int[]> visited = new ArrayList<>();

    public Direction move(int[] current, Direction direction) {
        int iteration = 0;
        int[] workingWith = new int[2];
        workingWith[0] = current[0];
        workingWith[1] = current[1];
        //check where you went from previous move;
        if (testMaze[workingWith[1]][workingWith[0]].getSymbol() == 'E') {
            return Direction.STUCK;
        }
        if (direction == Direction.START) ;
        else if (direction == Direction.NORTH) {
            workingWith[1]--;
        } else if (direction == Direction.EAST) {
            workingWith[0]++;
        } else if (direction == Direction.SOUTH) {
            workingWith[1]++;
        } else if (direction == Direction.WEST) {
            workingWith[0]--;
        } else {
            System.out.println("You somehow got stuck");
            System.exit(0);
        }
        //check where to go
        visited.add(workingWith);
        if (getNorth(workingWith)) {
            move(workingWith, Direction.NORTH);
        } else if (getEast(workingWith)) {
            move(workingWith, Direction.EAST);
        } else if (getSouth(workingWith)) {
            move(workingWith, Direction.SOUTH);
        } else if (getWest(workingWith)) {
            move(workingWith, Direction.WEST);
        }
        return Direction.STUCK;
    }

    public boolean getNorth(int[] current) {
        int[] copy = new int[2];
        copy[0] = current[0];
        copy[1] = current[1];
        copy[1]--;
        if (visited.contains(copy)) {
            return false;
        }
        if (testMaze[copy[1]][copy[0]].isPassable()) {
            System.out.println("North");
            return true;
        }
        return false;
    }

    public boolean getEast(int[] current) {
        int[] copy = new int[2];
        copy[0] = current[0];
        copy[1] = current[1];
        copy[0]++;
        if (visited.contains(copy)) {
            return false;
        }
        if (testMaze[copy[1]][copy[0]].isPassable()) {
            System.out.println("East");
            return true;
        }
        return false;
    }

    public boolean getSouth(int[] current) {
        int[] copy = new int[2];
        copy[0] = current[0];
        copy[1] = current[1];
        copy[1]++;
        if (visited.contains(copy)) {
            return false;
        }
        if (testMaze[copy[1]][copy[0]].isPassable()) {
            System.out.println("South");
            return true;
        }
        return false;
    }

    public boolean getWest(int[] current) {
        int[] copy = new int[2];
        copy[0] = current[0];
        copy[1] = current[1];
        copy[0]--;
        if (visited.contains(copy)) {
            return false;
        }
        if (testMaze[copy[1]][copy[0]].isPassable()) {
            System.out.println("West");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LogansExpWanderer robot = new LogansExpWanderer();
        int[] current = new int[]{1, 1};
        robot.move(current, Direction.START);
    }

    @Override
    public boolean contains(ArrayList<int[]> store, int[] position){
        for (int[] check : store){
            
        }
    }
}
