package com.bytecatcher.app.helper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

class ThreadCreatorClass implements Runnable {
    Map<String, String> commandMap;
    TextArea loggingArea;
    ProgressBar loader;
    static String ytDlpPath = "lib/yt-dlp.exe";

    ThreadCreatorClass(Map<String, String> commandMap, TextArea loggingArea, ProgressBar loader) {
        this.commandMap = commandMap;
        this.loggingArea = loggingArea;
        this.loader = loader;
    }

    public void run() {
        Platform.runLater(() -> loader.setProgress(ProgressBar.INDETERMINATE_PROGRESS));

        try {
            List<String> command = new ArrayList<>();
            command.add(ytDlpPath);

            for (Map.Entry<String, String> entry : commandMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                if (key.equals("--source")) continue; // defer video URL to last

                command.add(key);
                if (value != null && !value.isEmpty()) {
                    command.add(value);
                }
            }

            // Add the URL at the end
            command.add(commandMap.get("--source"));
            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();
            BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            while ((line = stdOut.readLine()) != null) {
                final String logLine = line;
                Platform.runLater(() -> loggingArea.appendText(logLine + "\n"));
            }
            while ((line = stdErr.readLine()) != null) {
                final String errLine = line;
                Platform.runLater(() -> loggingArea.appendText("[ERROR] " + errLine + "\n"));
            }

            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Platform.runLater(() -> loader.setProgress(0));
        }
    }
}

public class ThreadCreator {
    Map<String, String> commandMap;
    TextArea loggingArea;
    ProgressBar oLoader;

    public ThreadCreator(Map<String, String> commandMap, TextArea loggingArea, ProgressBar oLoader) {
        this.commandMap = commandMap;
        this.loggingArea = loggingArea;
        this.oLoader = oLoader;
    }

    public Thread getDownloaderThread() {
        return new Thread(new ThreadCreatorClass(commandMap, loggingArea, oLoader));
    }
}


