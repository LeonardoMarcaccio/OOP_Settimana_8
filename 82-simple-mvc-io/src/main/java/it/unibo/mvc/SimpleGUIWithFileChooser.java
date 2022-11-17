package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("Exercise 82");

    /**
     * Contructor for the new GUI.
     * @param controller
     */
    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.setContentPane(panel);
        final JTextArea textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
        final JTextField filepath = new JTextField(controller.pathOfFIle());
        filepath.setEditable(false);
        final JButton saveButton = new JButton("SAVE");
        panel.add(saveButton, BorderLayout.SOUTH);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.printStringToFile(textArea.getText());
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(frame, ioException, "Error", JOptionPane.ERROR_MESSAGE);
                    ioException.printStackTrace(); // NOPMD: allowed as this is just an exercise
                }
            }
        });

        final JPanel supportPanel = new JPanel();
        supportPanel.setLayout(new BorderLayout());
        panel.add(supportPanel, BorderLayout.NORTH);
        supportPanel.add(filepath, BorderLayout.CENTER);
        final JButton browse = new JButton("Browse file");
        supportPanel.add(browse, BorderLayout.LINE_END);
        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(controller.getCurrentFile());
                if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrentFile(chooser.getSelectedFile());
                    filepath.setText(controller.pathOfFIle());
                } else {
                    if (chooser.showSaveDialog(frame) != JFileChooser.CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(browse, "Something went wrong");
                    }
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw, sh);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Main Method for our GUI.
     * @param args
     */
    public static void main(final String[] args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }
}
