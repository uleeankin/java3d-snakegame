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

        Appearance appearance = new Appearance();
        ColoringAttributes coloringAttributes = new ColoringAttributes();
        coloringAttributes.setColor(new Color3f(Color.GREEN));
        coloringAttributes.setShadeModel(ColoringAttributes.NICEST);
        appearance.setColoringAttributes(coloringAttributes);

        Font3D font3D = new Font3D(new Font("Courier", Font.BOLD, 1), new FontExtrusion());
        Text3D text3D = new Text3D(font3D, "SNAKE", new Point3f(0f, 0.0f, 0.0f));
        text3D.setAlignment(Text3D.ALIGN_CENTER);
        Shape3D shape3D = new Shape3D();
        shape3D.setGeometry(text3D);
        shape3D.setAppearance(appearance);

        Text3D text3Dgame = new Text3D(font3D, "game", new Point3f(-1.5f, -0.8f, 0.0f));
        text3D.setAlignment(Text3D.ALIGN_CENTER);
        Shape3D shape3Dgame = new Shape3D();
        shape3Dgame.setGeometry(text3Dgame);
        shape3Dgame.setAppearance(appearance);


        Transform3D transform3D = new Transform3D();
        transform3D.setScale(0.4f);
        TransformGroup scaleTransform = new TransformGroup(transform3D);
        scaleTransform.addChild(shape3D);
        scaleTransform.addChild(shape3Dgame);

        //add rotation
        TransformGroup transformGroup =  new TransformGroup();
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        Alpha alpha = new Alpha(-1, 5000);
        RotationInterpolator rotationInterpolator = new RotationInterpolator(alpha, transformGroup);
        rotationInterpolator.setSchedulingBounds(new BoundingSphere());

        transformGroup.addChild(scaleTransform);
        threeDTitle.addChild(transformGroup);
        threeDTitle.addChild(rotationInterpolator);

        return threeDTitle;
    }
}
