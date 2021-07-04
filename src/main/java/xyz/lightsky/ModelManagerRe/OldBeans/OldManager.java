package xyz.lightsky.ModelManagerRe.OldBeans;

import xyz.lightsky.ModelManagerRe.Manager;

import java.util.Map;

public class OldManager implements Manager {
    public Map<String, Model> modelsMap;

    public OldManager(Map<String, Model> modelsMap) {
        this.modelsMap = modelsMap;
    }

    public Model getMainModel(){
        return modelsMap.values().toArray(new Model[0])[0];
    }

    public String getMainIdentifier(){
        return modelsMap.keySet().toArray(new String[0])[0];
    }
}
