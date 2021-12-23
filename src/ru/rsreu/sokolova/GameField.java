package ru.rsreu.sokolova;

import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private static final int SIZE = 800;
    public static JFrame frame = new JFrame("SnakeGame");
    public static SimpleUniverse simpleUniverse;
    public static BranchGroup scene = new BranchGroup();
    //private Timer timer;

    public GameField() {
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);
        setLayout(new BorderLayout());
        add(canvas);

        simpleUniverse = new SimpleUniverse(canvas);
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        scene.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        scene.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        scene.addChild(StartPage.getThreeDTitle());
        //scene.addChild(Snake.getSnakeBodyPart());
        scene.compile();
        simpleUniverse.addBranchGraph(scene);
    }

    public static void getGameField() throws InterruptedException {
        System.setProperty("sun.awt.noerasebackground", "true");
        frame.setLayout(new BorderLayout());
        GameField panel = new GameField();
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
