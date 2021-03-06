package Assigment1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;

/**
 * The GUI for assignment 1, DualThreads
 */
public class GUIAssignment1 {

    /**
     * These are the components you need to handle.
     * You have to add listeners and/or code
     */
    private JFrame frame;                   // The Assignment1 window
    private JButton btnDisplay;             // Start thread moving display
    private JButton btnDStop;               // Stop moving display thread
    private JButton btnTriangle;            // Start moving graphics thread
    private JButton btnTStop;               // Stop moving graphics thread
    private JButton btnOpen;                // Open audio file
    private JButton btnPlay;                // Start playing audio
    private JButton btnStop;                // Stop playing
    private JButton btnGo;                  // Start game catch me
    private JPanel pnlMove;                 // The panel to move display in
    private JTrianglePanel pnlRotate;
    private JPanel pnlGame;                 // The panel to play in
    private JLabel lblPlaying;              // Playing text
    private JLabel lblAudio;                // Audio file
    private JLabel lblRandomText;           // Display text on random position
    private JTextArea txtHits;              // Dispaly hits
    private JComboBox cmbSkill;             // Skill combo box, needs to be filled in
    private ButtonListener buttonListener;
    private Controller controller;
    private JFileChooser fileChooser;

    /**
     * Constructor
     */
    public GUIAssignment1(Controller controller) {
        this.controller = controller;
    }

    /**
     * Starts the application
     */
    public void start() {
        frame = new JFrame();
        frame.setBounds(0, 0, 819, 438);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setTitle("Multiple Thread Demonstrator");
        initializeGUI();                    // Fill in components
        frame.setVisible(true);
        frame.setResizable(false);            // Prevent user from change size
        frame.setLocationRelativeTo(null);    // Start middle screen
    }

    /**
     * Sets up the GUI with components
     */
    private void initializeGUI() {
        // The music player outer panel
        JPanel pnlSound = new JPanel();
        Border b1 = BorderFactory.createTitledBorder("Music Player");
        pnlSound.setBorder(b1);
        pnlSound.setBounds(12, 12, 450, 100);
        pnlSound.setLayout(null);

        fileChooser = new JFileChooser();
        buttonListener = new ButtonListener();


        // Add labels and buttons to this panel
        lblPlaying = new JLabel("Now Playing: ");    // Needs to be alteraed
        lblPlaying.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblPlaying.setBounds(128, 16, 300, 20);
        pnlSound.add(lblPlaying);
        JLabel lbl1 = new JLabel("Loaded Audio File: ");
        lbl1.setBounds(10, 44, 130, 13);
        pnlSound.add(lbl1);
        lblAudio = new JLabel("...");                // Needs to be altered
        lblAudio.setBounds(115, 44, 300, 13);
        pnlSound.add(lblAudio);
        btnOpen = new JButton("Open");
        btnOpen.setBounds(6, 71, 75, 23);
        btnOpen.addActionListener(buttonListener);
        pnlSound.add(btnOpen);
        btnPlay = new JButton("Play");
        btnPlay.setBounds(88, 71, 75, 23);
        btnPlay.addActionListener(buttonListener);
        pnlSound.add(btnPlay);
        btnStop = new JButton("Stop");
        btnStop.setBounds(169, 71, 75, 23);
        btnStop.addActionListener(buttonListener);
        pnlSound.add(btnStop);
        frame.add(pnlSound);

        // The moving display outer panel
        JPanel pnlDisplay = new JPanel();
        Border b2 = BorderFactory.createTitledBorder("Display Thread");
        pnlDisplay.setBorder(b2);
        pnlDisplay.setBounds(12, 118, 222, 269);
        pnlDisplay.setLayout(null);

        // Add buttons and drawing panel to this panel
        lblRandomText = new JLabel();
        lblRandomText.setBackground(Color.WHITE);
        btnDisplay = new JButton("Start Display");
        btnDisplay.setBounds(10, 226, 121, 23);
        btnDisplay.addActionListener(buttonListener);
        pnlDisplay.add(btnDisplay);
        btnDStop = new JButton("Stop");
        btnDStop.setBounds(135, 226, 75, 23);
        btnDStop.addActionListener(buttonListener);
        pnlDisplay.add(btnDStop);
        pnlMove = new JPanel();
        pnlMove.setBounds(10, 19, 200, 200);
        Border b21 = BorderFactory.createLineBorder(Color.black);
        pnlMove.setBorder(b21);
        pnlDisplay.add(pnlMove);
        pnlMove.add(lblRandomText);
        frame.add(pnlDisplay);

        // The moving graphics outer panel
        JPanel pnlTriangle = new JPanel();
        Border b3 = BorderFactory.createTitledBorder("Triangle Thread");
        pnlTriangle.setBorder(b3);
        pnlTriangle.setBounds(240, 118, 222, 269);
        pnlTriangle.setLayout(null);

        // Add buttons and drawing panel to this panel
        btnTriangle = new JButton("Start Rotate");
        btnTriangle.setBounds(10, 226, 121, 23);
        btnTriangle.addActionListener(buttonListener);
        pnlTriangle.add(btnTriangle);
        btnTStop = new JButton("Stop");
        btnTStop.setBounds(135, 226, 75, 23);
        btnTStop.addActionListener(buttonListener);
        pnlTriangle.add(btnTStop);
        pnlRotate = new JTrianglePanel();
        pnlRotate.setBounds(10, 19, 200, 200);
        Border b31 = BorderFactory.createLineBorder(Color.black);
        pnlRotate.setBorder(b31);
        pnlTriangle.add(pnlRotate);

        // Add this to main window
        frame.add(pnlTriangle);

        // The game outer panel
        JPanel pnlCatchme = new JPanel();
        Border b4 = BorderFactory.createTitledBorder("Catch Me");
        pnlCatchme.setBorder(b4);
        pnlCatchme.setBounds(468, 12, 323, 375);
        pnlCatchme.setLayout(null);

        // Add controls to this panel
        JLabel lblSkill = new JLabel("Skill:");
        lblSkill.setBounds(26, 20, 50, 13);
        pnlCatchme.add(lblSkill);
        JLabel lblInfo = new JLabel("Hit Image with Mouse");
        lblInfo.setBounds(107, 13, 150, 13);
        pnlCatchme.add(lblInfo);
        JLabel lblHits = new JLabel("Hits:");
        lblHits.setBounds(240, 20, 50, 13);
        pnlCatchme.add(lblHits);
        cmbSkill = new JComboBox();            // Need to be filled in with data
        cmbSkill.setBounds(19, 41, 61, 23);
        pnlCatchme.add(cmbSkill);
        btnGo = new JButton("GO");
        btnGo.setBounds(129, 41, 75, 23);
        btnGo.addActionListener(buttonListener);
        pnlCatchme.add(btnGo);
        txtHits = new JTextArea();            // Needs to be updated
        txtHits.setBounds(233, 41, 71, 23);
        Border b40 = BorderFactory.createLineBorder(Color.black);
        txtHits.setBorder(b40);
        pnlCatchme.add(txtHits);
        pnlGame = new JPanel();
        pnlGame.setBounds(19, 71, 285, 283);
        Border b41 = BorderFactory.createLineBorder(Color.black);
        pnlGame.setBorder(b41);
        pnlCatchme.add(pnlGame);
        frame.add(pnlCatchme);
    }


