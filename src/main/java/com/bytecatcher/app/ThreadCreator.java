package com.bytecatcher.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

class ThreadCreatorClass implements Runnable {
    String videoSource;
    String downloadLocation;
    TextArea loggingArea;

    ThreadCreatorClass(String videoSource, String downloadLocation, TextArea loggingArea) {
        this.videoSource = videoSource;
        this.downloadLocation = downloadLocation;
        this.loggingArea = loggingArea;
    }

    public void run() {
        try {
            String outputTemplate = downloadLocation + "\\%(title)s.%(ext)s";
            ProcessBuilder pb = new ProcessBuilder("yt-dlp.exe", "-f", "bestvideo*+bestaudio/best", "--no-playlist",
                    "--output", outputTemplate, videoSource);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                final String logLine = line;
                Platform.runLater(() -> loggingArea.appendText(logLine + "\n"));
            }
            process.waitFor();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadCreator {
    TextField videoSource;
    TextField downloadLocation;
    TextArea loggingArea;

    public ThreadCreator(TextField videoSource, TextField downloadLocation, TextArea loggingArea) {
        this.videoSource = videoSource;
        this.downloadLocation = downloadLocation;
        this.loggingArea = loggingArea;
    }

    public Thread getDownloaderThread() {
        String url = videoSource.getText();
        String path = downloadLocation.getText();
        ThreadCreatorClass thread = new ThreadCreatorClass(url, path, loggingArea);
        return new Thread(thread);
    }
}
