package me.NoughtsandCrosses.game;

import javafx.beans.property.*;


public class GameBoard {
    private final int size;
    private final String[][] board;
    private final StringProperty currentPlayer = new SimpleStringProperty("X");
    private final IntegerProperty scoreX;
    private final IntegerProperty scoreO;

    public GameBoard(int size, StringProperty globalplayerX, StringProperty globalplayerO,
                     IntegerProperty scoreX, IntegerProperty scoreO) {
        this.size = size;
        StringProperty playerXName = new SimpleStringProperty();
        playerXName.set(globalplayerX.get());
        StringProperty playerOName = new SimpleStringProperty();
        playerOName.set(globalplayerO.get());
        board = new String[size][size];
        this.scoreX = scoreX;
        this.scoreO = scoreO;
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "";
            }
        }
    }

    public void switchPlayer() {
        if (currentPlayer.get().equals("X")) {
            currentPlayer.set("O");
        } else {
            currentPlayer.set("X");
        }
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col].isEmpty()) {
            board[row][col] = currentPlayer.get();
            return true;
        }
        return false;
    }

    public String checkWinner() {
        for (int i = 0; i < size; i++) {
            if (!board[i][0].isEmpty()) {
                boolean win = true;
                for (int j = 1; j < size; j++) {
                    if (!board[i][j].equals(board[i][0])) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    if (board[i][0].equals("X")) scoreX.set(scoreX.get() + 1);
                    if (board[i][0].equals("O")) scoreO.set(scoreO.get() + 1);
                    return board[i][0];

                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (!board[0][i].isEmpty()) {
                boolean win = true;
                for (int j = 1; j < size; j++) {
                    if (!board[j][i].equals(board[0][i])) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    if (board[0][i].equals("X")) scoreX.set(scoreX.get() + 1);
                    if (board[0][i].equals("O")) scoreO.set(scoreO.get() + 1);
                    return board[0][i];
                }
            }
        }
        if (!board[0][0].isEmpty()) {
            boolean win = true;
            for (int i = 1; i < size; i++) {
                if (!board[i][i].equals(board[0][0])) {
                    win = false;
                    break;
                }
            }
            if (win) {
                if (board[0][0].equals("X")) scoreX.set(scoreX.get() + 1);
                if (board[0][0].equals("O")) scoreO.set(scoreO.get() + 1);
                return board[0][0];
            }
        }

        if (!board[0][size - 1].isEmpty()) {
            boolean win = true;
            for (int i = 1; i < size; i++) {
                if (!board[i][size - 1 - i].equals(board[0][size - 1])) {
                    win = false;
                    break;
                }
            }
            if (win) {
                if (board[0][size - 1].equals("X")) scoreX.set(scoreX.get() + 1);
                if (board[0][size - 1].equals("O")) scoreO.set(scoreO.get() + 1);
                return board[0][size - 1];
            }
        }
        for  (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].isEmpty()) {
                    return "";
                }
            }
        }
        return "draw";
    }
    public String getCurrentPlayer() {
        return currentPlayer.get();
    }

    public StringProperty currentPlayerProperty() {
        return currentPlayer;
    }
}
