package xyz.lightsky.ModelManagerRe.Beans;

import java.util.Map;

public class Manager {
    public Map<String, Model> modelsMap;

    public Manager(Map<String, Model> modelsMap) {
        this.modelsMap = modelsMap;
    }

    public Model getModel(){
        return modelsMap.values().toArray(new Model[0])[0];
    }

    public String getModelLabel(){
        return modelsMap.keySet().toArray(new String[0])[0];
    }
}
