import java.io.*;
import java.util.Scanner;

/**
 * Created by Dewey Milton on 11/5/2016.
 * Description: Threads that calculate the number
 * of letters and words in a file
 * Stores contents of file into subpanels and saves
 * the results into another file
 */
public class CountThread implements Runnable {

    private int lCount;
    private int wCount;
    private boolean newWord;
    private Scanner scan;
    private String tmpString;
    private String contents;
    private int threadID;
    private File newFile;
    private File file;
    private String text;
    private boolean done;

    public CountThread(File _file, int _ID, File newFile) throws FileNotFoundException {

        this.newFile = newFile;
        this.done = false;
        this.text = "";
        this.file = _file;
        this.contents = "";
        this.threadID = _ID;
        this.lCount = 0;
        this.wCount = 0;
        this.newWord = true;
        this.scan = new Scanner(file);
        tmpString = "";
    }

    /*
       Runnable method for thread
     */
    public void run(){

        while(scan.hasNext()){

            tmpString = scan.nextLine().toLowerCase();


            for(int pos = 0; pos < tmpString.length(); pos++){
                if(newWord && tmpString.charAt(pos) >= 97 && tmpString.charAt(pos) <= 122){
                    newWord = false;
                    wCount++;
                    lCount++;
                }
                else if(tmpString.charAt(pos) >= 97 && tmpString.charAt(pos) <= 122){
                    this.lCount++;
                }
                else if(tmpString.charAt(pos) == ' '){
                    newWord = true;
                }

                if(pos + 1 == tmpString.length()){
                    newWord = true;
                    contents += tmpString + "\n";
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        this.text = "Thread " + threadID + ": the file " + file.getAbsolutePath() +
                " has " + wCount + " words and " + lCount + " letters.";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
            writer.write(this.text);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        resultPane results = new resultPane(lCount, wCount, threadID, contents);

    }

}
