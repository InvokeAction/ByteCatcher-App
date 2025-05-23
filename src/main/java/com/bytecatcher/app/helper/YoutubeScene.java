package com.bytecatcher.app.helper;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class YoutubeScene {

    @FXML
    private TextField oDownloadLocation;

    @FXML
    private ProgressBar oLoader;

    @FXML
    private Button oYoutubeDownload;

    @FXML
    private AnchorPane oYoutubeFragment;

    @FXML
    private TextField oYoutubeSource;

    @FXML
    private TextArea oYtloggingArea;

    @FXML
    void onDownload(ActionEvent event) {
        Map<String, String> commandMap = new LinkedHashMap<>();
        commandMap.put("--source", oYoutubeSource.getText()); // URL will be added last
        String outputTemplate = oDownloadLocation.getText() + "\\%(title)s.%(ext)s";
        commandMap.put("--output", outputTemplate);
        commandMap.put("-f", "bestvideo*+bestaudio/best");
        commandMap.put("--ffmpeg-location", "lib/ffmpeg.exe");
        ThreadCreator creator = new ThreadCreator(commandMap, oYtloggingArea, oLoader);
        creator.getDownloaderThread().start();
    }

    @FXML
    void onDownloadLocation(MouseEvent event) {
        Stage stage = (Stage) oDownloadLocation.getScene().getWindow();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Download Location");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedDir = directoryChooser.showDialog(stage);
        if (selectedDir != null) {
            oDownloadLocation.setText(selectedDir.getAbsolutePath());
        } else {
            System.out.println("No directory selected default path will be choosen");
        }
    }
}
