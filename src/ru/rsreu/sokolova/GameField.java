package ru.rsreu.sokolova;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private static final int SIZE = 800;
    public static JFrame frame = new JFrame("SnakeGame");
    //private Timer timer;

    public static void getGameField() throws InterruptedException {
        System.setProperty("sun.awt.noerasebackground", "true");
        frame.setLayout(new BorderLayout());
        StartPage panel = new StartPage();
        frame.add(panel);
        frame.setSize(SIZE, SIZE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /*Thread.sleep(5000);
        StartPage.threeDTitle.detach();

        StartPage.scene.addChild(Snake.getSnakeBodyPart());*/


//ToDo: события нажатия на клавиши (плюс ограничения нельзя в обратную сторону без поворота)

    }


}
