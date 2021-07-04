package xyz.lightsky.ModelManagerRe.NewBeans;

import java.util.List;

public class Model {

    public Description description;
    public List<Bone> bones;

    public Model(Description description, List<Bone> bones) {
        this.description = description;
        this.bones = bones;
    }

    public Description getDescription() {
        return description;
    }

    public List<Bone> getBones() {
        return bones;
    }

}
