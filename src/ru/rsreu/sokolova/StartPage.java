package ru.rsreu.sokolova;

import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import java.awt.*;


public class StartPage {

    public static BranchGroup threeDTitle = new BranchGroup();

    public StartPage() {

    }
    public static BranchGroup getThreeDTitle() {

        threeDTitle.setCapability(BranchGroup.ALLOW_DETACH);
        //add start text
        Background background = new Background(new Color3f(Color.BLACK));
        background.setApplicationBounds(new BoundingSphere());
        threeDTitle.addChild(background);

        Shape3D snakeText = getText("SNAKE", new Point3f(0f, 0f, 0f), Color.GREEN);

        Shape3D gameText = getText("game", new Point3f(-0.4f, -0.8f, 0f), Color.GREEN);

        Shape3D nextText = getText("Press ENTER for continue...", new Point3f(0f, -15.0f, 0f), Color.WHITE);

        Transform3D transformGreen = new Transform3D();
        transformGreen.setScale(0.4f);

        TransformGroup scaleRotateTransform = new TransformGroup(transformGreen);
        scaleRotateTransform.addChild(snakeText);
        scaleRotateTransform.addChild(gameText);

        Transform3D transformWhite = new Transform3D();
        transformWhite.setScale(0.05f);
        TransformGroup scaleTransform = new TransformGroup(transformWhite);
        scaleTransform.addChild(nextText);

        //add rotation
        TransformGroup rotateGroup =  new TransformGroup();
        rotateGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        Alpha alpha = new Alpha(-1, 5000);
        RotationInterpolator rotationInterpolator = new RotationInterpolator(alpha, rotateGroup);
        rotationInterpolator.setSchedulingBounds(new BoundingSphere());

        rotateGroup.addChild(scaleRotateTransform);
        threeDTitle.addChild(scaleTransform);
        threeDTitle.addChild(rotateGroup);
        threeDTitle.addChild(rotationInterpolator);

        return threeDTitle;
    }

    private static Shape3D getText(String text, Point3f point, Color color) {
        Font3D font3D = new Font3D(new Font("Courier", Font.BOLD, 1), new FontExtrusion());
        Text3D text3D = new Text3D(font3D, text, point);
        text3D.setAlignment(Text3D.ALIGN_CENTER);
        Shape3D shape3D = new Shape3D();
        shape3D.setGeometry(text3D);
        shape3D.setAppearance(getAppearance(color));
        return shape3D;
    }

    private static Appearance getAppearance(Color color) {
        Appearance appearance = new Appearance();
        ColoringAttributes coloringAttributes = new ColoringAttributes();
        coloringAttributes.setColor(new Color3f(color));
        coloringAttributes.setShadeModel(ColoringAttributes.NICEST);
        appearance.setColoringAttributes(coloringAttributes);
        return appearance;
    }
}
