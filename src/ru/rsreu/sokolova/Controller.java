package ru.rsreu.sokolova;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static ru.rsreu.sokolova.GameField.scene;

public class Controller implements KeyListener {

    public Controller() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                StartPage.threeDTitle.detach();
                scene.addChild(Snake.getSnakeBodyPart());
                break;
            case KeyEvent.VK_PAGE_UP:
                break;
            case KeyEvent.VK_PAGE_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_RIGHT:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
