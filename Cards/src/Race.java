import java.io.Serializable;

public class Race implements Serializable {
    private int driverCount;
    private int rounds;
    private String label;
    private Fahrer[] Driver;

    public Race() {
    }

    public Race(int driverCount, int rounds, String label) {
        this.driverCount = driverCount;
        this.rounds = rounds;
        this.label = label;
        for (int i = 0; i < driverCount; i++) {
            this.Driver[i] = new Fahrer();
        }

    }

    public int getDriverCount() {
        return this.driverCount;
    }

    public void setDriverCount(int driverCount) {
        this.driverCount = driverCount;
    }

    public int getRounds() {
        return this.rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addDriver(Fahrer Driver, int s) {

        this.Driver[s] = Driver;

    }

    public Fahrer getDriver(Fahrer Driver, int s) {

        return this.Driver[s] = Driver;

    }

    @Override
    public String toString() {
        String p = "";
        for (int j = 0; j < driverCount; j++) {
            p = p + Driver[j].getName() + ": ";
            for (int i = 0; i < rounds; i++) {
                p = p + Driver[i].getTime(i) + " ; ";
            }
            p = p + "\n";
        }
        ;

        return p;

    }

}
