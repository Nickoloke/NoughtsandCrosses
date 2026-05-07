package me.NoughtsandCrosses.game;

import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.awt.*;
import java.lang.Integer;


public class MenuScreen {
    private final Scene scene;
    private final TextField xName;
    private final TextField oName;
    private final TextField size;

    public MenuScreen(Runnable start, Runnable score) {
        VBox vBox = new VBox(50);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        HBox hBox1 = new HBox(10);
        hBox1.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox(10);
        hBox2.setAlignment(Pos.CENTER);

        Label title = new Label("Noughts and Crosses");
        Label vs = new Label("vs");
        Label sizetitle = new Label("Size: ");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 60));
        vs.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        sizetitle.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        xName = new TextField("");
        oName = new TextField("");
        size = new TextField("3");
        xName.setMaxWidth(200);
        oName.setMaxWidth(200);
        size.setMaxWidth(100);

        Button startButton = new Button("Start Game");
        Button scoreButton = new Button("Score");
        startButton.setMaxWidth(200);
        scoreButton.setMaxWidth(200);

        hBox.getChildren().addAll(xName,vs, oName);
        hBox1.getChildren().addAll(sizetitle,size);
        hBox2.getChildren().addAll(startButton,scoreButton);
        vBox.getChildren().addAll(title, hBox2, hBox, hBox1);
        scene = new Scene(vBox, width, height);

        startButton.setOnAction(e -> {
            if (validate(xName,oName,size)) {
                start.run();
            }
        });
        scoreButton.setOnAction(e -> {
            if (validate(xName,oName,size)) {
                score.run();
            }
        });
    }

    public Scene getScene() {
        return scene;
    }

    public boolean validate(TextField xName, TextField oName, TextField size) {
        return !xName.getText().isEmpty() && !oName.getText().isEmpty()
                && !size.getText().isEmpty() && size.getText().matches("[0-9]+")
                && Integer.parseInt(size.getText()) > 1 && Integer.parseInt(size.getText()) < 10;
    }
    public String getXName() {
        return xName.getText();
    }
    public String getOName() {
        return oName.getText();
    }
    public int getSize() {
        return Integer.parseInt(size.getText());
    }
}
