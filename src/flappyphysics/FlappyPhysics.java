package flappyphysics;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
public class FlappyPhysics extends JPanel implements ActionListener{
   int WIDTH = 640;
   boolean f = false;
   int HEIGHT = 500;
   float birdradius = 20;
   float Y = birdradius + 20;
   float Speed = 2;
   double Gravity = 9.8;
   double birdMass = .04;
   double flapF = 0;
   double Forceflap = 2;
   static float FPS = 30;
   double sum = ((Gravity*birdMass)-Forceflap)*50;
   int wing = 65;
   
   public FlappyPhysics() {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    JPanel pane1 = new JPanel();
    add(pane1);
    JButton b1 = new JButton("Flap");
    JButton b2 = new JButton("Change Mass");
    JButton b5 = new JButton("Change Flap Force");
    JButton b3 = new JButton("Change FPS");
    JButton b4 = new JButton("About");
    b1.addActionListener(this);
    b2.addActionListener(this);
    b5.addActionListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
    pane1.add(b1);
    pane1.add(b2);
    pane1.add(b5);
    pane1.add(b3);
    pane1.add(b4);
    
      Thread gameThread = new Thread() {
         @Override
         public void run() {
            while (true) {
               Y += Speed;
               if (Y + birdradius > HEIGHT) {
                  Speed = 0;
                  Y = HEIGHT - birdradius;
               }
               if(Speed == 0){
                   flapF=0;
                   wing = 65;
               }
               repaint();
               
               try {
                  Thread.sleep(1000 / (int)FPS); 
               } catch (InterruptedException ex) { }
               Speed+=Gravity;
            }
         }
      };
      gameThread.start();
   }
   
   @Override
   public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(new Color(100,190,220));
    g.fillRect(0,0,WIDTH,HEIGHT-50);                                            //blue background
    g.setColor(new Color(100,190,50));
    g.fillRect(0,HEIGHT-50,WIDTH,20);                                           //green grass
    g.setColor(new Color(255,255,180));
    g.fillRect(0,HEIGHT-30,WIDTH,50);                                           //yellow soil 
    g.setColor(new Color(240,240,240));                                         
    g.fillRoundRect(27,95,30,360,10,10);                                        //position y axis background
    g.fillRoundRect(WIDTH-215,HEIGHT-170,210,110,10,10);                //fbd background
    g.fillRoundRect(WIDTH-215, 90,210,165,10,10);                        //velocity background
    
