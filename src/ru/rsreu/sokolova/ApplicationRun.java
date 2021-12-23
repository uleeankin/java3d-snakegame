package ru.rsreu.sokolova;

import static ru.rsreu.sokolova.GameField.getGameField;

class ApplicationRun {

    public static void main(String[] args) {
        try {
            getGameField();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
