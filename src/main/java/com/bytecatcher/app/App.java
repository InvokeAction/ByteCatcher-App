package com.bytecatcher.app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bytecatcher/app/view/MainScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ByteCatcher - Smart Downloader");
        primaryStage.setResizable(false);
        primaryStage.show();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
