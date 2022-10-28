import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogansExpWanderer {

    static Tile[][] testMaze = new Tile[][]{
            {Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER},
            {Tile.WATER, Tile.STARTING_LOCATION, Tile.LAND, Tile.LAND, Tile.WATER, Tile.WATER},
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
    List<String> instructions = new ArrayList<>();

    public Direction move(int[] current, Direction direction) {
        int[] workingWith = new int[2];
        workingWith[0] = current[0];
        workingWith[1] = current[1];
        if (direction == Direction.START);

        if (testMaze[workingWith[1]][workingWith[0]].getSymbol() == 'E') {
            System.out.println("You Made it");
            System.exit(0);
        }
        //check where to go
        visited.add(workingWith);
        if (getNorth(workingWith)) {
            workingWith[1]--;
            move(workingWith, Direction.NORTH);
        } else if (getEast(workingWith)) {
            workingWith[0]++;
            move(workingWith, Direction.EAST);
        } else if (getSouth(workingWith)) {
            workingWith[1]++;
            move(workingWith, Direction.SOUTH);
        } else if (getWest(workingWith)) {
            workingWith[0]--;
            move(workingWith, Direction.WEST);
        }
        System.out.println("Going Back");
        move(current, Direction.START);
        return Direction.STUCK;
    }

    public boolean getNorth(int[] current) {
        int[] copy = new int[2];
        copy[0] = current[0];
        copy[1] = current[1];
        copy[1]--;
        if (contains(visited, copy)) {
            return false;
        }
        else if (testMaze[copy[1]][copy[0]].isPassable()) {
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
        if (contains(visited, copy)) {
            return false;
        }
        else if (testMaze[copy[1]][copy[0]].isPassable()) {
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
        if (contains(visited, copy)) {
            return false;
        }
        else if (testMaze[copy[1]][copy[0]].isPassable()) {
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
        if (contains(visited, copy)) {
            return false;
        }
        else if (testMaze[copy[1]][copy[0]].isPassable()) {
            System.out.println("West");
            return true;
        }
        return false;
    }
    public static boolean contains(List<int[]> visited, int[] position) {
        for (int[] check : visited) {
            String pos = Arrays.toString(position);
            String che = Arrays.toString(check);
            if (pos.equals(che)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LogansExpWanderer robot = new LogansExpWanderer();
        System.out.println(testMaze[4][4].getSymbol());
        int[] current = new int[]{1, 1};
        robot.move(current, Direction.START);
    }


}
