import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoPanel extends JPanel {
    final int PANEL_WIDTH = 400;
    final int PANEL_HEIGHT = 500;

    private JLabel Name, Runde, Zeit;
    private JButton nextRound, lastRound, morePlayer, lessPlayer, Save, Reset;

    private int maxPlayer = 100;
    private JTextField[] tfName = new JTextField[maxPlayer];
    private JTextField[] tfRunde = new JTextField[maxPlayer];
    private JTextField[] tfZeit = new JTextField[maxPlayer];

    private int playercount = 0;
    private int round = 0;

    private Fahrer[] Driver = new Fahrer[maxPlayer];
    private Race Rennen;

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
        Save.setBounds(230, 130, 150, 30);
        this.add(Save);

        Reset = new JButton("Reset");
        Reset.setBounds(230, 160, 150, 30);
        this.add(Reset);

        // Textfelder
        // addPLayer();
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
        tfName[playercount] = new JTextField("Fahrer");
        tfName[playercount].setBounds(10, 40 + (26 * playercount), 70, 25);
        this.add(tfName[playercount]);

        tfRunde[playercount] = new JTextField(Integer.toString(round));
        tfRunde[playercount].setBounds(90, 40 + (26 * playercount), 30, 25);
        tfRunde[playercount].setEditable(false);
        this.add(tfRunde[playercount]);

        tfZeit[playercount] = new JTextField("0");
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
        }

    }

    public void nextRound() {
        round++;
        saveData();

        for (int i = 0; i < playercount; i++) {
            tfRunde[i].setText(Integer.toString(round));
            tfZeit[i].setText("0");
            tfName[i].setEditable(false);
        }
    }

    public void lastRound() {
        round--;
        saveData();

        for (int i = 0; i < playercount; i++) {
            tfRunde[i].setText(Integer.toString(round));
            tfZeit[i].setText("0");
            tfName[i].setEditable(false);
        }
    }

    public void saveData() {

        Rennen = new Race(playercount + 1, round + 1, "test");
        for (int i = 0; i < playercount; i++) {
            Driver[i] = new Fahrer(tfName[i].getText());
            Driver[i].setTime(Integer.parseInt(tfRunde[i].getText()), Integer.parseInt(tfZeit[i].getText()));
            Rennen.addDriver(Driver[i], i);
        }
    }

    public void StoreData() {
        System.out.print(Rennen.toString());
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Daten.txt"));
            for (int i = 0; i < playercount; i++) {
                out.writeObject(Rennen);
            }
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

    }

    public void GetData() {
        // ObjectInputStream in new
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Daten.txt"));
            for (int i = 0; i < playercount; i++) {
                Rennen = (Race) in.readObject();
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

    }

}
