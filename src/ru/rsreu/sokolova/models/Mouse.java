package ru.rsreu.sokolova.models;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.util.Random;

public class Mouse {

    public static final float SPHERE_SIZE = 0.05f;
    private static final float MIN_X = -0.945f;
    private static final float MAX_X = 0.945f;
    private static final float MIN_Y = -0.91f;
    private static final float MAX_Y = 0.91f;

    public static GroupHierarchy mouse = new GroupHierarchy();

    public Mouse() {

    }

    public static BranchGroup getMouse() {
        BranchGroup branchGroup = new BranchGroup();
        branchGroup.setCapability(BranchGroup.ALLOW_DETACH);
        branchGroup.addChild(getMouseTransform());
        branchGroup.addChild(getMouseLight());
        mouse.setBranchGroup(branchGroup);
        return branchGroup;
    }

    public static void setMouseTranslation() {
        float x = getRandomXPosition();
        float y = getRandomYPosition();
        Transform3D transform3D = new Transform3D();
        Vector3f vector = new Vector3f(x, y, 0f);
        transform3D.setTranslation(vector);
        Mouse.mouse.getTransformGroup().setTransform(transform3D);
        Mouse.mouse.setFirstXPosition(x);
        Mouse.mouse.setFirstYPosition(y);
    }

    private static TransformGroup getMouseTransform() {
        TransformGroup transformGroup = new TransformGroup();
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        transformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);

        Transform3D transform3D = new Transform3D();

        float x = getRandomXPosition();
        float y = getRandomYPosition();

        transform3D.setTranslation(new Vector3f(x, y, 0f));
        transformGroup.setTransform(transform3D);
        transformGroup.addChild(getSphere());

        mouse.setTransformGroup(transformGroup);
        mouse.setXPosition(x);
        mouse.setYPosition(y);
        return transformGroup;
    }

    private static DirectionalLight getMouseLight() {
        Color3f lightColor = new Color3f(0.8f, 0.8f, 0.8f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 200.0);
        Vector3f lightDirection = new Vector3f(-1.0f, -0.5f, -8.0f);
        DirectionalLight light = new DirectionalLight(lightColor, lightDirection);
        light.setInfluencingBounds(bounds);
        return light;
    }

    public static float getRandomXPosition() {
        return new Random().nextFloat() * (MAX_X - MIN_X) + MIN_X;
    }

    public static float getRandomYPosition() {
        return new Random().nextFloat() * (MAX_Y - MIN_Y) + MIN_Y;
    }

    private static Node getSphere() {
        return new Sphere(SPHERE_SIZE, Primitive.GENERATE_NORMALS, 70, getMouseAppearance());
    }

    private static Appearance getMouseAppearance() {
        Material surface = new Material();
        surface.setShininess(10.0f);
        surface.setEmissiveColor(0.1f, 0.1f, 0.1f);
        surface.setAmbientColor(0.0f, 0.0f, 0.0f);
        surface.setDiffuseColor(0.3f, 0.3f, 0.3f);
        surface.setSpecularColor(0.01f, 0.01f, 0.01f);
        Appearance appearance = new Appearance();
        appearance.setMaterial(surface);
        return appearance;
    }
}
