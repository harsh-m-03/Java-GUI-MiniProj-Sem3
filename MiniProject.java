import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//user define package
import Password.password;

public class MiniProject {
    public static void main(String[] args) {

        // hiding other frames
        frameForFile.setVisible(false);
        frameForPass.setVisible(false);

        // Setting Font for homePage
        Font fontForHome = new Font("Times New Roman", Font.BOLD, 25);

        // Setting buttons for homePage
        JButton toChooseFile = new JButton("Work With File");
        JButton toChoosePass = new JButton("Work With Password");
        toChooseFile.setFont(fontForHome);
        toChoosePass.setFont(fontForHome);
        toChooseFile.setPreferredSize(new Dimension(268, 38));
        toChooseFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toChoosePass.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Making buttons Responsive
        toChooseFile.addActionListener(e -> {
            System.out.println("FileButton is Selected");
            homePage.setVisible(false); // Removing HomePage by making it invisible
            frameForFile.setVisible(true);
            frameForPass.setVisible(false);
            operationOnFile();
        });

        toChoosePass.addActionListener(e -> {
            System.out.println("Password is Selected");
            homePage.setVisible(false);
            frameForFile.setVisible(false);
            frameForPass.setVisible(true);
            operationOnPass();
        });

        // Adding buttons to panel
        JPanel page1Chooser = new JPanel();
        page1Chooser.add(toChooseFile);
        page1Chooser.add(toChoosePass);

        // adding buttons to jpanel

        // Adding panel to bod for alignment
        Box boxForHome = new Box(BoxLayout.Y_AXIS);
        boxForHome.add(Box.createVerticalGlue());
        boxForHome.add(page1Chooser);
        boxForHome.add(Box.createVerticalGlue());

        // Setting JFrame for homePage
        homePage.setTitle("Encrypter/Decrypter");
        homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homePage.setSize(350, 380); // setting JFrame size
        homePage.setResizable(false);
        homePage.setLocationRelativeTo(null); // to get JFrame in center
        homePage.add(boxForHome); // Adding Box to JFrame
        homePage.setVisible(true);
    }

    public static JFrame homePage = new JFrame();
    public static JFrame frameForFile = new JFrame();
    public static JFrame frameForPass = new JFrame();
    public static final int key = 3;

