package ru.rsreu.sokolova.models;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

import static ru.rsreu.sokolova.GameField.scene;

public class Snake {

    private static final float TRANSLATION_Z = 0.0f;
    private static final int SNAKE_LENGTH = 3;
    public static final float SPHERE_SIZE = 0.05f;
    public static final float TRANSLATION_Z_EYE = SPHERE_SIZE * 0.9f;
    private static final float TRANSLATION_Y = 0.0f;
    private static final float TRANSLATION_X_START = SPHERE_SIZE * 3.5f;
    private static final float TRANSLATION_X = SPHERE_SIZE * 1.8f;
    public static final float TRANSLATION = SPHERE_SIZE * 1.8f;

    private static float xTranslation;
    private static float yTranslation;
    private static float zTranslation;
    private static GroupHierarchy groupHierarchy;
    public static float lastXPosition;
    public static float lastYPosition;
    public static List<GroupHierarchy> snakeDots;
    public static List<GroupHierarchy> snakeEyes;

    private Snake() {

    }

    public static void createSnake() {
        Snake snake = new Snake();
        groupHierarchy = new GroupHierarchy();
        snakeDots = new ArrayList<>();
        snakeEyes = new ArrayList<>();
        snake.getSnakeHead(groupHierarchy);
        snakeDots.add(groupHierarchy);
        for (int i = 1; i < SNAKE_LENGTH; i++) {
            groupHierarchy = new GroupHierarchy();
            snake.getSnakeBodyPart(groupHierarchy);
            snakeDots.add(groupHierarchy);
        }

    }

    public static void increaseSnake() {
        GroupHierarchy groupHierarchy = new GroupHierarchy();
        BranchGroup bodyPart = new BranchGroup();
        bodyPart.addChild(getSnakeBodyLight());
        bodyPart.addChild(getSnakeBodyTranslation(lastXPosition, lastYPosition, zTranslation, groupHierarchy));
        groupHierarchy.setBranchGroup(bodyPart);
        xTranslation -= TRANSLATION_X;
        snakeDots.add(groupHierarchy);
        scene.addChild(bodyPart);
    }

    private BranchGroup getSnakeHead(GroupHierarchy groupHierarchy) {
        BranchGroup snakeHead = new BranchGroup();
        yTranslation = TRANSLATION_Y;
        zTranslation = TRANSLATION_Z;
        xTranslation = TRANSLATION_X_START;
        BranchGroup head = new BranchGroup();
        head.addChild(getSnakeBodyLight());
        head.addChild(getSnakeBodyTranslation(xTranslation, yTranslation, zTranslation, groupHierarchy));
        snakeHead.addChild(head);
        snakeHead.addChild(getEyes());
        groupHierarchy.setBranchGroup(snakeHead);
        xTranslation -= TRANSLATION_X;
        return snakeHead;
    }

    public BranchGroup getSnakeBodyPart(GroupHierarchy groupHierarchy) {
        BranchGroup bodyPart = new BranchGroup();
        bodyPart.addChild(getSnakeBodyLight());
        bodyPart.addChild(getSnakeBodyTranslation(xTranslation, yTranslation, zTranslation, groupHierarchy));
        groupHierarchy.setBranchGroup(bodyPart);
        xTranslation -= TRANSLATION_X;
        return bodyPart;
    }

    private static Node getSnakeBodyLight() {
        Color3f lightColor = new Color3f(0.8f, 1.0f, 0.8f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 10.0);
        Vector3f lightDirection = new Vector3f(-0.5f, -0.1f, -0.9f);
        DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
        light.setInfluencingBounds(bounds);
        return light;
    }

    private static TransformGroup getSnakeBodyTranslation(float x, float y, float z, GroupHierarchy groupHierarchy) {
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        Transform3D transform3D = new Transform3D();
        Vector3f vector = new Vector3f(x, y, z);
        transform3D.setTranslation(vector);
        transformGroup.setTransform(transform3D);
        transformGroup.addChild(getSphere(groupHierarchy));
        groupHierarchy.setTransformGroup(transformGroup);
        groupHierarchy.setFirstXPosition(x);
        groupHierarchy.setFirstYPosition(y);
        return transformGroup;

    }

    private static Node getSphere(GroupHierarchy groupHierarchy) {
        return new Sphere(SPHERE_SIZE, Primitive.GENERATE_NORMALS, 70, getBodyAppearance());
    }

    private static Appearance getBodyAppearance() {
        Material surface = new Material();
        surface.setShininess(10.0f);
        surface.setEmissiveColor(0.1f, 0.3f, 0.1f);
        surface.setAmbientColor(0.0f, 0.0f, 0.0f);
        surface.setDiffuseColor(0.0f, 0.6f, 0.0f);
        surface.setSpecularColor(0.05f, 0.05f, 0.05f);
        Appearance appearance = new Appearance();
        appearance.setMaterial(surface);
        return appearance;
    }

    private static Node getEyes() {
        GroupHierarchy groupHierarchy = new GroupHierarchy();
        BranchGroup branchGroup = new BranchGroup();
        branchGroup.addChild(getEyeTranslation(TRANSLATION_X_START - 0.02f, TRANSLATION_Y + 0.01f, TRANSLATION_Z_EYE, groupHierarchy));
        groupHierarchy.setBranchGroup(branchGroup);
        snakeEyes.add(groupHierarchy);
        groupHierarchy = new GroupHierarchy();
        groupHierarchy.setBranchGroup(branchGroup);
        branchGroup.addChild(getEyeTranslation(TRANSLATION_X_START + 0.02f, TRANSLATION_Y + 0.01f, TRANSLATION_Z_EYE, groupHierarchy));
        snakeEyes.add(groupHierarchy);
        branchGroup.addChild(getEyeColour());
        return branchGroup;
    }

    private static Node getEyeColour() {
        Color3f lightColor = new Color3f(0.0f, 0.0f, 0.0f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 10.0);
        Vector3f lightDirection = new Vector3f(-1.0f, -0.5f, -5.0f);
        DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
        light.setInfluencingBounds(bounds);
        return light;
    }

    private static Node getEyeTranslation(float x, float y, float z, GroupHierarchy groupHierarchy) {
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        Transform3D transform3D = new Transform3D();
        Vector3f vector = new Vector3f(x, y, z);
        transform3D.setTranslation(vector);
        transformGroup.setTransform(transform3D);
        transformGroup.addChild(getEye());
        groupHierarchy.setTransformGroup(transformGroup);
        groupHierarchy.setFirstXPosition(x);
        groupHierarchy.setFirstYPosition(y);
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
        surface.setShininess(5.0f);
        surface.setEmissiveColor(0.1f, 0.1f, 0.1f);
        surface.setAmbientColor(0.0f, 0.0f, 0.0f);
        surface.setDiffuseColor(0.0f, 0.0f, 0.0f);
        surface.setSpecularColor(0.01f, 0.01f, 0.01f);
        Appearance appearance = new Appearance();
        appearance.setMaterial(surface);
        return appearance;
    }

}
