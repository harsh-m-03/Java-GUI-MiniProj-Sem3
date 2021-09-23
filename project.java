import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.util.*;
import java.io.*;

public class project {
    public static void main(String[] args) {

        // Creating JFrame
        JFrame frameForFile = new JFrame();

        frameForFile.setTitle("Encrypter/Decrypter");
        frameForFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameForFile.setSize(450, 380);
        frameForFile.setLocationRelativeTo(null);

        // Creating Font
        Font fontForButton = new Font("Times New Roman", Font.BOLD, 30);
        Font fontForRadio = new Font("", Font.PLAIN, 15);

        // Making Radiobuttons
        JRadioButton radioPDF = new JRadioButton("PDF");
        JRadioButton radioImage = new JRadioButton("Image");
        JRadioButton radioOther = new JRadioButton("Others");
        radioPDF.setFont(fontForRadio); // Adding fonts to radio buttons
        radioImage.setFont(fontForRadio);
        radioOther.setFont(fontForRadio);
        radioPDF.setBorder(null);
        radioImage.setBorder(null);
        radioOther.setBorder(null);
        radioPDF.setCursor(new Cursor(Cursor.HAND_CURSOR)); // changing the curson to hand_cursor
        radioImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
        radioOther.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Creating ButtonGroup to ensure no multiple buttons are selected
        ButtonGroup groupForRadioFile = new ButtonGroup();
        groupForRadioFile.add(radioPDF);
        groupForRadioFile.add(radioImage);
        groupForRadioFile.add(radioOther);

        // Adding Button
        JButton EncrpytButton = new JButton("Encrypt");
        JButton DecryptButton = new JButton("Decrypt");
        EncrpytButton.setFont(fontForButton);
        EncrpytButton.setPreferredSize(new Dimension(150, 38));
        EncrpytButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DecryptButton.setFont(fontForButton);
        DecryptButton.setPreferredSize(new Dimension(150, 38));
        DecryptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Adding RadioButtons to JPanel to make them together as one
        JPanel panelForRadio = new JPanel();
        panelForRadio.add(radioPDF);
        panelForRadio.add(radioImage);
        panelForRadio.add(radioOther);

        // Adding Buttons to JPanel to make them together as one
        JPanel panelForButton = new JPanel();
        panelForButton.add(EncrpytButton);
        panelForButton.add(DecryptButton);

        // Adding back Button
        JButton backButton = new JButton("<-");

        // Adding panel for back button
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.LINE_AXIS));
        backPanel.add(backButton);

        // Making buttons Responsive
        EncrpytButton.addActionListener(e -> {
            if (!radioPDF.isSelected() && !radioImage.isSelected() && !radioOther.isSelected()) { // if RadioButton not
                                                                                                  // selected,
                // throw error
                JFrame errorBox = new JFrame();
                JOptionPane.showMessageDialog(errorBox, "Please Select a choice");
            } else {
                System.out.println("Encrpyt-Button Clicked");
                operation();
            }
        });

        DecryptButton.addActionListener(e -> {
            if (!radioPDF.isSelected() && !radioImage.isSelected() && !radioOther.isSelected()) { // if RadioButton not
                                                                                                  // selected,
                                                                                                  // throw error
                JFrame errorBox = new JFrame();
                JOptionPane.showMessageDialog(errorBox, "Please Select a choice");
            } else {
                System.out.println("Decrpyt-Button Clicked");
                operation();
            }
        });

        // Adding everything to JFrame
        frameForFile.setLayout(new FlowLayout());
        frameForFile.add(panelForButton);
        frameForFile.add(panelForRadio);


        frameForFile.setVisible(true);

    }

    public static void operation() {
        int key = 3; // Pass any random key
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            System.out.println(data.length);
            fis.read(data);
            for (int i = 0; i < data.length; i++)
                data[i] = (byte) (key ^ data[i]);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fis.close();
            fos.close();
            JOptionPane.showMessageDialog(null, "Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}