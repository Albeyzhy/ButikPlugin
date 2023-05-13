package dk.yzhy.utils;

import dk.yzhy.Butik;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CoinsUtils {
    public static Map<UUID, Long> coins = new HashMap<>();

    static public void setCoins(OfflinePlayer p, Long a) {
        coins.put(p.getUniqueId(), a);
        System.out.println("[Butik] Spilleren " + p.getName() + "'s coins blev sat til " + a);
    }
    static public void addCoins(OfflinePlayer p, Long a) {
        UUID uuid = p.getUniqueId();
        Long antal = a;
        if (coins.containsKey(uuid)) {
            antal = coins.get(uuid) + a;
        } else if (Butik.CoinDataYML.get("Konti." + uuid) != null) {
            antal = Butik.CoinDataYML.getLong("Konti." + uuid) + a;
        }
        coins.put(uuid, antal);
        System.out.println("[Butik] Spilleren " + p.getName() + " fik tilføjet " + a + " coins");
    }
    static public void removeCoins(OfflinePlayer p, Long a) {
        UUID uuid = p.getUniqueId();
        Long antal = a;
        if (coins.containsKey(uuid)) {
            antal = coins.get(uuid) - a;
        } else if (Butik.CoinDataYML.get("Konti." + uuid) != null) {
            antal = Butik.CoinDataYML.getLong("Konti." + uuid) - a;
        }
        coins.put(uuid, antal);
        System.out.println("[Butik] Spilleren " + p.getName() + " fik fjernet " + a + " coins");
    }
    static public Long getCoins(OfflinePlayer p) {
        UUID uuid = p.getUniqueId();
        if (coins.containsKey(uuid)) {
            return coins.get(uuid);
        } else if (Butik.CoinDataYML.get("Konti." + uuid) != null) {
            return Butik.CoinDataYML.getLong("Konti." + uuid);
        } else {
            return 0L;
        }
    }
}
