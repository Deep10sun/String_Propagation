# String_Propagation

This is a simulation of a string that I made in my junior year of high school when I was taking AP CSA and learning JAVA for the first time. It really tested my ability to learn and absorb information. By the end of the project, I had gained a wealth of knowledge: implementing OOP in JAVA, using graphics libraries such as AWT and swing, Algorithm development, research skills (learning something completely new from scratch), and ultimately, self sufficiency/independence. 



 # **Equation: a(x,t) + v(x,t)γ = (c^2)κ(x,t)-(l^2)(d^4u/du^4)**
 
  a(x,t) is the acceleration of the wave
  
  v(x,t)γ is the damping term (accounts for energy lost over time)
        
  (c^2)κ(x,t) is the curvature of the string. Basically it is multipling the wave speed squared by the second spatial derivative of the string. (Describes how the string propagates)
        
  (l^2)(d^4u/du^4) is the resistive force. It is the fourth order spatial derivative that models the resistance due to the bending of the string.

  This equation combines all the factors of the string to produce its dynamic movement

  # CODE
  I had a total of 3 classes for this simulation: Main, MyFrame, and String

  **Main**:
      
      private static int WIDTH = 600; //setting WIDTH
      private static int HEIGHT = 600; //setting HEIGHT
      public static void main(String[] args){
          string g = new string(200); //creating the string
          myFrame mf = new myFrame(WIDTH,HEIGHT); //creating the window

          // "Connecting" the string to the window and vice versa
          mf.setStr(g); 
          g.setFrame(mf);
      }

  **MyFrame**:
  
  I used Jframe to animate the string (not the best choice). I also implemented interfaces to pickup mouse & keyboard input. 
 
    public class myFrame extends JFrame implements MouseMotionListener,Runnable,MouseListener,KeyListener{
    
  The window would start the moment the contructor is called (or the moment the object is created):
  
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
  The last line, (thread.start()) would allow the program to animate the string
  
    public void run() {
        while(true){
          //This would move the string 5 times before drawing it on to the screen
            for(int i = 0; i < 5; i++){
                s.move();
            }
            s.drew(); //DRAWS THE STRING

            //This segment is to reset the window to the background color (hence emualting the animation)
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

  The draw function draws each individual "section dx" of the string from start to finish (which is probably why it is also very laggy)

    public void draw(string g, int i){
        Graphics2D g2d = (Graphics2D)getGraphics();
        g2d.setColor(Color.BLACK);
        double[] cords = {g.x[i-1],g.y0[i-1]};
        double[] cords2 = {g.x[i],g.y0[i]};
        str_can(cords2); //just a function that converts coordinates of string to coordinates of canvas
        str_can(cords); // ^^
        Stroke sk = new BasicStroke(2f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        g2d.setStroke(sk);
        g2d.drawLine((int)cords[0],(int)cords[1],(int)cords2[0],(int)cords2[1]);
    }
  **String**:
  The string is "chopped" up into n number of pieces (depends on the parameter of the constructor)
  
  There are 4 arrays: 
  
  x[] stores the x position of each "chunk" of the string
  
  y0[], y1[], y2[] store the position of the string at different time points.
  
  The way this simulation works is using the fourth order differential equation above to find the position of the string **BASED** on the position of the string 2 "time-stamps" before. In other words, you iterate through each individual chunk of the string (dx) and calculate its new y position based on its positions two time stamps before using the fourth order differential equation above.

  **NOTE: You are considering both the previous time point, and the time point before the previous time point**
  
    public void move(){
        y2[0] = y1[0];
        y2[1] = y1[1];
        y2[num-2] = y1[num-2];
        y2[num-1] = y1[num-1];
            for(int i = 2; i < y1.length-2;i+=1){
                y2[i] = update(i,this.y0,this.y1,this.gamma,this.stress,this.xdiff,this.tdiff,this.speed);
            }
            y0 = Arrays.copyOf(y1,y1.length);
            y1 = Arrays.copyOf(y2,y2.length);
    }
Thus, when the move functoin is called, it is calculating the new y position of each chunk "dx" based on two time points before. This array is then drawn onto the window when the function is called.  
