import java.awt.event.*;
import javax.swing.*;

public class MainFenster extends JFrame {
    Rennstrecke Rennstrecke_;
    private JButton Beschleunugen, GoInOneRennen, GoRennen,Start, Verlangsamen, InsertInfos;
    JFrame Infos;
    InfoPanel Infosp;

    MainFenster() {

        Rennstrecke_ = new Rennstrecke();
        Start = new JButton();
        Beschleunugen = new JButton();
        Verlangsamen = new JButton();
        InsertInfos = new JButton("infos");
        GoRennen = new JButton("go");
        GoInOneRennen = new JButton("goinone");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(Start);
        this.add(Beschleunugen);
        this.add(Verlangsamen);
        this.add(InsertInfos);
        this.add(GoRennen);
        this.add(GoInOneRennen);
        this.add(Rennstrecke_);
        

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        GoInOneRennen.setBounds(1000, 300, 80, 80);
        GoInOneRennen.setVisible(true);
        // InsertInfos.setOpaque(false);
        // InsertInfos.setContentAreaFilled(false);
        // InsertInfos.setBorderPainted(false);

        GoRennen.setBounds(900, 300, 80, 80);
        GoRennen.setVisible(true);
        // InsertInfos.setOpaque(false);
        // InsertInfos.setContentAreaFilled(false);
        // InsertInfos.setBorderPainted(false);

        InsertInfos.setBounds(700, 300, 80, 80);
        InsertInfos.setVisible(true);
        // InsertInfos.setOpaque(false);
        // InsertInfos.setContentAreaFilled(false);
        // InsertInfos.setBorderPainted(false);

        Start.setBounds(225, 220, 80, 80);
        Start.setVisible(true);
        Start.setOpaque(false);
        Start.setContentAreaFilled(false);
        Start.setBorderPainted(false);

        Beschleunugen.setBounds(270, 320, 50, 35);
        Beschleunugen.setVisible(true);
        Beschleunugen.setOpaque(false);
        Beschleunugen.setContentAreaFilled(false);
        Beschleunugen.setBorderPainted(false);

        Verlangsamen.setBounds(215, 320, 50, 35);
        Verlangsamen.setVisible(true);
        Verlangsamen.setOpaque(false);
        Verlangsamen.setContentAreaFilled(false);
        Verlangsamen.setBorderPainted(false);

        Start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Rennstrecke_.triggerTimer();
            }
        });
        Beschleunugen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rennstrecke_.incV();
            }
        });

        Verlangsamen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rennstrecke_.decV();
            }
        });

        InsertInfos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Infos = new JFrame();
                Infos.setLocationRelativeTo(null);
                Infos.setVisible(true);

                Infosp = new InfoPanel();
                Infos.add(Infosp);
                Infos.pack();

            }
        });
        GoRennen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rennstrecke_.setInfos(Infosp.getInfos());
            }
        });
        GoInOneRennen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rennstrecke_.oneRound();
            }
        });

    }
}