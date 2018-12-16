package classes;

import java.io.BufferedWriter;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class Logger {
    private File logFile;
    private int imagesCount = 0;
    private String logDirectory;

    public Logger(String testName){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        logDirectory = "logs/" + testName + " " + dateFormat.format(new Date());
        String testFileName = testName + ".html";
        String filePath = logDirectory + "/" + testFileName;
        new File(logDirectory).mkdir();
        logFile = new File(filePath);
    }

    public void logInfo(String info, File screenshot) throws IOException {
        FileUtils.copyFile(screenshot, new File(logDirectory + "/" + imagesCount + ".png"));
        writeTemplate(info, imagesCount + ".png");
        imagesCount++;
    }

    public void logInfo(String info) throws IOException {
        writeTemplate(info, null);
    }

    public void logError(String error) throws IOException {
        writeTemplate("<b>" + error + "</b>", null);
    }

    public void logError(String error, File screenshot) throws IOException {
        FileUtils.copyFile(screenshot, new File(logDirectory + "/" + imagesCount + ".png"));
        writeTemplate("<b>" + error + "</b>", imagesCount + ".png");
        imagesCount++;
    }

    public void logImportantInfo(String info) throws IOException {
        writeTemplate("<b>" + info + "</b>", null);
    }

    private void writeTemplate(String text,String picName) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("HH.mm/ss");
        BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true));
        bw.write("<div>" + dateFormat.format(new Date()) + " - " + text + "</div>");
        if(picName != null)
            bw.write("<div><a href=\"" + picName + "\">" + picName + "</a></div>");
        bw.write("<div></div>");
        bw.close();
    }
}
