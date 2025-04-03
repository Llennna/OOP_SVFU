public class Knight extends Unit{
    public Knight(int x, int y){
        super(x,y);
    }

    @Override
    public void setTarget(int cx, int cy) {
        setTx(cx);
        setTy(cy);
        path= new WaveAlg().findKnightPath(map.getField(), getX(),getY(), getTx(), getTy());
    }
}
