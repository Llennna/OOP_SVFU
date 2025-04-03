public class Rook extends Unit {
    public Rook(int x, int y){
        super(x,y);
    }

    @Override
    public void setTarget(int cx, int cy) {
        //super.setTarget(cx, cy);
        setTx(cx);
        setTy(cy);
        path= new WaveAlg().findRookPath(map.getField(), getX(),getY(), getTx(), getTy());

    }

}
