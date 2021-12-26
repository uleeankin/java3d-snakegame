package ru.rsreu.sokolova.models;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class GroupHierarchy {

    private BranchGroup branchGroup;
    private TransformGroup transformGroup;
    private float xPosition;
    private float yPosition;

    public GroupHierarchy() {

    }

    public BranchGroup getBranchGroup() {
        return this.branchGroup;
    }

    public void setBranchGroup(BranchGroup branchGroup) {
        this.branchGroup = branchGroup;
    }

    public TransformGroup getTransformGroup() {
        return this.transformGroup;
    }

    public void setTransformGroup(TransformGroup transformGroup) {
        this.transformGroup = transformGroup;
    }

    public float getXPosition() {
        return this.xPosition;
    }

    public void setFirstXPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public void setXPosition(float xPosition) {
        Transform3D transform3D = new Transform3D();
        Vector3f vector = new Vector3f(xPosition, this.yPosition, 0f);
        transform3D.setTranslation(vector);
        this.transformGroup.setTransform(transform3D);
        this.xPosition = xPosition;
    }

    public float getYPosition() {
        return this.yPosition;
    }

    public void setFirstYPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public void setYPosition(float yPosition) {
        Transform3D transform3D = new Transform3D();
        Vector3f vector = new Vector3f(this.xPosition, yPosition, 0f);
        transform3D.setTranslation(vector);
        this.transformGroup.setTransform(transform3D);
        this.yPosition = yPosition;
    }

    public void setZPosition() {
        Transform3D transform3D = new Transform3D();
        Vector3f vector = new Vector3f(this.xPosition, this.yPosition, Snake.TRANSLATION_Z_EYE);
        transform3D.setTranslation(vector);
        this.transformGroup.setTransform(transform3D);
    }
}
