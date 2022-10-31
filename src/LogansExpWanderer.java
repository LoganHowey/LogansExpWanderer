import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogansExpWanderer {

    private final Tile[][] testMaze;

    public LogansExpWanderer(Tile[][] testMaze){
    this.testMaze = testMaze;
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
        int[] x = new int[]{current[0], current[1]};
        if (testMaze[current[1]][current[0]].getSymbol() == 'E') {
            instructions.add("You Made it");
            print(instructions);
            System.exit(0);
        }

        visited.add(x);
        if (getNorth(current)) {
            move(current, Direction.NORTH);
        } else if (getEast(current)) {
            move(current, Direction.EAST);//[2,1], East//[3,1], East
        } else if (getSouth(current)) {
            move(current, Direction.SOUTH);
        } else if (getWest(current)) {
            move(current, Direction.WEST);
        }
        if (direction == Direction.NORTH) {
            current[1]++;
            instructions.remove(instructions.size() - 1);
            move(current, Direction.START);
        } else if (direction == Direction.EAST) {
            current[0]--;
            instructions.remove(instructions.size() - 1);
            move(current, Direction.START);
        } else if (direction == Direction.SOUTH) {
            current[1]--;
            instructions.remove(instructions.size() - 1);
            move(current, Direction.START);
        } else if (direction == Direction.WEST) {
            current[0]++;
            instructions.remove(instructions.size() - 1);
            move(current, Direction.START);
        } else {
            return Direction.STUCK;
        }
        return Direction.STUCK;
    }

    public boolean getNorth(int[] current) {
        current[1]--;
        if (contains(visited, current)) {
            current[1]++;
            return false;
        } else if (testMaze[current[1]][current[0]].isPassable()) {
            instructions.add("North");
            return true;
        }
        current[1]++;
        return false;
    }

    public boolean getEast(int[] current) {
        current[0]++;
        if (contains(visited, current)) {
            current[0]--;
            return false;
        } else if (testMaze[current[1]][current[0]].isPassable()) {
            instructions.add("East");
            return true;
        }
        current[0]--;
        return false;
    }

    public boolean getSouth(int[] current) {
        current[1]++;
        if (contains(visited, current)) {
            current[1]--;
            return false;
        } else if (testMaze[current[1]][current[0]].isPassable()) {
            instructions.add("South");
            return true;
        }
        current[1]--;
        return false;
    }

    public boolean getWest(int[] current) {
        current[0]--;
        if (contains(visited, current)) {
            current[0]++;
            return false;
        } else if (testMaze[current[1]][current[0]].isPassable()) {
            instructions.add("West");
            return true;
        }
        current[0]++;
        return false;
    }

    public static Tile[][] makeMaze(char[][] input) {
        Tile[][] aMaze = new Tile[input.length][input.length];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j > input.length; j++) {
                if (input[i][j] == 'W') {
                    aMaze[i][j] = Tile.WATER;
                } else if (input[i][j] == '=') {
                    aMaze[i][j] = Tile.LAND;
                } else if (input[i][j] == 'S') {
                    aMaze[i][j] = Tile.STARTING_LOCATION;
                } else {
                    aMaze[i][j] = Tile.ENDING_LOCATION;
                }
            }
        }
    return aMaze;
    }

    public static boolean contains(List<int[]> visited, int[] position) {
        for (int[] check : visited) {
            String pos = Arrays.toString(position);
            String che = Arrays.toString(check);
            if (pos.equals(che)) {
                return true;
            }
        }
        return false;
    }

    public static void print(List<String> toPrint){
        for (String movement : toPrint){
            System.out.print(movement + ", ");
        }
    }

    public static void main(String[] args) {
        Tile[][] maze = new Tile[][]{
                {Tile.WATER, Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.WATER, Tile.LAND, Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER, Tile.LAND, Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER, Tile.LAND, Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER, Tile.STARTING_LOCATION, Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER},
                {Tile.WATER, Tile.WATER, Tile.LAND, Tile.WATER,Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER, Tile.LAND, Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,},
                {Tile.WATER, Tile.WATER, Tile.LAND, Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.WATER, Tile.LAND, Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,Tile.WATER, Tile.WATER,},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.ENDING_LOCATION, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.ENDING_LOCATION, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.ENDING_LOCATION, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.ENDING_LOCATION, Tile.LAND, Tile.LAND, Tile.LAND, Tile.LAND, Tile.WATER,Tile.LAND, Tile.LAND, Tile.LAND,Tile.LAND, Tile.LAND, Tile.LAND,Tile.WATER},
                {Tile.WATER, Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER,Tile.WATER}
        };
        LogansExpWanderer robot = new LogansExpWanderer(maze);
        int[] current = new int[]{11, 9};
        robot.move(current, Direction.START);
        print(robot.instructions);
    }


}
