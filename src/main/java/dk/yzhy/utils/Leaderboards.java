package dk.yzhy.utils;

import dk.yzhy.Butik;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Leaderboards {
    public static ExecutorService executorService;
    public static void print(Player p) {
        Map<UUID, Double> LB = new HashMap<>();
        p.sendMessage("§7Loader leaderboards ind, dette kan tage flere minutter. (Async)");
        executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            for (String path : Butik.CoinDataYML.getKeys(true)) {
                if(!Objects.equals(path, "Konti")) {
                    Long antal = Butik.CoinDataYML.getLong(path);
                    UUID player = UUID.fromString(path.replaceAll("Konti.", ""));
                    LB.put(player, Double.valueOf(antal));
                }
            }
            LB.putAll(CoinsUtils.coins);
            // Leaderboard udregning:
            ArrayList<Map.Entry<UUID, Double>> LBA = new ArrayList<>(LB.entrySet());
            LBA.sort(Map.Entry.comparingByValue());
            Collections.reverse(LBA);
            for(int i = 1; i <= 10; i++){
                String[] value = LBA.get(i - 1).toString().split("=");
                OfflinePlayer offlineplayer = Bukkit.getOfflinePlayer(value[0]);
                p.sendMessage(i + ". " + offlineplayer.getName() + " " + value[1]);
            }
        });
        executorService.shutdown();
    }
}
