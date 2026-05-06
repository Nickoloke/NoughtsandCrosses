package uk.ac.soton.comp1206;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class Application extends javafx.application.Application {
    private MenuScreen menuScreen;
    private final IntegerProperty globalScoreX = new SimpleIntegerProperty(0);
    private final IntegerProperty globalScoreO = new SimpleIntegerProperty(0);
    private final StringProperty globalplayerX = new SimpleStringProperty("");
    private final StringProperty globalplayerO = new SimpleStringProperty("");

    public void start(Stage primaryStage) {
        menuScreen = new MenuScreen(
                () -> {
                    globalplayerX.set(menuScreen.getXName());
                    globalplayerO.set(menuScreen.getOName());
                    GameScreen gameScreen = new GameScreen(globalplayerX, globalplayerO
                            , menuScreen.getSize(), globalScoreX, globalScoreO, () -> primaryStage.setScene(menuScreen.getScene()));
                    primaryStage.setScene(gameScreen.getScene());
                },

                () -> {
                    globalplayerX.set(menuScreen.getXName());
                    globalplayerO.set(menuScreen.getOName());
                    ScoreBoardScreen scoreBoardScreen = new ScoreBoardScreen(globalScoreX, globalScoreO,
                            globalplayerX, globalplayerO, () -> primaryStage.setScene(menuScreen.getScene()));
                    primaryStage.setScene(scoreBoardScreen.getScene());
                }
        );

        primaryStage.setScene(menuScreen.getScene());
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