    /**
     * Places a text string at the position from the
     * x and y coordinates.
     *
     * @param text The text that will be displayed.
     * @param x    Position on the x-axis.
     * @param y    Position on the y-axis.
     */
    public void placeRandomText(String text, int x, int y) {
        lblRandomText.setText(text);
        lblRandomText.setLocation(x, y);
    }

    /**
     * Rotates an triangle on the selected panel.
     *
     * @param angle The angle that the triangle should rotate to.
     */
    public void rotateTriangle(double angle) {
        pnlRotate.setAngle(angle);
        pnlRotate.repaint();
    }

    /**
     * Returns the dimension on Random text label.
     *
     * @return The dimension.
     */
    public Dimension getRandomTextDimension() {
        return lblRandomText.getSize();
    }

    /**
     * Returns the dimension on the Move panel.
     *
     * @return The dimension.
     */
    public Dimension getDisplayDimension() {
        return pnlMove.getSize();
    }

    /**
     * Private class that listen to all the buttons on the gui.
     */
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //Get the selected button
            Object btnClicked = e.getSource();

            //Finds the selected button then calls a method in the controller
            if (btnClicked.equals(btnDisplay)) {
                controller.startDisplay();
            } else if (btnClicked.equals(btnDStop)) {
                controller.stopDisplay();
            } else if (btnClicked.equals(btnGo)) {
                System.out.println("btnGo");
            } else if (btnClicked.equals(btnTriangle)) {
                controller.startRotate();
            } else if (btnClicked.equals(btnTStop)) {
                controller.stopRotate();
            }
        }
    }

    /**
     * Inner class that lets an polygon rotate around
     * a point.
     */
    private class JTrianglePanel extends JPanel {

        private Polygon polygon;
        private int[] xpos = {75, 25, 125};
        private int[] ypos = {25, 75, 75};
        private int height, length;
        private double angle;


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Creates an polygon then rotate it
            polygon = new Polygon(xpos, ypos, 3);
            this.length = (xpos[2] + xpos[1]) / 2;
            this.height = (ypos[0] + ypos[1]) / 2;
            Graphics2D g2 = (Graphics2D) g;
            AffineTransform affineTransform = g2.getTransform();
            g2.setTransform(AffineTransform.getRotateInstance(Math.toRadians(angle), length, height));
            g2.setColor(Color.black);
            g2.drawPolygon(polygon);
            g2.setTransform(affineTransform);
        }

        public void setAngle(double angle) {
            this.angle = angle;
        }
    }
}
