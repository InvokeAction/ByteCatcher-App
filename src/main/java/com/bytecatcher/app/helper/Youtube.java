package com.bytecatcher.app.helper;
import java.io.IOException;
import javafx.scene.control.TextField;

class YoutubeDownloader implements Runnable {
    String oYoutubeSorce;
    String oDownloadLocation;

    YoutubeDownloader(String oYoutubeSorce, String oDownloadLocation) {
        this.oYoutubeSorce = oYoutubeSorce;
        this.oDownloadLocation = oDownloadLocation;
    }

    public void run() {
        try {
            String outputTemplate = oDownloadLocation + "\\%(title)s.%(ext)s";
            ProcessBuilder pb = new ProcessBuilder("yt-dlp.exe", "--no-playlist", "--output",outputTemplate, oYoutubeSorce);
            pb.redirectErrorStream(true);
            pb.start();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

public class Youtube {
    TextField oYoutubeSorce;
    TextField oDownloadLocation;

    public Youtube(TextField oYoutubeSorce, TextField oDownloadLocation) {
        this.oYoutubeSorce = oYoutubeSorce;
        this.oDownloadLocation = oDownloadLocation;
    }

    public Thread getDownloaderThread() {
        String url = oYoutubeSorce.getText();
        String path = oDownloadLocation.getText();
        YoutubeDownloader ytd = new YoutubeDownloader(url, path);
        return new Thread(ytd);
    }
}

