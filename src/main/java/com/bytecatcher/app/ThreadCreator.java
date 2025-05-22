package com.bytecatcher.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

class ThreadCreatorClass implements Runnable {
    String videoSource;
    String downloadLocation;
    TextArea loggingArea;
    static String ytDlpPath = "lib/yt-dlp.exe";
    static String ffmpegPath = "lib/ffmpeg.exe";
    ProgressBar oLoader;

    ThreadCreatorClass(String videoSource, String downloadLocation, TextArea loggingArea, ProgressBar oLoader) {
        this.videoSource = videoSource;
        this.downloadLocation = downloadLocation;
        this.loggingArea = loggingArea;
        this.oLoader = oLoader;
    }

    public void run() {
        Platform.runLater(() -> {
            oLoader.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS); // or 0.0
        });

        try {
            String outputTemplate = downloadLocation + "\\%(title)s.%(ext)s";
            ProcessBuilder pb = new ProcessBuilder(ytDlpPath,
                    "-f", "bestvideo*+bestaudio/best",
                    "--ffmpeg-location", ffmpegPath,
                    "--output", outputTemplate,
                    videoSource);
            pb.redirectErrorStream(false);
            Process process = pb.start();

            BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            while ((line = stdOut.readLine()) != null) {
                String logLine = line;
                Platform.runLater(() -> loggingArea.appendText(logLine + "\n"));
            }

            while ((line = stdErr.readLine()) != null) {
                String errLine = line;
                Platform.runLater(() -> loggingArea.appendText("[ERROR] " + errLine + "\n"));
            }

            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            Platform.runLater(() -> {
                oLoader.setProgress(0);
            });
        }
    }

}

public class ThreadCreator {
    TextField videoSource;
    TextField downloadLocation;
    TextArea loggingArea;

    ProgressBar oLoader;

    public ThreadCreator(TextField videoSource, TextField downloadLocation, TextArea loggingArea, ProgressBar oLoader) {
        this.videoSource = videoSource;
        this.downloadLocation = downloadLocation;
        this.loggingArea = loggingArea;
        this.oLoader = oLoader;
    }

    public Thread getDownloaderThread() {
        String url = videoSource.getText();
        String path = downloadLocation.getText();
        ThreadCreatorClass thread = new ThreadCreatorClass(url, path, loggingArea, oLoader);
        return new Thread(thread);
    }
}
