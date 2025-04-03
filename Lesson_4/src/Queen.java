public class Queen extends Unit  {
    public  Queen( int x , int y){
        super(x,y);
    }

    @Override
    public void setTarget(int cx, int cy) {
        //super.setTarget(cx, cy);

        path= new WaveAlg().findQueenPath(map.getField(), getX(),getY(), cx,cy);
    }
}
