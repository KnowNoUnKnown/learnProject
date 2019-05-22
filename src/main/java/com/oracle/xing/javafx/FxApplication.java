package com.oracle.xing.javafx;



/**
 * Created by liuyong
 * 2019-01-19  11-22
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            Parent root = FXMLLoader.load(getClass()
                    .getResource("/fx/application.fxml"));
            primaryStage.setTitle("FX Application");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
