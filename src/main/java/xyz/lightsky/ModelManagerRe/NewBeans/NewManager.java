package xyz.lightsky.ModelManagerRe.NewBeans;

import com.google.gson.annotations.SerializedName;
import xyz.lightsky.ModelManagerRe.Manager;

import java.util.List;

public class NewManager implements Manager{

    public String format_version = "1.12.0";

    @SerializedName("minecraft:geometry")
    public List<Model> models;

    public NewManager(List<Model> models) {
        this.models = models;
    }

    public Model getMainModel() {
        return models.get(0);
    }

    public Model getIndexModel(int index) {
        return models.get(index);
    }

    /**
     *
     * @return GeometryName
     */
    public String getMainIdentifier() {
        return getMainModel().getDescription().identifier;
    }


}
