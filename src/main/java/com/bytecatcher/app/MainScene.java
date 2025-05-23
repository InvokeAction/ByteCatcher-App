package com.bytecatcher.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainScene {
    @FXML
    private AnchorPane oBaseAnchor;

    @FXML
    private Button oUdemyButton;

    @FXML
    private Button oYoutubeButton;

    @FXML
    void onUdemyBase(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bytecatcher/app/view/UdemyScene.fxml"));
            AnchorPane youtubeFragment = loader.load();
            oBaseAnchor.getChildren().clear();
            oBaseAnchor.getChildren().add(youtubeFragment);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onYoutubeBase(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bytecatcher/app/view/YoutubeScene.fxml"));
            AnchorPane youtubeFragment = loader.load();
            oBaseAnchor.getChildren().clear();
            oBaseAnchor.getChildren().add(youtubeFragment);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}