package ru.rsreu.sokolova.controller;

import ru.rsreu.sokolova.GameField;
import ru.rsreu.sokolova.Motion;
import ru.rsreu.sokolova.models.Mouse;
import ru.rsreu.sokolova.models.Snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionController implements ActionListener {

    private static final float MIN_X = -0.945f;
    private static final float MAX_X = 0.945f;
    private static final float MIN_Y = -0.91f;
    private static final float MAX_Y = 0.91f;

    public Timer timer = new Timer(500, this);

    @Override
    public void actionPerformed(ActionEvent e) {
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

    private void eatMouse() {
        if (isXMouse() && isYMouse()) {
            Snake.increaseSnake();
            Mouse.setMouseTranslation();
            GameField.score++;
        }
    }

    private boolean isYMouse() {
        return (Snake.snakeDots.get(0).getYPosition() - Snake.SPHERE_SIZE <= Mouse.mouse.getYPosition() + Mouse.SPHERE_SIZE + 0.01) &&
                (Snake.snakeDots.get(0).getYPosition() + Snake.SPHERE_SIZE >= Mouse.mouse.getYPosition() - Mouse.SPHERE_SIZE - 0.01);
    }

    private boolean isXMouse() {
        return (Snake.snakeDots.get(0).getXPosition() - Snake.SPHERE_SIZE <= Mouse.mouse.getXPosition() + Mouse.SPHERE_SIZE + 0.01) &&
                (Snake.snakeDots.get(0).getXPosition() + Snake.SPHERE_SIZE >= Mouse.mouse.getXPosition() - Mouse.SPHERE_SIZE - 0.01);
    }

    private void checkBounds() {
        for (int i = 0; i < Snake.snakeDots.size(); i++) {
            if (Snake.snakeDots.get(i).getXPosition() + Snake.SPHERE_SIZE >= MAX_X ||
                    Snake.snakeDots.get(i).getXPosition() + Snake.SPHERE_SIZE <= MIN_X ||
                    Snake.snakeDots.get(i).getYPosition() + Snake.SPHERE_SIZE >= MAX_Y ||
                    Snake.snakeDots.get(i).getYPosition() + Snake.SPHERE_SIZE <= MIN_Y) {

                GameField.inGame = false;
                this.timer.stop();
                //вывести текст с баллами
                //перезапуск и выход
            }
        }
    }
}
