import java.util.*;

public class string {
    myFrame mf;
    public int num;
    public double[] x;
    public double[] y0;
    public double[] y1;
    public double[] y2;
    public int gamma = 200;
    public double stress = 0.002; 
    public double xdiff;
    public double speed = 1.0/100;
    public double tdiff = 0.2;
    
    public string(int n){
        num = n;
        x = new double[num];
        for(int i = 0; i < n; i++){
            x[i] = (i*1.0)/n;
        }
        y0 = new double[n];
        y1 = Arrays.copyOf(y0,y0.length);
        y2 = Arrays.copyOf(y1,y1.length);
        xdiff = (x[1] - x[0]);
        y2[0] = y1[0];
        y2[1] = y1[1];
        y2[num-2] = y1[num-2];
        y2[num-1] = y1[num-1];
        
    }
    public void setFrame(myFrame test){
        mf = test;
    }
    
    public double update(int i, double[] y0, double[] y1, int gamma, double stress, double xdiff, double tdiff, double speed){
        return (Math.pow((1/Math.pow(speed*tdiff,2))+(gamma/(2*tdiff)),-1))*((1/Math.pow(xdiff,2))*(y1[i+1]-2*y1[i]+y1[i-1])-(1/Math.pow(speed*tdiff,2))*(y0[i]-2*y1[i])+(gamma/2*tdiff)*(y0[i])-(Math.pow(stress,2)/Math.pow(xdiff,4))*(y1[i-2]-4*y1[i-1]+6*y1[i]-4*y1[i+1]+y1[i+2]));
    }
    public void move(){
            for(int i = 2; i < y1.length-2;i+=1){
                y2[i] = update(i,this.y0,this.y1,this.gamma,this.stress,this.xdiff,this.tdiff,this.speed);
            }
            y0 = Arrays.copyOf(y1,y1.length);
            y1 = Arrays.copyOf(y2,y2.length);
    }
    public void drew(){
        for(int i = 2; i < y0.length-2;i++){
            mf.draw(this,i);
        }
    }
}
