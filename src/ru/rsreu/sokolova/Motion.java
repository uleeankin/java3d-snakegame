package ru.rsreu.sokolova;

import ru.rsreu.sokolova.models.Snake;

public class Motion {

    public Motion() {

    }

    public static void leftMotion() {
        Motion.snakeBodyMotion();
        Snake.snakeDots.get(0).setXPosition(Snake.snakeDots.get(0).getXPosition() - Snake.TRANSLATION);
        Motion.eyesMotion(Snake.snakeDots.get(0).getXPosition(), Snake.snakeDots.get(0).getYPosition());
    }

    public static void rightMotion() {
        Motion.snakeBodyMotion();
        Snake.snakeDots.get(0).setXPosition(Snake.snakeDots.get(0).getXPosition() + Snake.TRANSLATION);
        Motion.eyesMotion(Snake.snakeDots.get(0).getXPosition(), Snake.snakeDots.get(0).getYPosition());
    }

    public static void upMotion() {
        Motion.snakeBodyMotion();
        Snake.snakeDots.get(0).setYPosition(Snake.snakeDots.get(0).getYPosition() + Snake.TRANSLATION);
        Motion.eyesMotion(Snake.snakeDots.get(0).getXPosition(), Snake.snakeDots.get(0).getYPosition());
    }

    public static void downMotion() {
        Motion.snakeBodyMotion();
        Snake.snakeDots.get(0).setYPosition(Snake.snakeDots.get(0).getYPosition() - Snake.TRANSLATION);
        Motion.eyesMotion(Snake.snakeDots.get(0).getXPosition(), Snake.snakeDots.get(0).getYPosition());
    }

    private static void snakeBodyMotion() {
        Snake.lastXPosition = Snake.snakeDots.get(Snake.snakeDots.size() - 1).getXPosition();
        Snake.lastYPosition = Snake.snakeDots.get(Snake.snakeDots.size() - 1).getYPosition();
        for (int i = Snake.snakeDots.size() - 1; i > 0; i--) {
            Snake.snakeDots.get(i).setXPosition(Snake.snakeDots.get(i - 1).getXPosition());
            Snake.snakeDots.get(i).setYPosition(Snake.snakeDots.get(i - 1).getYPosition());
        }
    }

    private static void eyesMotion(float x, float y) {
        Snake.snakeEyes.get(0).setXPosition(x - 0.02f);
        Snake.snakeEyes.get(0).setYPosition(y + 0.01f);
        Snake.snakeEyes.get(0).setZPosition();
        Snake.snakeEyes.get(1).setXPosition(x + 0.02f);
        Snake.snakeEyes.get(1).setYPosition(y + 0.01f);
        Snake.snakeEyes.get(1).setZPosition();
    }

}
