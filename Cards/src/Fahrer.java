import java.io.Serializable;

public class Fahrer implements Serializable{
    private String name;
    private int gefahreneRunden;
    private int Zeiten[] = new int[100];

    public Fahrer(String Name) {
        this.name = Name;
        gefahreneRunden = 0;
    }

    public Fahrer() {
        gefahreneRunden = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getTime(int round) {
        return Zeiten[round];
    }

    public void setTime(int Round, int Time) {
        this.gefahreneRunden++;
        Zeiten[Round] = Time;
    }
    public void setLabel(String Label) {
        this.name = Label;
    }

    public int getRounds() {
        return this.gefahreneRunden;
    }

}
