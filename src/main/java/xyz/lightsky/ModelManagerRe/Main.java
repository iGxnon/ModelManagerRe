package xyz.lightsky.ModelManagerRe;

import cn.nukkit.entity.data.Skin;
import cn.nukkit.plugin.PluginBase;
import com.google.gson.Gson;
import xyz.lightsky.ModelManagerRe.NewBeans.NewManager;
import xyz.lightsky.ModelManagerRe.OldBeans.OldManager;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Main extends PluginBase {

    private static LinkedHashMap<String, Skin> models = new LinkedHashMap<String, Skin>();

    @Override
    public void onEnable() {
        if(getDataFolder().mkdirs()) {
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

    /**
     *
     * @return Models list
     */
    public static LinkedHashMap<String, Skin> getModels() {
        return models;
    }

    /**
     *
     * @param name index
     * @return Model
     */
    public static Skin getModel(String name) throws SkinNotFoundException {
        if(models.get(name) == null) throw new SkinNotFoundException();
        return models.get(name);
    }

    private void loadModels() throws IOException, SkinConfigException {
        double start = System.currentTimeMillis();
        File dir = getDataFolder();
        if (dir.listFiles() == null) return;
        for(String modelName : Objects.requireNonNull(dir.list())){
            double startOne = System.currentTimeMillis();
            File modelDir = null;
            if(!((modelDir = (new File(dir, modelName))).isDirectory())) continue;
            if(Objects.requireNonNull(modelDir.list()).length != 2) continue;
            Skin skin = new Skin();
            skin.setSkinId(modelName);
            for(File data : Objects.requireNonNull(modelDir.listFiles())){
                if(data.getName().endsWith(".json")){
                    String json = new String(Files.readAllBytes(data.toPath()), StandardCharsets.UTF_8);
                    skin.setGeometryData(json);
                    skin.setGeometryName(Objects.requireNonNull(getModelManager(data.toString())).getMainIdentifier());
                }else if(data.getName().endsWith(".png")){
                    skin.setSkinData(ImageIO.read(data));
                }else{
                    throw new SkinConfigException("model:" + modelName + " load failed! Please confirm the type of config file. Tips: .json and .png");
                }
            }
            models.putIfAbsent(modelName, skin);
            double endOne = System.currentTimeMillis();
            getLogger().info("成功构建 " + modelName + " 用时: " + (endOne - startOne) + "ms");
        }
        double end = System.currentTimeMillis();
        getLogger().info("构建完毕 总用时: " + (end - start) + "ms");
    }

    /**
     *
     * @param jsonPath provide a path
     * @return NewManager or OldManager
     * @throws IOException
     */
    public static Manager getModelManager(String jsonPath) throws IOException {
        File json = new File(jsonPath);
        if(!json.exists()) return null;
        String data = new String(Files.readAllBytes(json.toPath()));
        if(data.contains("1.12.0")){
            return (new Gson()).fromJson(data, (Type) NewManager.class);
        }
        data = "{\"modelsMap\": " + data + "}";
        return (new Gson()).fromJson(data, OldManager.class);
    }


    /**
     *
     * @param json json data
     * @return NewManager or OldManager
     */
    public static Manager getFromJsonString(String json) {
        if(json.contains("1.12.0")){
            return (new Gson()).fromJson(json, (Type) NewManager.class);
        }
        json = "{\"modelsMap\": " + json + "}";
        return (new Gson()).fromJson(json, OldManager.class);
    }


    /**
     *
     * @param manager NewManager or OldManager
     * @return json data
     */
    public static String getJsonString(Manager manager) {
        String json = (new Gson()).toJson(manager);
        if(json.contains("1.12.0")) return json;
        json = json.substring(13);
        json = json.substring(0, json.length()-1);
        return json;
    }




}
