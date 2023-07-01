package dk.yzhy.task;

import dk.yzhy.Butik;
import dk.yzhy.utils.CoinsUtils;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class FreeCoins{
    public static void start(Player p) {
        Long SessionID = System.currentTimeMillis();
        p.setMetadata("SessionID", new FixedMetadataValue(Butik.getInstance(), System.currentTimeMillis()));
        new BukkitRunnable() {
            @Override
            public void run() {
                if(p.hasMetadata("SessionID") && Objects.equals(p.getMetadata("SessionID").get(0).asString(), SessionID.toString())) {
                    CoinsUtils.addCoins(p, 10L);
                    p.sendMessage("§e§l[!] §7Du modtog 10 coins for at være online!");
                }else{cancel();}
            }
        }.runTaskTimerAsynchronously(Butik.getInstance(), 72000L, 72000L);
    }
}
