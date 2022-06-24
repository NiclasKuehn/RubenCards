import java.util.ArrayList;
import java.util.List;

import java.io.*;

public class Race implements Serializable{
    private String label;
    List<Fahrer> Fahrer = new ArrayList<>();


    public Race(String label) {

        this.label = label;

    }

    public int getDriverCount() {
        return Fahrer.size();
    }

    public int getRounds() {
        if (Fahrer.size()>0){
            return Fahrer.get(0).getRounds();
        }
        throw new IllegalArgumentException("In dem Rennen gibt es noch keinen Fahrer");
    }
    


    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addDriver(Fahrer Driver) {
        Fahrer.add(Driver);
    }
    public void remDriver() {
        Fahrer.remove(Fahrer.size()-1);
    }
    public void changeDriver(int i, Fahrer Driver) {
        Fahrer.set(i, Driver);
    }

    public Fahrer getDriver(int Nummer) {
        if(Nummer<=Fahrer.size())
        return Fahrer.get(Nummer);
        throw new IllegalArgumentException("Fahrer "+Nummer+" existiert noch garnicht.");

    }

    @Override
    public String toString() {
        String p = "";
        for (int j = 0; j < this.getDriverCount(); j++) {
            p = p + this.getDriver(j).getName() + ": ";
            for (int i = 0; i < this.getDriver(j).getRounds(); i++) {
                p = p + this.getDriver(j).getTime(i) + " ; ";
            }
            p = p + "\n";
        }
        ;

        return p;

    }
    public void StoreRace(){
        Race sRace=this;
        System.out.println("\n Rennen wird nun gespeichert unter "+sRace.label+": \n\n"+sRace.toString());
        

            try {
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(sRace.label));
                stream.writeObject(sRace);
                stream.close();
            } catch (IOException ioex) {
                System.err.println("Fehler beim Schreiben des Objekts aufgetreten.");
                ioex.printStackTrace();
            }
    }
    public void GetRace(String dateiname) {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(dateiname));
            Race bRace =(Race) stream.readObject();
            this.label=dateiname;
            this.Fahrer=bRace.Fahrer;
            stream.close();
        } catch (ClassNotFoundException cnfex) {
            System.err.println("Die Klasse des geladenen Objekts konnte nicht gefunden werden.");
        } catch (IOException ioex) {
            System.err.println("Das Objekt konnte nicht geladen werden.");
            ioex.printStackTrace();
        }

    }

}
