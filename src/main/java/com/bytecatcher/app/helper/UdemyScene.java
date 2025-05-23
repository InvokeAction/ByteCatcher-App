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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class UdemyScene {

  @FXML
    private TextField oDownloadLocation;

    @FXML
    private ProgressBar oLoader;

    @FXML
    private Button oUdemyDownload;

    @FXML
    private TextField oUdemyPassword;

    @FXML
    private TextField oUdemySource;

    @FXML
    private TextField oUdemyUserName;

    @FXML
    private TextArea oUdemyloggingArea;

    @FXML
    void onDownload(ActionEvent event) {
         Map<String, String> commandMap = new LinkedHashMap<>();

    // Mandatory values
    commandMap.put("--source", oUdemySource.getText());  // This is your custom key for the final video URL
    String outputTemplate = oDownloadLocation.getText() + "\\%(title)s.%(ext)s";
    commandMap.put("--output", outputTemplate);
    commandMap.put("-f", "bestvideo*+bestaudio/best");
    commandMap.put("--ffmpeg-location", "lib/ffmpeg.exe");

    // Optional: username and password
    String username = oUdemyUserName.getText();
    String password = oUdemyPassword.getText();
    if (username != null && !username.isEmpty()) {
        commandMap.put("--username", username);
    }
    if (password != null && !password.isEmpty()) {
        commandMap.put("--password", password);
    }

    // Optional flags (checkbox style logic can go here)
    // commandMap.put("--no-playlist", null); // Example flag without value

    // Pass to ThreadCreator
    ThreadCreator creator = new ThreadCreator(commandMap, oUdemyloggingArea, oLoader);
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
