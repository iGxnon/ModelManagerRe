package xyz.lightsky.ModelManagerRe.NewBeans;

import java.util.List;

public class Bone  {

    public String name;
    public List<Double> pivot;
    public List<Cube> cubes;

    public Bone(String name, List<Double> pivot, List<Cube> cubes) {
        this.name = name;
        this.pivot = pivot;
        this.cubes = cubes;
    }

    public String getName() {
        return name;
    }

    public List<Cube> getCubes() {
        return cubes;
    }

    public List<Double> getPivot() {
        return pivot;
    }

}
