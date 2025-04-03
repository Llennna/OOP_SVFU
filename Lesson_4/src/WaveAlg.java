import java.util.ArrayList;
import java.util.Collections;

public class WaveAlg {
        public static void main(String[] args) {
        Map map = new Map();
        int [][] field=map.getField();
        WaveAlg waveAlg = new WaveAlg();
        waveAlg.findPath(field,0,0,3,3);
        waveAlg.printArr(field);

    }
    int[] dx, dy;
    public void printArr(int[][] field) {
        for (int i = 0; i < field.length ; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]+"\t");
            }
            System.out.println();
        }
    }
    public ArrayList<Point> findPath(int[][] field, int x, int y, int tx, int ty){
        if (field[ty][tx]!=-1){
            System.out.println("Юнит отпрален на стенку");
            return new ArrayList<Point>();
        }
        ArrayList<Point> oldWave = new ArrayList<>();
        ArrayList<Point> wave = new ArrayList<>();
        oldWave.add(new Point(x,y));
        int count=0;
        field[y][x]=count;
        boolean isExist=false;
        out:
        while (!oldWave.isEmpty()){
            count++;
            for( Point p: oldWave){
                //int []dx = new int []{0,0,-1,1};
                //int []dy = new int []{-1,1,0,0};
                for (int i = 0; i < dx.length; i++) {
                    int cx =p.x()+dx[i];
                    int cy =p.y()+dy[i];
                    if( cx>=0 && cy>=0 && cy< field.length && cx< field[i].length){
                        if (field[cy][cx]==Map.EMPTY) {
                            field[cy][cx] = count;
                            if(cx==tx && cy==ty){
                                isExist=true;
                                break out;
                            }
                            wave.add(new Point(cx, cy));
                        }
                    }
                }
            }
            oldWave = new ArrayList<>(wave);
            wave.clear();
        }
        /// ///////////
        wave.clear();
        if(!isExist){
            return wave;
        }
        wave.add(new Point(tx,ty));
        while(field[ty][tx]!=0){
            //int []dx = new int []{0,0,-1,1};
            //int []dy = new int []{-1,1,0,0};
            for (int i = 0; i < dx.length; i++) {
                int cx =tx+dx[i];
                int cy =ty+dy[i];
                if( cx>=0 && cy>=0 && cy< field.length && cx< field[i].length) {
                    if (field[ty][tx] - 1 == field[cy][cx]) {
                        tx = cx;
                        ty = cy;
                        wave.add(new Point(cx, cy));
                        break;
                    }
                }
            }
        }
        Collections.reverse(wave);
        for(Point p: wave){
            System.out.println(p.x()+","+ p.y());
            field[p.y()][p.x()]=0;
        }
        return wave;
    }

    public ArrayList<Point> findRookPath(int[][] field, int x, int y, int tx, int ty) {
        dy = new int []{-1,1,0,0};
        dx = new int[] {0,0,-1,1};
            return findPath(field, x, y, tx, ty);
    }

    public ArrayList<Point> findQueenPath(int[][] field, int x, int y, int tx, int ty) {
        dy = new int []{-1,1,0,0,1, 1, -1, -1};
        dx = new int[] {0,0,-1,1,1, -1, 1, -1};
            return findPath(field, x, y, tx, ty);
    }
    public ArrayList<Point> findKnightPath(int[][] field, int x, int y, int tx, int ty) {
        dy = new int []{2,1,-2,1,2,1,-2,-1};
        dx = new int[] {1,2,1,2,-1,-2,-1,-2};
        return findPath(field, x, y, tx, ty);
    }

    public ArrayList<Point> findBishopPath(int[][] field, int x, int y, int tx, int ty) {
        dy = new int []{1, 1, -1, -1};
        dx = new int[] {1, -1, 1, -1};
        return findPath(field, x, y, tx, ty);
    }
}

