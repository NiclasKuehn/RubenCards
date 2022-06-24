import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InfoPanel extends JPanel {
    final int PANEL_WIDTH = 400;
    final int PANEL_HEIGHT = 500;

    private JLabel Name, Runde, Zeit;
    private JButton nextRound, lastRound, morePlayer, lessPlayer, Save, Load,Reset;
    private JTextField RaceLabel;

    private int maxPlayer = 100;
    private JTextField[] tfName = new JTextField[maxPlayer];
    private JTextField[] tfRunde = new JTextField[maxPlayer];
    private JTextField[] tfZeit = new JTextField[maxPlayer];

    private int playercount = 0;
    private int round = 0;

    private Race Rennen = new Race("default");
    private Fahrer zwischenFahrer = new Fahrer();

    public InfoPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(null);

        // Label

        Name = new JLabel("Name");
        Name.setBounds(10, 10, 50, 20);
        this.add(Name);

        Runde = new JLabel("Runde");
        Runde.setBounds(80, 10, 50, 20);
        this.add(Runde);

        Zeit = new JLabel("Zeit in s");
        Zeit.setBounds(150, 10, 50, 20);
        this.add(Zeit);

        // BUttons

        morePlayer = new JButton("+ Fahrer");
        morePlayer.setBounds(230, 10, 150, 30);
        this.add(morePlayer);

        lessPlayer = new JButton("- Fahrer");
        lessPlayer.setBounds(230, 40, 150, 30);
        this.add(lessPlayer);

        nextRound = new JButton("nächste Runde");
        nextRound.setBounds(230, 70, 150, 30);
        this.add(nextRound);

        lastRound = new JButton("letze Runde");
        lastRound.setBounds(230, 100, 150, 30);
        this.add(lastRound);

        Save = new JButton("Save");
        Save.setBounds(230, 160, 150, 30);
        this.add(Save);

        Load = new JButton("Load");
        Load.setBounds(230, 190, 150, 30);
        this.add(Load);

        Reset = new JButton("Reset");
        Reset.setBounds(230, 220, 150, 30);
        this.add(Reset);

        // Textfelder

        RaceLabel = new JTextField("Name des Rennens");
        RaceLabel.setBounds(230, 130 , 150, 30);
        this.add(RaceLabel);

        // addPLayer();

        Reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reset();
            }
        });
        Load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GetData();
            }
        });
        morePlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPLayer();
            }
        });
        lessPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remPLayer();
            }
        });
        nextRound.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextRound();
            }
        });
        lastRound.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lastRound();
            }
        });
        Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StoreData();
            }
        });

    }

    public void addPLayer() {
        if(Rennen.getDriverCount()>playercount){
        tfName[playercount] = new JTextField(Rennen.getDriver(playercount).getName());
        tfRunde[playercount] = new JTextField(Integer.toString(round));
        tfZeit[playercount] = new JTextField(Integer.toString(Rennen.getDriver(playercount).getTime(round)));
        }
        else{
        tfName[playercount] = new JTextField("Fahrer");
        tfRunde[playercount] = new JTextField(Integer.toString(round));
        tfZeit[playercount] = new JTextField("0");
        Rennen.addDriver(new Fahrer("init"));
        }

        tfName[playercount].setBounds(10, 40 + (26 * playercount), 70, 25);
        this.add(tfName[playercount]);

        tfRunde[playercount].setBounds(90, 40 + (26 * playercount), 30, 25);
        tfRunde[playercount].setEditable(false);
        this.add(tfRunde[playercount]);

        tfZeit[playercount].setBounds(150, 40 + (26 * playercount), 50, 25);
        this.add(tfZeit[playercount]);

        playercount++;
        

    }

    public void remPLayer() {
        if (playercount > 0) {
            playercount--;
            this.remove(tfRunde[playercount]);
            this.remove(tfZeit[playercount]);
            this.remove(tfName[playercount]);
            this.setVisible(false);
            this.setVisible(true);
            Rennen.remDriver();
        }

    }

    public void nextRound() {
        round++;
        saveData();

        for (int i = 0; i < playercount; i++) {
            tfRunde[i].setText(Integer.toString(round));
            if(Rennen.getDriver(i).getRounds()>=round)
            tfZeit[i].setText(Integer.toString(Rennen.getDriver(i).getTime(round)));
            else tfZeit[i].setText("0");
            tfName[i].setEditable(false);
        }
    }

    public void lastRound() {
        if(round >0){
        round--;
        saveData();

        for (int i = 0; i < playercount; i++) {
            tfRunde[i].setText(Integer.toString(round));
            tfZeit[i].setText(Integer.toString(Rennen.getDriver(i).getTime(round)));
            tfName[i].setEditable(false);
        }}
    }

    public void saveData() {
        System.out.println("daten werden in Race gespeichert: (playercount: "+(playercount+1)+", round:"+(round+1)+")");
        for (int i = 0; i < playercount; i++) {
            zwischenFahrer= Rennen.getDriver(i);
            zwischenFahrer.setLabel(tfName[i].getText());
            zwischenFahrer.setTime(Integer.parseInt(tfRunde[i].getText()), Integer.parseInt(tfZeit[i].getText()));
            Rennen.changeDriver(i,zwischenFahrer);
            Rennen.setLabel(RaceLabel.getText());
        }
    }

    public void StoreData() {
        saveData();
        Rennen.StoreRace();

    }
    public void Reset(){
        
        for (int i = playercount; i > 0; i--) {
            remPLayer();
        }
        round=0;
    }

    public void GetData() {
        Reset();
        Rennen.GetRace(RaceLabel.getText());
        for (int i = 0; i < Rennen.getDriverCount(); i++) {
            addPLayer();
        }
    }
    public Race getInfos(){
        return Rennen;
    }


}
