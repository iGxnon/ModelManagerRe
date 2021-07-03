package xyz.lightsky.ModelManagerRe;

import cn.nukkit.entity.data.Skin;
import cn.nukkit.plugin.PluginBase;
import com.google.gson.Gson;
import xyz.lightsky.ModelManagerRe.Beans.Manager;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Main extends PluginBase {

    private static LinkedHashMap<String, Skin> models = new LinkedHashMap<String, Skin>();

    @Override
    public void onEnable() {
        if(getDataFolder().mkdirs() && new File(getDataFolder(), "/Models/").mkdirs()) {
            getLogger().info("首次加载中");
        }
        getLogger().info("正在载入模型...");
        try {
            loadModels();
        } catch (IOException | SkinConfigException e) {
            e.printStackTrace();
        }
        getLogger().info("模型载入完毕...");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static LinkedHashMap<String, Skin> getModels() {
        return models;
    }

    public static Skin getModel(String name) {
        return models.get(name);
    }

    private void loadModels() throws IOException, SkinConfigException {
        File dir = new File(getDataFolder(), "/Models/");
        if (dir.listFiles() == null) return;
        for(String modelName : Objects.requireNonNull(dir.list())){
            File modelDir = null;
            if(!((modelDir = (new File(dir, modelName))).isDirectory())) continue;
            if(Objects.requireNonNull(modelDir.list()).length != 2) continue;
            Skin skin = new Skin();
            skin.setSkinId(modelName);
            for(File data : Objects.requireNonNull(modelDir.listFiles())){
                if(data.getName().endsWith(".json")){
                    String json = new String(Files.readAllBytes(data.toPath()), StandardCharsets.UTF_8);
                    skin.setGeometryData(json);
                    skin.setGeometryName(Objects.requireNonNull(getModelManager(data.toString())).getModelLabel());
                }else if(data.getName().endsWith(".png")){
                    skin.setSkinData(ImageIO.read(data));
                }else{
                    throw new SkinConfigException("model:" + modelName + " load failed! Please confirm the type of config file. Tips: .json and .png");
                }
            }
            models.putIfAbsent(modelName, skin);
            getLogger().info("成功构建 "+modelName);
        }
    }

    private static Manager getModelManager(String jsonPath) throws IOException {
        File json = new File(jsonPath);
        if(!json.exists()) return null;
        String data = new String(Files.readAllBytes(json.toPath()));
        data = "{\"modelsMap\": " + data + "}";
        return (new Gson()).fromJson(data, Manager.class);
    }

    private static Manager getFromJsonString(String json) {
        json = "{\"modelsMap\": " + json + "}";
        return (new Gson()).fromJson(json, Manager.class);
    }

    public static String getJsonString(Manager manager) {
        String json = (new Gson()).toJson(manager);
        json = json.substring(13);
        json = json.substring(0, json.length()-1);
        return json;
    }


}
