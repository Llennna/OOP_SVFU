public class Map {
    public static final int WALL=-2;
    public static final int EMPTY =-1;
    public static final int UNIT =-3;
    private int[][] field = new int[][]{
            {-1,-1,-2,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-2,-1,-1,-1,-1,-2,-2,-1},
            {-1,-1,-2,-1,-1,-1,-2,-2,-2,-1},
            {-1,-1,-2,-1,-1,-1,-1,-2,-2,-1},
            {-1,-1,-1,-1,-2,-2,-1,-1,-2,-1},
            {-1,-1,-1,-1,-2,-1,-1,-1,-2,-1},
            {-1,-1,-1,-1,-2,-1,-1,-1,-2,-1},
            {-1,-1,-1,-1,-2,-1,-1,-1,-2,-1},

    };

    public int getCol() {
        return  field[0].length;
    }

    public  int getRow() {
        return field.length;
    }
    public  int getValue(int x, int y){
        return  field[x][y];
    }

    public boolean IsWall(int x, int y) {
        return  field[y][x]==WALL;
    }
    public int[][] getField() {
        int [][] copy = new int[field.length][];
        for (int i = 0; i < field.length; i++) {
            int[] row = new int [field[i].length];
            for (int j = 0; j < field[i].length; j++) {
                row[j]=field[i][j];
            }
            copy[i]=row;
        }
        return copy;
    }
    public void putUnit(int x, int y) {
        field[y][x] = UNIT;
    }
    public void remove(int x, int y) {
        field[y][x] = EMPTY;
    }
}
