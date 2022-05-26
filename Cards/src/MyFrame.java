import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame {
    animation panel;
    private JButton Beschleunugen, Start, Verlangsamen, InsertInfos;
    JFrame Infos;

    MyFrame() {

        panel = new animation();
        Start = new JButton();
        Beschleunugen = new JButton();
        Verlangsamen = new JButton();
        InsertInfos = new JButton();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(Start);
        this.add(Beschleunugen);
        this.add(Verlangsamen);
        this.add(InsertInfos);
        this.add(panel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

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
                panel.triggerTimer();
            }
        });
        Beschleunugen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.incV();
            }
        });

        Verlangsamen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.decV();
            }
        });

        InsertInfos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Infos = new JFrame();
                Infos.setLocationRelativeTo(null);
                Infos.setVisible(true);

                InfoPanel Infosp = new InfoPanel();
                Infos.add(Infosp);
                Infos.pack();

            }
        });

    }
}