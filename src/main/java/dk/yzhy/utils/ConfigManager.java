package dk.yzhy.utils;

import dk.yzhy.Butik;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ConfigManager {
    static HashMap<String, String[]> messages;
    public static void loadALL() {
        messages = new HashMap<>();
        for (String path : Butik.mainConfigYML.getKeys(true)) {
            if (!Butik.mainConfigYML.isConfigurationSection(path)) {
                if(Butik.mainConfigYML.getStringList(path) != null && Butik.mainConfigYML.isList(path)) {
                    List<String> stringList = Butik.mainConfigYML.getStringList(path);
                    messages.put(path, stringList.toArray(new String[0]));
                    continue;
                }

                if(Butik.mainConfigYML.getString(path) != null) {
                    List<String> stringList = Collections.singletonList(Butik.mainConfigYML.getString(path));
                    messages.put(path, stringList.toArray(new String[0]));
                }
            }
        }
    }
    public static String[] get(String path) {
        if(messages.containsKey(path)){
            return messages.get(path);
        }
        return new String[] { "" };
    }

    public static String getString(String path) {
        if(messages.containsKey(path)){
            return messages.get(path)[0];
        }
        return "";
    }
    public static Integer getInt(String path) {
        if(messages.containsKey(path)){
            return Integer.valueOf(messages.get(path)[0]);
        }
        return 0;
    }
    public static String getPrefix() {
        if(messages.containsKey("Prefix")){
            return ChatColor.translateAlternateColorCodes('&', messages.get("Prefix")[0]);
        }
        return "";
    }
    public static String getChatPrefix() {
        if(messages.containsKey("ChatPrefix")){
            return ChatColor.translateAlternateColorCodes('&', messages.get("ChatPrefix")[0]);
        }
        return "";
    }
    public static String getCoinPrefix() {
        if(messages.containsKey("CoinPrefix")){
            return ChatColor.translateAlternateColorCodes('&', messages.get("CoinPrefix")[0]);
        }
        return "";
    }
    public static Boolean getBoolean(String path) {
        if(messages.containsKey(path)){
            return Boolean.valueOf(messages.get(path)[0]);
        }
        return false;
    }
}
