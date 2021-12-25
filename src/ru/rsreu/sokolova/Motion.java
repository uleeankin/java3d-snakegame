package ru.rsreu.sokolova;

import ru.rsreu.sokolova.models.Snake;

import javax.swing.*;

public class Motion {

    public Motion() {

    }

    public static void leftMotion() {
        Motion.snakeBodyMotion();
        Snake.snakeDots.get(0).setXPosition(Snake.snakeDots.get(0).getXPosition() - Snake.TRANSLATION);
    }

    public static void rightMotion() {
        Motion.snakeBodyMotion();
        Snake.snakeDots.get(0).setXPosition(Snake.snakeDots.get(0).getXPosition() + Snake.TRANSLATION);
    }

    public static void upMotion() {
        Motion.snakeBodyMotion();
        Snake.snakeDots.get(0).setYPosition(Snake.snakeDots.get(0).getYPosition() + Snake.TRANSLATION);
    }

    public static void downMotion() {
        Motion.snakeBodyMotion();
        Snake.snakeDots.get(0).setYPosition(Snake.snakeDots.get(0).getYPosition() - Snake.TRANSLATION);
    }

    private static void snakeBodyMotion() {
        for (int i = Snake.snakeDots.size() - 1; i > 0; i--) {
            Snake.snakeDots.get(i).setXPosition(Snake.snakeDots.get(i - 1).getXPosition());
            Snake.snakeDots.get(i).setYPosition(Snake.snakeDots.get(i - 1).getYPosition());
        }
    }
}
