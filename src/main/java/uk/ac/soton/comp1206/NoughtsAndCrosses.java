package uk.ac.soton.comp1206;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.*;

public class NoughtsAndCrosses extends Application {

    int clickCount = 0;
    boolean xTurn = true;
    boolean gameWon = false;

    GridPane gridPane = new GridPane();
    Button[][] buttons = new Button[3][3];

    Label statusLabel = new Label("X's Turn");

    @Override
    public void start(Stage stage) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                Button button = new Button(" ");
                button.setMinSize(140, 140);
                buttons[i][j] = button;
                button.setOnAction(new ButtonClickHandler(i, j));
                gridPane.add(button, i, j);
            }
        }

        HBox topBar = new HBox(50);
        Button resetButton = new Button("Reset");
        resetButton.setMinSize(100, 40);
        resetButton.setTranslateY(25);
        resetButton.setOnAction(e -> resetBoard());

        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(resetButton);
        gridPane.setAlignment(Pos.CENTER);
        statusLabel.setAlignment(Pos.CENTER);


        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(gridPane);
        root.setBottom(statusLabel);

        Scene scene = new Scene(root, 550, 550);
        stage.setTitle("Noughts and Crosses");
        stage.setScene(scene);
        stage.show();
    }

    private void resetBoard() {
        clickCount = 0;
        xTurn = true;
        gameWon = false;
        statusLabel.setText("X's Turn");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
            }
        }
    }

    private void checkWinner() {
        String winner = "";

        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals(" ") &&
                    buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText())) {
                winner = buttons[i][0].getText();
            }
        }

        for (int j = 0; j < 3; j++) {
            if (!buttons[0][j].getText().equals(" ") &&
                    buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                    buttons[0][j].getText().equals(buttons[2][j].getText())) {
                winner = buttons[0][j].getText();
            }
        }

        if (!buttons[0][0].getText().equals(" ") &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText())) {
            winner = buttons[0][0].getText();
        }
        if (!buttons[0][2].getText().equals(" ") &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText())) {
            winner = buttons[0][2].getText();
        }

        if (!winner.equals("")) {
            gameWon = true;
            statusLabel.setText(winner + " Wins");
        } else if (clickCount == 9) {
            gameWon = true;
            statusLabel.setText("It's a Draw");
        }
    }

    private class ButtonClickHandler implements EventHandler<ActionEvent> {
        private final int row;
        private final int col;

        public ButtonClickHandler(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            Button clickedButton = buttons[row][col];
            clickedButton.setFont(new Font(30));
            if (!gameWon && clickedButton.getText().equals(" ")) {

                if (xTurn) {
                    clickedButton.setText("X");
                } else {
                    clickedButton.setText("O");
                }

                clickCount++;
                xTurn = !xTurn;

                if (xTurn) {
                    statusLabel.setText("X's Turn");
                } else {
                    statusLabel.setText("O's Turn");
                }
                
                checkWinner();
            }
        }
    }
}