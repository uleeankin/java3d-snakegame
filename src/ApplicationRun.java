import com.sun.j3d.utils.universe.SimpleUniverse;

public class ApplicationRun {

    public void main() {
        SimpleUniverse universe = new SimpleUniverse();
        universe.getViewingPlatform().setNominalViewingTransform();
    }
}
