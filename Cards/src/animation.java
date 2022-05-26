import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class animation extends JPanel implements ActionListener {
    final int PANEL_WIDTH = 2000;
    final int PANEL_HEIGHT = 1000;
    Image card;
    Image background;
    Timer timer;
    int maxStarter = 10;
    car[] Starter = new car[maxStarter];
    Graphics2D g2D;

    public animation() {
        for (int i = 0; i < maxStarter; i++) {
            Starter[i] = new car();
            Starter[i].setTimeRound(10 + i);

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

    public void decV() {
        for (int i = 0; i < maxStarter; i++) {
            timer.stop();
            Starter[i].setVelocity(Starter[i].getVelocity() / 2);
            timer.start();
        }
    }

    public void incV() {
        for (int i = 0; i < maxStarter; i++) {
            timer.stop();
            Starter[i].setVelocity(Starter[i].getVelocity() * 2);
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
        g2D.drawString("Text", 400, 300);

        for (int i = 0; i < maxStarter; i++) {
            g2D.drawString(Starter[i].getName(), Starter[i].getx() + 20, Starter[i].gety());
            g2D.drawImage(Starter[i].getIcon(), Starter[i].getx(), Starter[i].gety(), null);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < maxStarter; i++) {
            Starter[i].CalcPosRactange(10, 70, 1800, 730);
        }

        repaint();

    }
}