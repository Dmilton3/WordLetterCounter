import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Dewey Milton on 11/5/2016.
 * Name: MainFrame Class
 * Description: Creates the main frame of the program
 * able to choose multiple files, using multi threads
 * to count the number of words and letters
 * Calls subpanels to list the results, and prints to a file
 */
public class mainFrame {

    private JFrame frame;
    private JTextArea inputScreen;
    private JPanel textScreen;
    private JPanel fileScreen;
    private JPanel addPanel;
    private JPanel submitPanel;
    private JPanel menuPanel;
    private JButton submitB;
    private JButton addB;
    private JScrollPane scrollP;
    private JFileChooser fileC;
    private int fileOp;
    private File[] files;
    private File newFile;

    /*
       Name: mainFrame Constructor
       @Param None
     */
    public mainFrame(){


       // this.newFile = new File("H://Java/MiltonFile.txt");

        this.newFile = new File("MiltonFile.txt");

        try{
            BufferedWriter eraser = new BufferedWriter(new FileWriter(newFile));
            eraser.write("");
            eraser.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        frame = new JFrame("WordLetterCounter");
        frame.setSize(600,600);
        frame.setBackground(new Color(11, 15, 255));

        fileScreen = new JPanel(new BorderLayout());

        fileScreen.setSize(400,400);
        fileScreen.setPreferredSize(new Dimension(450,610));
        fileScreen.setBackground(new Color(11, 15, 255));
        fileScreen.setVisible(true);
        fileScreen.setLayout(new BorderLayout());
        frame.getContentPane().add(fileScreen, BorderLayout.NORTH);


        textScreen = new JPanel();
        textScreen.setVisible(true);
        textScreen.setPreferredSize(new Dimension(400,400));
        textScreen.setBackground(new Color(5,30, 5));
        fileScreen.add(textScreen);



        inputScreen = new JTextArea(50,50);
        inputScreen.setEditable(false);
        inputScreen.setLineWrap(true);
        inputScreen.setSize(300,300);
        inputScreen.setPreferredSize(new Dimension(300,350));
        inputScreen.setBackground(new Color(255, 251, 198));
        inputScreen.setVisible(true);
        scrollP = new JScrollPane(inputScreen);
        textScreen.add(scrollP);

        menuPanel = new JPanel(new BorderLayout());
        menuPanel.setPreferredSize(new Dimension(600, 100));
        menuPanel.setBackground(new Color(71, 76, 223));
        menuPanel.setVisible(true);
        frame.getContentPane().add(menuPanel, BorderLayout.SOUTH);

        submitPanel = new JPanel(new FlowLayout());
        submitPanel.setBackground(new Color(71, 76, 223));
        submitPanel.setSize(new Dimension(300, 100));
        submitPanel.setVisible(true);
        menuPanel.add(submitPanel, BorderLayout.EAST);

        submitB = new JButton("Submit");
        submitB.setSize(new Dimension(50,50));
        submitB.setVisible(true);
        submitB.setBackground(new Color(217, 223, 218));
        submitPanel.add(submitB);

        addPanel = new JPanel((new FlowLayout()));
        addPanel.setBackground(new Color(71, 76, 223));
        addPanel.setSize(new Dimension(300,100));
        addPanel.setVisible(true);
        menuPanel.add(addPanel, BorderLayout.WEST);

        addB = new JButton("Add Files");
        addB.setSize(new Dimension(50,50));
        addB.setVisible(true);
        addB.setBackground(new Color(217, 223, 218));
        addPanel.add(addB);



        addB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputScreen.setText("");
                fileC = new JFileChooser();

                fileC.setCurrentDirectory(new java.io.File("."));
                fileC.setDialogTitle("Select Files");
                fileC.setMultiSelectionEnabled(true);
                fileOp = fileC.showOpenDialog(frame);

                if(fileOp == JFileChooser.APPROVE_OPTION){
                    files = fileC.getSelectedFiles();

                    for(int pos = 0;pos < files.length; pos++){
                        inputScreen.append(files[pos].getName() + "\n");
                    }
                }
            }
        });

        submitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (files != null) {
                        for (int pos = 0; pos < files.length; pos++) {
                            getResults(files[pos], pos);
                        }

                }else
                    inputScreen.setText("Invalid option, please select files!");

            }


        });
    }


    /*
       Name: getResults
       @Param files takes in an array of Files
       @Param _ID int to ID thread processing counts
       Creates multiple threads to calculate results
     */
    public void getResults(File files, int _ID){

        CountThread r = null;
        try {
            r = new CountThread(files, _ID, newFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(r != null) {
            Thread t = new Thread(r);
            t.start();
        }
        else
            inputScreen.setText("Invalid File Option");
    }

    /*
       @Name: run()
       @Descr: sets mainFrame Visible
    */
    public void run(){
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
