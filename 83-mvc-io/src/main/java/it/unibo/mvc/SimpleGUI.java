package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final Controller controller = new SimpleController();

    /**
     * Class Constructor.
     */
    public SimpleGUI() {
        final JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        panel.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
        final JButton print = new JButton("Print");
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setString(textField.getText());
                controller.printString();
            }
        });

        final JButton history = new JButton("Show History");
        history.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                textArea.setText(String.join("\n", controller.getHistory()));
            }
        });

        final JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.LINE_AXIS));
        panel.add(box, BorderLayout.SOUTH);
        box.add(print);
        box.add(history);

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
     * Main method used to launch the GUI.
     * @param args
     */
    public static void main(final String[] args) {
        final SimpleGUI gui = new SimpleGUI();
        gui.display();
    }
}
