import java.awt.*;
import javax.swing.*;

public class car {
    float x;
    float y;
    float velocity;
    Image cardImage;
    int i = 0;
    boolean b = true;
    int Runde=0;
    String name;

    public car() {
        x = 100;
        y = 70;
        velocity = 10;
        cardImage = new ImageIcon("Recources/car2 normal.png").getImage();
        name = "Car";

    }
    public car(String name) {
        x = 100;
        y = 70;
        velocity = 10;
        cardImage = new ImageIcon("Recources/car2 normal.png").getImage();
        this.name = name;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getx() {
        return Math.round(x);
    }

    public int gety() {
        return Math.round(y);
    }

    public Image getIcon() {
        return cardImage;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity / 10;
    }

    public float getVelocity() {
        return velocity * 10;
    }

    public void CalcPosRactange(int RecX, int RecY, int RecWidht, int RecHeight) {

        if ((this.y <= RecY && this.x <= RecX + RecWidht) || (this.y >= RecHeight + RecY && this.x >= RecX)) {
            this.x += this.velocity;

        } else {
            if (b) {
                swapIcon();
                b = false;
                this.y += this.velocity;
            }
            this.y += this.velocity;

            if (this.y >= RecHeight + RecY || this.y <= RecY) {
                this.velocity *= -1;
                swapIcon();
                b = true;
            }
        }

    }

    public void swapIcon() {
        i++;
        switch (i) {
            case 1:
                cardImage = new ImageIcon("Recources/car2 down.png").getImage();

                break;

            case 2:
                cardImage = new ImageIcon("Recources/car2left.png").getImage();

                break;

            case 3:
                cardImage = new ImageIcon("Recources/car2 up.png").getImage();

                break;

            case 4:
                cardImage = new ImageIcon("Recources/car2 normal.png").getImage();
                i = 0;
                Runde++;
                break;

            default:
                break;
        }
    }
    public int getRound(){
        return this.Runde;
    }

    // eine Runde hat 5060 pixel 100 mal in der sekunde wird aktualisiert
    public void setTimeRound(int sekunden) {
        if (sekunden>0)
        this.setVelocity(770 / sekunden);
        else this.setVelocity(0);
    }
}
