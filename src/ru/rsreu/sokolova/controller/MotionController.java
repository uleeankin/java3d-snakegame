package ru.rsreu.sokolova.controller;

import ru.rsreu.sokolova.GameField;
import ru.rsreu.sokolova.Motion;
import ru.rsreu.sokolova.StartPage;
import ru.rsreu.sokolova.models.Mouse;
import ru.rsreu.sokolova.models.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static ru.rsreu.sokolova.GameField.scene;

public class MotionController implements KeyListener {

    public static boolean isLeft;
    public static boolean isRight;
    public static boolean isUp;
    public static boolean isDown;
    private static boolean isWorkedTimer = false;

    public MotionController() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if (!GameField.inGame) {
                    StartPage.threeDTitle.detach();
                    Snake.createSnake();
                    for (int i = 0; i < Snake.snakeDots.size(); i++) {
                        scene.addChild(Snake.snakeDots.get(i).getBranchGroup());
                    }
                    scene.addChild(Mouse.getMouse());
                    GameField.inGame = true;
                }
                break;
            case KeyEvent.VK_UP:
                startTimer();
                isLeft = false;
                isRight = false;
                isDown = false;
                isUp = true;
                break;
            case KeyEvent.VK_DOWN:
                startTimer();
                isLeft = false;
                isRight = false;
                isDown = true;
                isUp = false;
                break;
            case KeyEvent.VK_LEFT:
                startTimer();
                isLeft = true;
                isRight = false;
                isDown = false;
                isUp = false;
                break;
            case KeyEvent.VK_RIGHT:
                startTimer();
                isRight = true;
                isLeft = false;
                isDown = false;
                isUp = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private static void startTimer() {
        if (!isWorkedTimer) {
            ActionController actionController = new ActionController();
            actionController.timer.start();
            isWorkedTimer = true;
        }
    }
}
