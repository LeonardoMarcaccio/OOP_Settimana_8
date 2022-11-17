package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("Exercise 82");

    /**
     * Constructor that creates the GUI for our program. 
     * @param controller
     */
    public SimpleGUI(final Controller controller) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.setContentPane(panel);
        final JTextArea textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Main method for our GUI.
     * @param args
     */
    public static void main(final String[] args) {
        final SimpleGUI gui = new SimpleGUI(new Controller());
        gui.display();
    }
}
