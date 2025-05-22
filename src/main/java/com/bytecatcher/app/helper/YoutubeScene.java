package com.bytecatcher.app.helper;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import com.bytecatcher.app.ThreadCreator;

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
        ThreadCreator youtube = new ThreadCreator(oYoutubeSource, oDownloadLocation,oYtloggingArea, oLoader);
        Thread youtubeThread = youtube.getDownloaderThread();
        youtubeThread.start();
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
