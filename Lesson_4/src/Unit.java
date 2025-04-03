import java.util.ArrayList;
public class Unit {
    private int x; // = 0
    private int y = 0;
    private int tx = 0;
    private int ty;
    private int life = 100;
    Map map = null;
    ArrayList<Point> path=new ArrayList<>();
    public void setMap(Map map){
        this.map= map;
        map.putUnit(x,y);
    }
    public int getLife() {
        return life;
    }
    public Unit(int x, int y) {
        this.x = x;
        this.y = y;
        tx = x;
        ty = y;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

    public int getTy() {
        return ty;
    }

    public void setTy(int ty) {
        this.ty = ty;
    }

    public int getTx() {
        return tx;
    }

    public void setTx(int tx) {
        this.tx = tx;
    }

    public   void  setTarget(int cx, int cy) {
        tx = cx;
        ty = cy;
        path= new WaveAlg().findPath(map.getField(),x,y,tx,ty);
    }
    public void move() {
        new WaveAlg().printArr(map.getField());
        if(!path.isEmpty()){
            Point p = path.getFirst();
            map.remove(x,y);
            x=p.x();
            y=p.y();
            map.putUnit(x,y);
            path.removeFirst();
        }
    }
}