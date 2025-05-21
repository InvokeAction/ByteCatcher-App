package com.bytecatcher.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.File;
import com.bytecatcher.app.helper.Youtube;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Button oYoutubeDownload;
    @FXML
    private TextField oYoutubeSource;
    @FXML
    private TextField oDownloadLocation;

    @FXML
    void onDownload(ActionEvent e) {
        Youtube yt = new Youtube(oYoutubeSource, oDownloadLocation);
        Thread yThread = yt.getDownloaderThread();
        yThread.start();

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