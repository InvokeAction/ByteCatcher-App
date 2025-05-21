package com.bytecatcher.app.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

class YoutubeDownloader implements Runnable {
    String oYoutubeSorce;
    String oDownloadLocation;
    TextArea oYtloggingArea;

    YoutubeDownloader(String oYoutubeSorce, String oDownloadLocation, TextArea oYtloggingArea) {
        this.oYoutubeSorce = oYoutubeSorce;
        this.oDownloadLocation = oDownloadLocation;
        this.oYtloggingArea = oYtloggingArea;
    }

    public void run() {
        try {
            String outputTemplate = oDownloadLocation + "\\%(title)s.%(ext)s";
            ProcessBuilder pb = new ProcessBuilder("yt-dlp.exe","-f", "bestvideo*+bestaudio/best","--no-playlist", "--output", outputTemplate,
                    oYoutubeSorce);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                final String logLine = line;
                Platform.runLater(() -> oYtloggingArea.appendText(logLine + "\n"));
            }
            process.waitFor();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Youtube {
    TextField oYoutubeSorce;
    TextField oDownloadLocation;
    TextArea oYtloggingArea;

    public Youtube(TextField oYoutubeSorce, TextField oDownloadLocation, TextArea oYtloggingArea) {
        this.oYoutubeSorce = oYoutubeSorce;
        this.oDownloadLocation = oDownloadLocation;
        this.oYtloggingArea = oYtloggingArea;
    }

    public Thread getDownloaderThread() {
        String url = oYoutubeSorce.getText();
        String path = oDownloadLocation.getText();
        YoutubeDownloader ytd = new YoutubeDownloader(url, path, oYtloggingArea);
        return new Thread(ytd);
    }
}
