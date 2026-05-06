package uk.ac.soton.comp1206;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;

import java.awt.*;


public class GameScreen {
    private final GameBoard gameBoard;
    private final GridPane gridPane;
    private final Button[][] buttons;
    private final Scene scene;
    private final Label turnName;
    private final Label winlabel;
    private final StringProperty globalplayerX;
    private final StringProperty globalplayerO;


    public GameScreen(StringProperty globalplayerX, StringProperty globalplayerO, int size, IntegerProperty scoreX, IntegerProperty scoreO,
                      Runnable back) {
        this.globalplayerX = globalplayerX;
        this.globalplayerO = globalplayerO;
        this.gameBoard = new GameBoard(size, globalplayerX, globalplayerO,scoreX,scoreO);
        this.buttons = new Button[size][size];
        this.gridPane = new GridPane();
        turnName = new Label(globalplayerX.get() + "'s Turn");
        turnName.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        gameBoard.currentPlayerProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.equals("X")) {
                turnName.setText(globalplayerX.get() + "'s Turn");
            } else {
                turnName.setText(globalplayerO.get() + "'s Turn");
            }
        });
        createButtons();
        gridPane.setAlignment(Pos.CENTER);
        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        backButton.setOnAction((ActionEvent e) -> back.run());
        winlabel = new Label("");
        winlabel.setAlignment(Pos.CENTER);
        winlabel.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        winlabel.setVisible(false);

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(winlabel, turnName, gridPane, backButton);
        scene = new Scene(vBox, width, height);
    }

    public void handleMove(Button button, int i, int j) {
        if (gameBoard.makeMove(i, j)){
            button.setText(gameBoard.getCurrentPlayer());
            button.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, 30));
            String winner = gameBoard.checkWinner();
            if (!winner.isEmpty()) {
                switch (winner) {
                    case "X" -> winner(globalplayerX);
                    case "O" -> winner(globalplayerO);
                    case "draw" -> draw();
                }
            } else {
                gameBoard.switchPlayer();
            }
        }
    }

    public void createButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                Button button = new Button(" ");
                button.setMinSize(140, 140);
                buttons[i][j] = button;
                final int finali = i;
                final int finalj = j;
                button.setOnAction(e -> handleMove(button, finali,finalj));
                gridPane.add(button, i, j);
            }
        }
    }
    public void winner(StringProperty player) {

        for (Button[] button : buttons) {
            for (Button value : button) {
                value.setDisable(true);
            }
        }
        winlabel.setVisible(true);
        winlabel.setText(player.get() + " is the winner!");
    }

    public void draw() {
        for (Button[] button : buttons) {
            for (Button value : button) {
                value.setDisable(true);
            }
        }
        winlabel.setVisible(true);
        winlabel.setText("The game is a draw!");
    }
    public Scene getScene() {
        return scene;
    }
}
