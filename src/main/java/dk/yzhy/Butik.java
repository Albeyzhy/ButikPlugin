package dk.yzhy;

import dk.yzhy.commands.Buy;
import dk.yzhy.commands.Coins;
import dk.yzhy.listeners.Chat;
import dk.yzhy.listeners.Join;
import dk.yzhy.task.SaveCoins;
import dk.yzhy.utils.CoinsUtils;
import dk.yzhy.utils.Config;
import dk.yzhy.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public final class Butik extends JavaPlugin {
    public static Map<String, String> config = new HashMap<>();
    public static Config CoinData, mainConfig;
    public static FileConfiguration CoinDataYML, mainConfigYML;
    public static Butik instance;



    @Override
    public void onEnable() {
        instance = this;
        System.out.println("[Butik] Butik plugin loaded!");
        register();
        LoadYAML();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new SaveCoins(), 12000, 12000);
    }

    @Override
    public void onDisable() {
        System.out.println("[Butik] Loader coins data fra hashmap til YML!");
        try {
            SaveToYAML();
            long tidBefore = System.currentTimeMillis();
            System.out.println("[Butik] Loadet alt data til YML successfuldt! (" + (System.currentTimeMillis() - tidBefore) + "ms)");
        } catch (Exception e) {
            System.out.println("[Butik] Fejlede at save alt YML data, se nedenunder:");
            e.printStackTrace();
        }
    }
    public static void SaveToYAML() {
        for (Map.Entry<UUID, Long> set : CoinsUtils.coins.entrySet()) {
            Butik.CoinDataYML.set("Konti." + set.getKey(), set.getValue());
        }
        Butik.CoinData.saveConfig();
    }
    private void register() {
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
        this.getCommand("buy").setExecutor(new Buy());
        this.getCommand("coins").setExecutor(new Coins());
    }
    private void LoadYAML() {
        if (!(new File(getDataFolder(), "coindata.yml")).exists())saveResource("coindata.yml", false);
        CoinData = new Config(this, null, "coindata.yml");
        CoinDataYML = CoinData.getConfig();
        if (!(new File(getDataFolder(), "config.yml")).exists())saveResource("config.yml", false);
        mainConfig = new Config(this, null, "config.yml");
        mainConfigYML = mainConfig.getConfig();
        ConfigManager.loadALL();
    }
    public static Butik getInstance() { return instance; }

}

