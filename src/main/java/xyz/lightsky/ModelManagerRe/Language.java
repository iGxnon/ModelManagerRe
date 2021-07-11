package xyz.lightsky.ModelManagerRe;

import java.util.HashMap;
import java.util.Map;

public class Language {

    private static String lang = "chs";

    private static Map<String, String> langMap = new HashMap<>();

    static void init() {
        ModelManagerRe.getInstance().saveDefaultConfig();
        lang = ModelManagerRe.getInstance().getConfig().getString("language");
        langMap.clear();
        registerMap();
    }

    public static String get(String index) {
        return langMap.get(index);
    }

    private static void registerMap() {
        switch (lang) {
            case "chs":
            default:
                langMap.put("first_load", "首次加载中");
                langMap.put("load_model", "正在载入模型...");
                langMap.put("load_end", "模型载入完毕...");
                langMap.put("load_success", "成功构建 ");
                langMap.put("time_cost", " 用时: ");
                langMap.put("load_success&time_cost", "构建完毕 总用时: ");
                break;
            case "eng":
                langMap.put("first_load", "First loading");
                langMap.put("load_model", "Loading models...");
                langMap.put("load_end", "Models load successfully...");
                langMap.put("load_success", "Successfully loaded ");
                langMap.put("time_cost", " Time cost: ");
                langMap.put("load_success&time_cost", "Load alright! All time cost: ");
                break;
        }
    }

}
