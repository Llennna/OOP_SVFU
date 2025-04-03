public class Bishop extends Unit  {
    public Bishop(int x , int y){
        super(x,y);
    }

    @Override
    public void setTarget(int cx, int cy) {
        //super.setTarget(cx, cy);
        setTx(cx);
        setTy(cy);
        path= new WaveAlg().findBishopPath(map.getField(), getX(),getY(), getTx(), getTy());
    }
}
