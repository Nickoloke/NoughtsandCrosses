package uk.ac.soton.comp1206;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import java.awt.*;

public class ScoreBoardScreen {
    private final Scene scene;

    public ScoreBoardScreen(IntegerProperty globalscoreX, IntegerProperty globalscoreO,
                            StringProperty globalplayerX, StringProperty globalplayerO, Runnable back) {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        VBox vBox = new VBox(50);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(40);
        hBox.setAlignment(Pos.CENTER);
        HBox hBox2 = new HBox(40);
        hBox2.setAlignment(Pos.CENTER);

        Label scoreLabel = new Label("Score:");
        scoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 100));
        Label playerx = new Label(globalplayerX.get() + " :");
        playerx.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        Label playero = new Label(globalplayerO.get() + " :");
        playero.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        Label scoreX = new Label(String.valueOf(globalscoreX.get()));
        scoreX.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        Label scoreO = new Label(String.valueOf(globalscoreO.get()));
        scoreO.setFont(Font.font("Verdana", FontWeight.BOLD, 50));

        Button backbutton = new Button("Back");
        backbutton.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        backbutton.setOnAction(e -> back.run());

        vBox.getChildren().addAll(scoreLabel,hBox,backbutton);
        hBox.getChildren().addAll(playerx,scoreX,playero,scoreO);

        scene = new Scene(vBox, width, height);
    }

    public Scene getScene() {
        return scene;
    }
}
