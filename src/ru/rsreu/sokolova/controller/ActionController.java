package ru.rsreu.sokolova.controller;

import ru.rsreu.sokolova.GameField;
import ru.rsreu.sokolova.Motion;
import ru.rsreu.sokolova.Page;
import ru.rsreu.sokolova.models.Mouse;
import ru.rsreu.sokolova.models.Snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ru.rsreu.sokolova.GameField.scene;

public class ActionController implements ActionListener {

    private static final float MIN_X = -0.945f;
    private static final float MAX_X = 0.945f;
    private static final float MIN_Y = -0.91f;
    private static final float MAX_Y = 0.91f;

    public Timer timer = new Timer(500, this);

    @Override
    public void actionPerformed(ActionEvent e) {
        //проверка на столкновение змеи самой с собой
        checkCollision();
        //проверка на столкновение с границами
        checkBounds();
        //проверка на мышь
        eatMouse();
        //движение змейки
        if (MotionController.isRight) {
            Motion.rightMotion();
        }
        if (MotionController.isLeft) {
            Motion.leftMotion();
        }
        if (MotionController.isUp) {
            Motion.upMotion();
        }
        if (MotionController.isDown) {
            Motion.downMotion();
        }
    }

    private void checkCollision() {
        for (int i = 1; i < Snake.snakeDots.size(); i++) {
            if (isYCollision(Snake.snakeDots.get(i).getYPosition()) &&
                    isXCollision(Snake.snakeDots.get(i).getXPosition())) {
                stopGame();
            }
        }
    }

    private boolean isYCollision(float y) {
        if (Snake.snakeDots.get(0).getYPosition() < y) {
            return (Snake.snakeDots.get(0).getYPosition() + Snake.SPHERE_SIZE >= y - 0.035);
        }
        return (Snake.snakeDots.get(0).getYPosition() - Snake.SPHERE_SIZE <= y + 0.035);
    }

    private boolean isXCollision(float x) {
        if (Snake.snakeDots.get(0).getXPosition() < x) {
            return (Snake.snakeDots.get(0).getXPosition() + Snake.SPHERE_SIZE >= x - 0.035);
        }
        return (Snake.snakeDots.get(0).getXPosition() - Snake.SPHERE_SIZE <= x + 0.035);
    }

    private void eatMouse() {
        if (isXMouse() && isYMouse()) {
            Snake.increaseSnake();
            Mouse.setMouseTranslation();
            GameField.score++;
        }
    }

    private boolean isYMouse() {
        return (Snake.snakeDots.get(0).getYPosition() - Snake.SPHERE_SIZE <= Mouse.mouse.getYPosition() + 0.04) &&
                (Snake.snakeDots.get(0).getYPosition() + Snake.SPHERE_SIZE >= Mouse.mouse.getYPosition() - 0.04);
    }

    private boolean isXMouse() {
        return (Snake.snakeDots.get(0).getXPosition() - Snake.SPHERE_SIZE <= Mouse.mouse.getXPosition() + 0.04) &&
                (Snake.snakeDots.get(0).getXPosition() + Snake.SPHERE_SIZE >= Mouse.mouse.getXPosition() - 0.04);
    }

    private void checkBounds() {
        for (int i = 0; i < Snake.snakeDots.size(); i++) {
            if (Snake.snakeDots.get(i).getXPosition() + Snake.SPHERE_SIZE >= MAX_X ||
                    Snake.snakeDots.get(i).getXPosition() + Snake.SPHERE_SIZE <= MIN_X ||
                    Snake.snakeDots.get(i).getYPosition() + Snake.SPHERE_SIZE >= MAX_Y ||
                    Snake.snakeDots.get(i).getYPosition() + Snake.SPHERE_SIZE <= MIN_Y) {

                stopGame();
            }
        }
    }

    private void stopGame() {
        GameField.inGame = false;
        this.timer.stop();
        MotionController.stopTimer();
        if (GameField.score >= GameField.maxScore) {
            GameField.maxScore = GameField.score;
        }
        clearField();
        scene.addChild(Page.getEndGameTitle());
    }

    private void clearField() {
        for (int i = 0; i < Snake.snakeDots.size(); i++) {
            Snake.snakeDots.get(i).getBranchGroup().detach();
        }
        Snake.snakeEyes.get(0).getBranchGroup().detach();
        Snake.snakeEyes.get(1).getBranchGroup().detach();
        Mouse.mouse.getBranchGroup().detach();
    }
}
