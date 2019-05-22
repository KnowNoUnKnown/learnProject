package com.oracle.xing.javafx.controller;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by liuyong
 * 2019-01-19  12-03
 */

public class TextCroller implements Initializable {

    @FXML
    private TextArea areaText;

    @FXML
    private TextField titleText;

    @FXML
    private Button confirmButton ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleText.setText("This is title!");
        areaText.setText("related 英[rɪˈleɪtɪd]\n" +
                "美[rɪˈletɪd]\n" +
                "adj. 有关系的; 叙述的;\n" +
                "vt. (relate过去式和过去分词) 有关，叙述;\n" +
                "[例句]He professed a distaste for everything related to money.\n" +
                "他声称厌恶与金钱有关的一切。");
        confirmButton.setOnAction(e ->{
            titleText.setText("按钮被点击");
            areaText.setText("按钮被点击");
        });
    }
}
