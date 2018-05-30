import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Dewey Milton on 11/5/2016.
 * Description: Result panes created by threads
 * Displays Contents of a file, the number of letters and words
 */
public class resultPane {

    private JFrame frame;
    private JPanel screen;
    private JTextArea input;
    private JScrollPane scrollP;
    private JPanel borderPane;
    private JPanel menuBar;
    private JTextField lCount;
    private JTextField wCount;
    private int numWords;
    private int numLetter;
    private String contents;
    private int x;
    private int y;
    private Random rand;

    private int threadId;


    public resultPane(int numL, int numW, int _ID, String _content){


        rand = new Random();
        x = rand.nextInt(1200);
        y = rand.nextInt(400);

        contents = _content;
        numLetter = numL;
        numWords = numW;
        threadId = _ID;

        frame = new JFrame("Thread ID : " + threadId);
        frame.setSize(new Dimension(400,400));
        frame.setBackground(new Color(43, 110, 255));
        frame.setLocation(x,y);

        borderPane = new JPanel(new FlowLayout());
        borderPane.setBackground(new Color(43, 110, 255));
        borderPane.setPreferredSize(new Dimension(400,300));
        borderPane.setVisible(true);
        frame.getContentPane().add(borderPane);

        screen = new JPanel(new FlowLayout());
        screen.setPreferredSize(new Dimension(375, 375));
        screen.setBackground(new Color(238, 187, 122));
        screen.setVisible(true);
        borderPane.add(screen);

        input = new JTextArea(contents);
        input.setBackground(new Color(238, 63, 198));
        input.setPreferredSize(new Dimension(350,350));
        input.setLineWrap(true);
        input.setEditable(false);
        input.setVisible(true);
        scrollP = new JScrollPane(input);
        screen.add(scrollP);


        menuBar = new JPanel(new FlowLayout());
        menuBar.setBackground(new Color(255,240,30));
        menuBar.setPreferredSize(new Dimension(400,75));
        menuBar.setVisible(true);
        frame.getContentPane().add(menuBar, BorderLayout.SOUTH);

        lCount = new JTextField("Letter Count: " + numLetter);
        lCount.setEditable(false);
        lCount.setPreferredSize(new Dimension(200, 25));
        lCount.setBackground(new Color(236, 248, 255));
        lCount.setVisible(true);
        menuBar.add(lCount);

        wCount = new JTextField("Word Count: " + numWords);
        wCount.setEditable(false);
        wCount.setPreferredSize(new Dimension(200, 25));
        wCount.setBackground(new Color(236,248,255));
        wCount.setVisible(true);
        menuBar.add(wCount);

        frame.setVisible(true);

    }

}