    // Methods for Pass:

   
    public static void operationOnPass() {
        // desiging frame for password
        frameForPass.setTitle("Work with Password");
        frameForPass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameForPass.setSize(500, 380);
        frameForPass.setResizable(false);
        frameForPass.setLocationRelativeTo(null);

        // Creating font
        Font fontForButton = new Font("Times New Roman", Font.BOLD, 25);
        Font fontForRadio = new Font("", Font.PLAIN, 15);

        // Creating textfield for input
        JTextField textField = new JTextField(25);
        textField.setPreferredSize(new Dimension(50, 38));
        textField.setEditable(false);
        textField.setFont(fontForRadio);

        // adding radioButtons for selection
        JRadioButton decryptEncrypt = new JRadioButton("Encrypt/Decrypt");
        JRadioButton GeneratePass = new JRadioButton("Generate Password");
        decryptEncrypt.setBorder(null);
        decryptEncrypt.setCursor(new Cursor(Cursor.HAND_CURSOR));
        decryptEncrypt.setFont(fontForRadio);
        GeneratePass.setBorder(null);
        GeneratePass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        GeneratePass.setFont(fontForRadio);

        // creating radio for generate password
        JRadioButton randomPass = new JRadioButton("Random");
        JRadioButton customPass = new JRadioButton("Custom");
        randomPass.setBorder(null);
        randomPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        randomPass.setFont(fontForRadio);
        customPass.setBorder(null);
        customPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        customPass.setFont(fontForRadio);

        // Creating ButtonGroup to ensure no multiple buttons are selected
        ButtonGroup groupForRadioFile = new ButtonGroup();
        groupForRadioFile.add(decryptEncrypt);
        groupForRadioFile.add(GeneratePass);

        // creating buttongroup for password generator
        ButtonGroup groupForPass = new ButtonGroup();
        groupForPass.add(customPass);
        groupForPass.add(randomPass);

        // Creating buttons
        JButton proceed = new JButton("Proceed");
        proceed.setFont(fontForButton);
        proceed.setPreferredSize(new Dimension(150, 38));

        // Adding back Button
        JButton backButton = new JButton("Home");
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Copy button
        JButton copyButton = new JButton("Copy");
        copyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Making backbutton responsive
        backButton.addActionListener(e -> {
            System.out.println("Back-Button Clicked");
            callHomePage();
        });

        // Creating panel for proceed and textfield
        JPanel panelForProceed = new JPanel();
        panelForProceed.add(proceed);
        panelForProceed.add(textField);

        // Jpanel for radiobuttons
        JPanel panelForRadio = new JPanel();
        panelForRadio.add(GeneratePass);
        panelForRadio.add(decryptEncrypt);

        // options in box
        Box boxForRadioOptions = new Box(BoxLayout.Y_AXIS);
        boxForRadioOptions.add(panelForRadio);
        boxForRadioOptions.setPreferredSize(new Dimension(440, 50));

        // Jpanel for password generator
        JPanel panelForPass = new JPanel();
        panelForPass.add(customPass);
        panelForPass.add(randomPass);
        panelForPass.setPreferredSize(new Dimension(80, 80));

        // box for password generator
        Box boxForPass = new Box(BoxLayout.Y_AXIS);
        boxForPass.add(panelForPass);
        boxForPass.setPreferredSize(new Dimension(440, 100));
        boxForPass.setVisible(false);

        // adding radiobutton responsive
        decryptEncrypt.addActionListener(e -> {
            textField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
            boxForPass.setVisible(false);
            textField.setEditable(true); // allow user to use textfield if this radio is selected
        });

        GeneratePass.addActionListener(e -> {
            textField.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            boxForPass.setVisible(true);
            textField.setEditable(false);
        });

        // Making proceed button responsive
        proceed.addActionListener(e -> {
            if (!decryptEncrypt.isSelected() && !GeneratePass.isSelected()) // if none is selected
                JOptionPane.showMessageDialog(null, "Please Select a choice", "Alert", JOptionPane.WARNING_MESSAGE);// alert
                                                                                                                    // message
            else if (decryptEncrypt.isSelected()) { // if user selects this radio
                char[] userPass = textField.getText().toCharArray(); // get the user's password from the textfield
                System.out.println("Decrypt/Encrypt Password");
                for (int i = 0; i < userPass.length; i++) // Encrypting / Decrypting password
                    userPass[i] = (char) ((int) (userPass[i]) ^ key);
                JOptionPane.showMessageDialog(null, "Done! Get Your Output In the TextBox"); // Done Message
                textField.setText(String.valueOf(userPass)); // Showing result in the text field
            } else if (GeneratePass.isSelected()) { // if user want to generate password
                System.out.println("Password Generator Selcted");

                if (!randomPass.isSelected() && !customPass.isSelected())
                    JOptionPane.showMessageDialog(null, "Please Select a choice", "Alert", JOptionPane.WARNING_MESSAGE);// alert
                                                                                                                        // message
                else if (randomPass.isSelected()) {
                    System.out.println("Randon Password is Selected");
                    String output = "Random Password generated!";
                    JOptionPane.showMessageDialog(null, output);// Done Message
                    password genpass = new password(); // creating an object of userdefine package
                    textField.setText(genpass.generate());// genpass.generate is a function in
                                                          // Password package
                }
                 else if (customPass.isSelected()) { // for custom password
                    System.out.println("Custom Pass is selected");
                    // taking length, and requirements from the user
                    int length = 0;
                    String temp = JOptionPane.showInputDialog(null, "Enter Length for the password(10-20)");
                    if (!checkInput(temp))
                        isValidInput("InValid Integer");
                    else {
                        length = Integer.parseInt(temp);

                        if (length > 20 || length < 10) // if length is invalid
                            JOptionPane.showMessageDialog(null, "Enter a Valid Length", "Alert",
                                    JOptionPane.WARNING_MESSAGE);
                        else {
                            // Checking number of uppercase
                            temp = JOptionPane.showInputDialog(null, "Enter number of UpperCase for the password");
                            int noUpperCase = 0;
                            while (!checkInput(temp)) {
                                isValidInput("InValid Integer");
                                temp = JOptionPane.showInputDialog(null, "Enter number of UpperCase for the password");
                            }
                            noUpperCase = Integer.parseInt(temp);

                            // checking number of lowercase
                            temp = JOptionPane.showInputDialog(null, "Enter number of LowerCase for the password");
                            int nolowerCase = 0;
                            while (!checkInput(temp)) {
                                isValidInput("InValid Integer");
                                temp = JOptionPane.showInputDialog(null, "Enter number of LowerCase for the password");
                            }
                            nolowerCase = Integer.parseInt(temp);

                            // checking number of specialcase
                            temp = JOptionPane.showInputDialog(null, "Enter number of Special Characters");
                            int noSpecialCase = 0;
                            while (!checkInput(temp)) {
                                isValidInput("InValid Integer");
                                temp = JOptionPane.showInputDialog(null, "Enter number of Special Characters");
                            }
                            noSpecialCase = Integer.parseInt(temp);

                            // calculating total by userInput
                            int total = noSpecialCase + noUpperCase + nolowerCase;

                            // calculating Number of numbers for password
                            int number = length - total;

                            if (total > length) // if values are incorrect
                                JOptionPane.showMessageDialog(null, "Credentials went beyond Length", "Alert",
                                        JOptionPane.WARNING_MESSAGE);
                            else {
                                password genpass = new password(); // creating an object of user defined package
                                JOptionPane.showMessageDialog(null, "Password is Generated in TextField!");
                                // displaying password
                                textField.setText(
                                        genpass.generate(length, noUpperCase, nolowerCase, noSpecialCase, number));
                            }
                        }
                    }
                }
            }
        });

        copyButton.addActionListener(e -> {
            System.out.println("CopyButton is pressed");
                StringSelection stringSelection = new StringSelection(textField.getText()); // getting text from textfield
                                                                                            // in stringselectoin
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard(); // getting access to clipboard
                clpbrd.setContents(stringSelection, null); // adding text to clipboard
                JOptionPane.showMessageDialog(null, "Text Copied!"); // done message
        });

        // adding all to password frame
        frameForPass.setLayout(new FlowLayout());
        frameForPass.add(panelForProceed);
        frameForPass.add(boxForRadioOptions);
        frameForPass.add(boxForPass);
        frameForPass.add(backButton);
        frameForPass.add(copyButton);
    }

