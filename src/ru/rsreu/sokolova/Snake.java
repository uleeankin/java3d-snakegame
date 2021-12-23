package ru.rsreu.sokolova;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.util.List;

public class Snake {

    private static final float TRANSLATION_Z = 0.0f;
    private static final int SNAKE_LENGTH = 3;
    private static final float SPHERE_SIZE = 0.2f;
    private static final float TRANSLATION_Z_EYE = SPHERE_SIZE * 0.9f;
    private static final float TRANSLATION_Y = 0.0f;
    private static final float TRANSLATION_X_START = SPHERE_SIZE * 3.5f;
    private static final float TRANSLATION_X = SPHERE_SIZE * 1.5f;

    private static float xTranslation;
    private static float yTranslation;
    private static float zTranslation;
    public static BranchGroup snake;
    public static List<BranchGroup> snakePoints;
    public static float snakeHeadPosition = TRANSLATION_X_START;

    public static void createSnake() {
        //ToDo: заполнение листа групп и добавление листа в одну группу
        snakePoints.add(getSnakeHead());
        for (int i = 1; i < SNAKE_LENGTH; i++) {
            snakePoints.add(getSnakeBodyPart());
        }
        for (int i = 0; i < snakePoints.size(); i++) {
            snake.addChild(snakePoints.get(i));
        }
    }

    private static BranchGroup getSnakeHead() {
        BranchGroup snakeHead = new BranchGroup();
        yTranslation = TRANSLATION_Y;
        zTranslation = TRANSLATION_Z;
        xTranslation = TRANSLATION_X_START;
        BranchGroup head = new BranchGroup();

        head.addChild(getSnakeBodyTranslation(xTranslation, yTranslation, zTranslation));
        head.addChild(getSnakeBodyLight());
        snakeHead.addChild(head);
        snakeHead.addChild(getEyes());
        xTranslation -= TRANSLATION_X;
        return snakeHead;
    }

    public static BranchGroup getSnakeBodyPart() {
        BranchGroup bodyPart = new BranchGroup();
        bodyPart.addChild(getSnakeBodyTranslation(xTranslation, yTranslation, zTranslation));
        bodyPart.addChild(getSnakeBodyLight());
        xTranslation -= TRANSLATION_X;
        return bodyPart;
    }

    private static Node getSnakeBodyLight() {
        Color3f lightColor = new Color3f(1.0f, 1.5f, 1.0f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 200.0);
        Vector3f lightDirection = new Vector3f(-1.0f, -0.5f, -5.0f);
        DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
        light.setInfluencingBounds(bounds);
        return light;
    }

    private static TransformGroup getSnakeBodyTranslation(float x, float y, float z) {
        TransformGroup transformGroup = new TransformGroup();
        Transform3D transform3D = new Transform3D();
        Vector3f vector = new Vector3f(x, y, z);
        transform3D.setTranslation(vector);
        transformGroup.setTransform(transform3D);
        transformGroup.addChild(getSphere());
        return transformGroup;

    }

    private static Node getSphere() {
        return new Sphere(SPHERE_SIZE, Primitive.GENERATE_NORMALS, 70, getBodyAppearance());
    }


    private static Appearance getBodyAppearance() {
        Material surface = new Material();
        surface.setShininess(10.0f);
        surface.setEmissiveColor(0.1f, 0.1f, 0.1f);
        surface.setAmbientColor(0.0f, 0.0f, 0.0f);
        surface.setDiffuseColor(0.0f, 0.1f, 0.0f);
        surface.setSpecularColor(0.01f, 0.01f, 0.01f);
        Appearance appearance = new Appearance();
        appearance.setMaterial(surface);
        return appearance;
    }

    private static Node getEyes() {
        BranchGroup branchGroup = new BranchGroup();
        branchGroup.addChild(getEyeTranslation(TRANSLATION_X_START - 0.02f, TRANSLATION_Y + 0.01f, TRANSLATION_Z_EYE));
        branchGroup.addChild(getEyeTranslation(TRANSLATION_X_START + 0.02f, TRANSLATION_Y + 0.01f, TRANSLATION_Z_EYE));
        branchGroup.addChild(getEyeColour());
        return branchGroup;
    }

    private static Node getEyeColour() {
        Color3f lightColor = new Color3f(0.0f, 0.0f, 0.0f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 200.0);
        Vector3f lightDirection = new Vector3f(-1.0f, -0.5f, -5.0f);
        DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
        light.setInfluencingBounds(bounds);
        return light;
    }

    private static Node getEyeTranslation(float x, float y, float z) {
        TransformGroup transformGroup = new TransformGroup();
        Transform3D transform3D = new Transform3D();
        Vector3f vector = new Vector3f(x, y, z);
        transform3D.setTranslation(vector);
        transformGroup.setTransform(transform3D);
        transformGroup.addChild(getEye());
        return transformGroup;
    }

    private static Node getEye() {
        TransformGroup transformGroup = new TransformGroup();
        Transform3D transform3D = new Transform3D();
        transform3D.setScale(new Vector3d(1.0f, 1.5f, 1.0f));
        transformGroup.setTransform(transform3D);
        transformGroup.addChild(new Sphere(SPHERE_SIZE * 0.16f, Primitive.GENERATE_NORMALS, 70, getEyeAppearance()));
        return transformGroup;
    }

    private static Appearance getEyeAppearance() {
        Material surface = new Material();
        surface.setShininess(10.0f);
        surface.setEmissiveColor(0.1f, 0.1f, 0.1f);
        surface.setAmbientColor(0.0f, 0.0f, 0.0f);
        surface.setDiffuseColor(0.0f, 0.0f, 0.0f);
        surface.setSpecularColor(0.01f, 0.01f, 0.01f);
        Appearance appearance = new Appearance();
        appearance.setMaterial(surface);
        return appearance;
    }


}