    g.setColor(new Color(0,0,0));
    g.fillRect(9, 42, 200, 50);                                    //background left
    g.fillRect(WIDTH-150, 42, 140, 45);                            //background right
    g.fillRect(WIDTH-212, HEIGHT-120, 120, 40); 
    g.fillRect(WIDTH-212, 130, 117, 40); 
    
    
    g.setColor(Color.yellow);
    g.fillRect((int) (320 - birdradius), (int) (Y - birdradius-50),(int)(2 * birdradius), (int)(2 * birdradius));   //bird body
    g.setColor(Color.orange);
    g.fillRect((int) (345 - birdradius), (int) (Y-45),(int)(birdradius), (int)(6));                                 //beak
    g.setColor(Color.white);
    g.fillOval((int) (350 - birdradius), (int) (Y-65),(15),(17));                                        //eyeball
    g.fillOval((int) (310 - birdradius), (int) (Y-wing),(30),(17));                                       //wing
    g.setColor(Color.gray);
    g.fillOval((int) (358 - birdradius), (int) (Y - 11-50),(int)(5), (int)(8));                                     //pupil
    g.setColor(Color.black);
    g.drawLine(WIDTH-50, HEIGHT-95, WIDTH-80, HEIGHT-95);                       //FBD x axis
    g.drawLine(WIDTH-80, HEIGHT-125, WIDTH-80, HEIGHT-65);                      //FBD Y axis
    g.setColor(Color.orange);
    g.fillRect(WIDTH-40, (int)((Speed)+148),5,5);                                    //velocity bird
    g.fillOval(WIDTH-70, (int)(HEIGHT-100),10,10);                                     //free body diagram bird
    g.setColor(Color.blue);
    g.fillRect(WIDTH-68,(HEIGHT+(int)((Gravity*birdMass)-Forceflap)*20)-100,7,-(int)((Gravity*birdMass)-Forceflap)*20);//positive force
    g.fillRect(WIDTH-68,HEIGHT-90,7,(int)((Gravity*birdMass)-Forceflap)*20);                                            //negative force
    
    
    g.setColor(Color.black);
    g.drawLine(550, (int)((Speed)+150),545,(int)((Speed)+150));                 //velocity moving line
    g.drawLine(WIDTH-90, 150, WIDTH-10, 150);                                      //velocity x axis
    g.drawLine(WIDTH-90,100,WIDTH-90,250);                                      //position y axis
    g.drawLine(50, HEIGHT-50, 50, 100);                                         //velocity y axis
    g.setFont(new Font("ARIAL", Font.BOLD, 9));
    g.setColor(Color.red);
    g.fillRect((int)45, (int)(Y-31), 15, 3);                                    //position moving rectangle
    g.setColor(Color.black);
        for(int i = 0; i <HEIGHT-140; i+=10){
            g.drawString(Integer.toString(i),30,HEIGHT-47-i);                   //position grade
            g.drawLine(47,HEIGHT-50-i,52,HEIGHT-50-i);
        }                                          
    g.setColor(new Color(0,255,0));
    g.setFont(new Font("Calibri", Font.PLAIN, 12));
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();
    StringBuilder sb5 = new StringBuilder();
    StringBuilder sb6 = new StringBuilder();
    StringBuilder sb7 = new StringBuilder();
    Formatter formatter1 = new Formatter(sb1);
    Formatter formatter2 = new Formatter(sb2);
    Formatter formatter3 = new Formatter(sb3);
    Formatter formatter4 = new Formatter(sb4);
    Formatter formatter5 = new Formatter(sb5);
    Formatter formatter6 = new Formatter(sb6);
    Formatter formatter7 = new Formatter(sb7);
    formatter1.format("Velocity= %2.0f pix/sec",-Speed);
    formatter2.format("Gravity= %.2f pix/sec^2", Gravity);
    formatter3.format("Flap Force= +%.2f N", flapF);
    formatter4.format("Mass= %.2f Kg", birdMass);
    formatter5.format("Y-Coordinate= %3.0f pixel", (HEIGHT-Y-20));
    formatter6.format("FPS= %2.0f fps", (FPS));
    formatter7.format("Net Force= %.2f N",-((Gravity*birdMass)-Forceflap));
    g.drawString(sb1.toString(), 20, 52);
    g.drawString(sb1.toString(), WIDTH-210, 152);
    g.drawString(sb2.toString(), 20, 67);
    g.drawString(sb5.toString(), 20, 82);
    g.drawString(sb3.toString(), WIDTH-140, 52);
    g.drawString(sb4.toString(), WIDTH-140, 67);
    g.drawString(sb6.toString(), WIDTH-140, 82);
    g.drawString(sb7.toString(), WIDTH-210, HEIGHT-98);
    g.setColor(Color.red);
    g.drawString("Free Body Diagram", WIDTH-140, HEIGHT-150);
    g.drawString("(when Flapped)", WIDTH-138, HEIGHT-138);
   }
   
   public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            JFrame frame = new JFrame("Flappy Physics");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new FlappyPhysics());
            frame.pack();
            frame.setVisible(true);
            frame.setResizable(false);
            JOptionPane.showMessageDialog(null,"Press Flap button repeatedly just as how you play Flappy Bird", "Flappy Physics", JOptionPane.INFORMATION_MESSAGE);
         }
      });
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if(s.equals("Flap")){
            flap();
        }
        if(s.equals("Change Mass")){
            mass();
        }
        if(s.equals("Change FPS")){
            fps();
        }
        if(s.equals("About")){
            JOptionPane.showMessageDialog(null,"By: \tReannu Instrella\n\tKeno Frianeza\n\tGeri Ann Apilado", "About", JOptionPane.INFORMATION_MESSAGE);
        }
        if(s.equals("Change Flap Force")){
            force();
        }
    }
   public void mass(){
        String second = JOptionPane.showInputDialog("Enter Mass of Object: ");
        JOptionPane.showMessageDialog(null,"Mass is now " + second + " kg", "Mass", JOptionPane.WARNING_MESSAGE);
        birdMass = Double.parseDouble(second);
   }
   public void force(){
        String second = JOptionPane.showInputDialog("Enter Flap Force (Default is 2 Newtons): ");
        JOptionPane.showMessageDialog(null,"Force is now " + second + " N", "Force", JOptionPane.WARNING_MESSAGE);
        Forceflap = Double.parseDouble(second);
   }
   public static void fps(){
        String second = JOptionPane.showInputDialog("Enter FPS: ");
        JOptionPane.showMessageDialog(null,"FPS is now " + second + " frames per second", "FPS", JOptionPane.WARNING_MESSAGE);
        FPS = Integer.parseInt(second);
   }
   public void flap(){
        Speed -= (Forceflap/birdMass);
        flapF = Forceflap;
        wing = 55;
        repaint();
   }
}