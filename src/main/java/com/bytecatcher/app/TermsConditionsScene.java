package com.bytecatcher.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TermsConditionsScene {
    @FXML
    private Button oProceedTC;

    @FXML
    private CheckBox oTCCheckBox;

    @FXML
    private AnchorPane oYoutubeBase;

    private boolean isTermsAccepted;

    @FXML
    void onProceedTC(ActionEvent event) {
        if (!isTermsAccepted) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Terms & Conditions");
            alert.setHeaderText(null);
            alert.setContentText("Accept the Terms & Conditions before proceeding");
            alert.showAndWait();
            return;
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bytecatcher/app/view/MainScene.fxml"));
            try {
                Parent root = loader.load();
                 Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                 stage.setScene(new Scene(root));
                 stage.setTitle("ByteCatcher");
                 stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
           
        }
    }

    @FXML
    void onTCCheckBox(ActionEvent event) {
        isTermsAccepted = oTCCheckBox.isSelected();
    }
}
