import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Rennstrecke extends JPanel implements ActionListener {
    final int PANEL_WIDTH = 2000;
    final int PANEL_HEIGHT = 1000;
    Image card;
    Image background;
    Timer timer;
    int maxStarter = 10;
    List<car> Auto = new ArrayList<>();
    Graphics2D g2D;
    Race Rennen = new Race("");

    public Rennstrecke() {
        for (int i = 0; i < maxStarter; i++) {
            car Autoa = new car();
            Autoa.setTimeRound(10 + i);
            Auto.add(Autoa);

        }

        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        background = new ImageIcon("Cards/Recources/CardsHintzergrundbut.png").getImage();
        timer = new Timer(1, this);// ms;g2D.drawString("lol", 1000, 1000);

    }


    public void triggerTimer() {
        if (timer.isRunning())
            timer.stop();
        else
            timer.start();
    }

    public void setInfos(Race Rennen){
        if(Rennen==null)this.Rennen= new Race("init");
        timer.stop();
        this.Rennen =Rennen;
        Auto.clear();
        for (int i = 0; i < Rennen.getDriverCount(); i++) {
                     car autoa =new car(Rennen.getDriver(i).getName());
                     autoa.setTimeRound(Rennen.getDriver(i).getTime(0));
                     Auto.add(autoa);

        }
        g2D.drawString(Rennen.getLabel(), 400, 300);
        this.repaint();

    }
    

    public void decV() {
        for (int i = 0; i < Auto.size(); i++) {
            timer.stop();
            car autoa = Auto.get(i);
            autoa.setVelocity(Auto.get(i).getVelocity() / 2);
            Auto.set(i, autoa);
            timer.start();
        }
    }

    public void incV() {
        for (int i = 0; i < Auto.size(); i++) {
            timer.stop();
            car autoa = Auto.get(i);
            autoa.setVelocity(Auto.get(i).getVelocity() *2);
            Auto.set(i, autoa);
            timer.start();
        }
    }

    public void paint(Graphics g) {

        super.paint(g);// paint background
        g2D = (Graphics2D) g;
        g2D.drawImage(background, 0, 0, null);

        Font font = new Font("Serif", 1, 40);
        g2D.setFont(font);
        g.setColor(Color.ORANGE);
        g2D.drawString(Rennen.getLabel(), 400, 300);
        

        for (int i = 0; i < Auto.size(); i++) {
            g2D.drawString(Auto.get(i).getName(), Auto.get(i).getx() + 20, Auto.get(i).gety());
            g2D.drawImage(Auto.get(i).getIcon(), Auto.get(i).getx(), Auto.get(i).gety(), null);

        }
    }
    public void oneRound(){
        int T;
        for (int j = 0; j < Auto.size(); j++) {
            T=0;
            for (int i = 0; i < Rennen.getDriver(j).getRounds(); i++) {
                T+=Rennen.getDriver(j).getTime(i); 
            }
            car autoa = Auto.get(j);
            autoa.setTimeRound(T);            
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < Auto.size(); i++) {
            car autoa = Auto.get(i);
            //paint cars
            //autoa.setTimeRound(Rennen.getDriver(i).getTime(autoa.getRound()));
            autoa.CalcPosRactange(10, 70, 1800, 730);
            
            Auto.set(i, autoa);
        }

        repaint();

    }
}