    // Methods for Files:

    public static void operationOnFile() {

        // designing frame for password
        frameForFile.setTitle("Encrypter/Decrypter");
        frameForFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameForFile.setSize(450, 380);
        frameForFile.setResizable(false);
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

        // Adding back Button
        JButton backButton = new JButton("Home");
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Adding RadioButtons to JPanel to make them together as one
        JPanel panelForRadio = new JPanel();
        panelForRadio.add(radioPDF);
        panelForRadio.add(radioImage);
        panelForRadio.add(radioOther);
        panelForRadio.setPreferredSize(new Dimension(500, 50));

        // Adding Buttons to JPanel to make them together as one
        JPanel panelForButton = new JPanel();
        panelForButton.add(EncrpytButton);
        panelForButton.add(DecryptButton);

        // Adding panel for back button
        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.LINE_AXIS));
        backPanel.add(backButton);
        backPanel.add(Box.createHorizontalGlue());

        // Making buttons Responsive
        EncrpytButton.addActionListener(e -> {
            if (!radioPDF.isSelected() && !radioImage.isSelected() && !radioOther.isSelected()) { // if RadioButton not
                                                                                                  // selected, throw
                                                                                                  // error
                JOptionPane.showMessageDialog(null, "Please Select a choice", "Alert", JOptionPane.WARNING_MESSAGE);// alert
                                                                                                                    // message

            } else {
                System.out.println("Encrpyt-Button Clicked");
                inputFile();
            }
        });

        DecryptButton.addActionListener(e -> {
            if (!radioPDF.isSelected() && !radioImage.isSelected() && !radioOther.isSelected()) { // if RadioButton not
                                                                                                  // selected,
                                                                                                  // throw error
                JOptionPane.showMessageDialog(null, "Please Select a choice", "Alert", JOptionPane.WARNING_MESSAGE);// alert
                                                                                                                    // message
            } else {
                System.out.println("Decrpyt-Button Clicked");
                inputFile();
            }
        });

        backButton.addActionListener(e -> {
            System.out.println("Back-Button Clicked");
            callHomePage();
        });

        // Adding everything to JFrame
        frameForFile.setLayout(new FlowLayout());
        frameForFile.add(panelForButton);
        frameForFile.add(panelForRadio);
        frameForFile.add(backPanel, BorderLayout.SOUTH);
    }

    public static void inputFile() {
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

    public static void callHomePage() {
        main(null);
    }

    public static void isValidInput(String output) {
        JOptionPane.showMessageDialog(null, output, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public static boolean checkInput(String str) {
        if (str == null || str.length() == 0)
            return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}

