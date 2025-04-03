
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JFrame implements Runnable{
    int col = 8;
    int row = 10;
    int sizeX = 50;
    int sizeY = 50;
    int left = 100;
    int top = 100;
    int selected = -1;
    ArrayList<Unit> units = new ArrayList<>();
    boolean isWork = true;
    BufferedImage bi;
    BufferedImage wall;
    Map map =new Map();
    public static void main(String[] args) {
        Game frame = new Game();
        frame.setDefaultCloseOperation(2);
        frame.setBounds(10,102,1200,800);
        frame.setVisible(true);
        new Thread(frame).start();
    }

    public Game() {
        try {
//            bi = ImageIO.read(new File("dragon0.png"));
            wall = ImageIO.read(new File("Wall.jpg"));
            //bi = bi.getSubimage(0,0,40,40);
        } catch (IOException e) {
            bi = new BufferedImage(sizeX,sizeY,BufferedImage.TYPE_INT_ARGB);
            Graphics g2 = bi.getGraphics();
            g2.setColor(Color.yellow);
            g2.fillOval(0, 0, sizeX, sizeY);
        }
        units.add(new Rook(1,2));
        units.add(new Queen(3,7));
        units.add(new Knight(6,4));
        units.add(new Bishop(1,1));
        for(Unit u : units) {
            u.setMap(map);
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int cx = (mouseEvent.getX()-left)/sizeX;
                int cy = (mouseEvent.getY()-top)/sizeY;
                if (mouseEvent.getButton()==MouseEvent.BUTTON1) {
                    for (int i = 0; i < units.size(); i++) {
                        Unit u = units.get(i);
                        if(u.getX()==cx && u.getY()==cy) {
                            selected = i;
                        }
                    }
                }else if(mouseEvent.getButton()==MouseEvent.BUTTON3){
                    for (int i = 0; i < units.size(); i++) {
                        if(i==selected){
                            Unit u = units.get(i);
                            u.setTarget(cx,cy);
                        }
                    }
                }
                repaint();//удалить  в будущем
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                isWork = false;
            }
        });
    }
    @Override
    public void paint(Graphics g) {
        this.createBufferStrategy(2);
        BufferStrategy bs = this.getBufferStrategy();
        g = bs.getDrawGraphics();
        super.paint(g);
        drawGrid(g);


        col = map.getCol();
        row = map.getRow();
        drawGrid(g);
        drawWall(g);
        drawUnit(g);
        bs.show();
    }
    private  void drawWall(Graphics g){
        g.setColor(new Color(100,40,40));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                    if(map.IsWall(j,i))
                        g.drawImage(wall, left+ sizeX*j, top+sizeY*i,sizeX,sizeY,null);
            }
        }
    }

    private void drawUnit(Graphics g) {
        for (int i = 0; i < units.size(); i++) {
            Unit u = units.get(i);
            g.setColor(Color.yellow);
            g.drawImage(bi, u.getX()*sizeX+top, u.getY()*sizeY+top, sizeX,sizeY, null);
            if(i==selected){
                g.setColor(Color.red);
                g.drawOval(u.getX()*sizeX+top, u.getY()*sizeY+top, sizeX, sizeY);
            }
            g.setColor(Color.red);
                g.drawString(u.getClass().getSimpleName(),u.getX()*sizeX+left,u.getY()*sizeY+top);
            g.setColor(Color.green);
            g.fillRect(u.getX()*sizeX+left, u.getY()*sizeY+top, u.getLife()/2,5);
            g.setColor(Color.red);
            g.drawRect(u.getX()*sizeX+left, u.getY()*sizeY+top, 50,5);
        }

    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.red);
        for (int i = 0; i < row+1; i++) {
            g.drawLine(left,top+i*sizeX,left+sizeY*col,top+i*sizeX);
        }
        g.setColor(Color.blue);
        for (int i = 0; i < col+1; i++) {
            g.drawLine(left+i*sizeY, top, left+i*sizeY, top + sizeX * row);
        }
    }

    @Override
    public void run() {
        while(isWork){
            for (Unit u: units) {
                u.move();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
    }
}