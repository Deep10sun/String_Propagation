import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import javax.swing.*;

public class myFrame extends JFrame implements MouseMotionListener,Runnable,MouseListener,KeyListener{
    public double x;
    public double y;
    public int width;
    public int height;
    int count = 0;
    string s;
    Graphics graph;
    Thread thread;
    myFrame(int w, int h){
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(w,h);
        this.setVisible(true);
        width = w;
        height = h;
        thread = new Thread(this,"String");
        thread.start();
    }
    public void setStr(string g ){
        s = g;
    }
    public void str_can(double[] coords){
        coords[0] = width*coords[0];
        coords[1] = width*coords[1]+height/2.0;
    }

    public void can_str(double[] coords){
        coords[0] = coords[0]/width;
        coords[1] = (coords[1]-(height/2))/width;
    }
    public void draw(string g, int i){
        Graphics2D g2d = (Graphics2D)getGraphics();
        g2d.setColor(Color.BLACK);
        double[] cords = {g.x[i-1],g.y0[i-1]};
        double[] cords2 = {g.x[i],g.y0[i]};
        str_can(cords2);
        str_can(cords);
        Stroke sk = new BasicStroke(2f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        g2d.setStroke(sk);
        g2d.drawLine((int)cords[0],(int)cords[1],(int)cords2[0],(int)cords2[1]);
    }
    public void reset(){
        
    }
    public void run() {
        while(true){
            for(int i = 0; i < 5; i++){
                s.move();
            }
            s.drew();
            Graphics2D g2d = (Graphics2D)getGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0,0,600,600);
            try{
                Thread.sleep(15);
            }
            catch(InterruptedException ie){
                System.out.println(ie.getMessage());
            }
           
        }
    }
    public void mouseClicked(MouseEvent e){
        double[] coords = {e.getX(),e.getY()};
            can_str(coords);
            x = Math.floor(coords[0]*1000)/1000;
            int index = Math.abs(Arrays.binarySearch(s.x,x));
            s.y1[index] = coords[1];
    }
    public void mousePressed(MouseEvent e){
        double[] coords = {e.getX(),e.getY()};
            can_str(coords);
            x = Math.floor(coords[0]*1000)/1000;
            int index = Math.abs(Arrays.binarySearch(s.x,x));
            s.y1[index] = coords[1];
        
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseDragged(MouseEvent e){
        double[] coords = {e.getX(),e.getY()};
        if(coords[1] >= 250 && coords[1] <= 550){
            can_str(coords);
            x = Math.floor(coords[0]*100)/100;
            int index = Math.abs(Arrays.binarySearch(s.x,x));
            s.y1[index] = coords[1];
        }
    }
    public void mouseMoved(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'r' || e.getKeyChar() == 'R'){
            Arrays.fill(s.y2,0);
            Arrays.fill(s.y0, 0);
            Arrays.fill(s.y1,0);
        }
        if(e.getKeyChar() == 's'){
            Arrays.fill(s.y0,0);
        }
    }
    public void keyReleased(KeyEvent e) {
    }
}
