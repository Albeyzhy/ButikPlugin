package dk.yzhy.utils;

import dk.yzhy.Butik;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class CoinsUtils {
    public static Map<UUID, Double> coins = new HashMap<>();

    static public void setCoins(OfflinePlayer p, Double a){
        coins.put(p.getUniqueId(), a);
        if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogCoins"))) {
            System.out.println("[Butik] Spilleren " + p.getName() + "'s coins blev sat til " + a);
        }
    }
    static public void addCoins(OfflinePlayer p, Double a){
        UUID uuid = p.getUniqueId();
        Double antal = a;
        if (coins.containsKey(uuid)) {
            antal = coins.get(uuid) + a;
        } else if (Butik.CoinDataYML.get("Konti." + uuid) != null) {
            antal = Butik.CoinDataYML.getLong("Konti." + uuid) + a;
        }
        coins.put(uuid, antal);
        if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogCoins"))) {
            System.out.println("[Butik] Spilleren " + p.getName() + " fik tilføjet " + a + " coins");
        }
    }
    static public void removeCoins(OfflinePlayer p, Double a){
        UUID uuid = p.getUniqueId();
        Double antal = a;
        if (coins.containsKey(uuid)) {
            antal = coins.get(uuid) - a;
        } else if (Butik.CoinDataYML.get("Konti." + uuid) != null) {
            antal = Butik.CoinDataYML.getLong("Konti." + uuid) - a;
        }
        coins.put(uuid, antal);
        if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogCoins"))) {
            System.out.println("[Butik] Spilleren " + p.getName() + " fik fjernet " + a + " coins");
        }
    }
    static public Double getCoins(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        if (coins.containsKey(uuid)) {
            return coins.get(uuid);
        } else if (Butik.CoinDataYML.get("Konti." + uuid) != null) {
            return Butik.CoinDataYML.getDouble("Konti." + uuid);
        } else {
            return 0D;
        }
    }
}
