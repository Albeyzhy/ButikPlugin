package dk.yzhy.listeners;

import ch.njol.skript.variables.Variables;
import dk.yzhy.task.FreeCoins;
import dk.yzhy.utils.CoinsUtils;
import dk.yzhy.Butik;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Join implements Listener {
    @EventHandler
    public void ChatEvent(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (!CoinsUtils.coins.containsKey(uuid)) {
            if (Butik.CoinDataYML.contains("Konti." + uuid)) {
                CoinsUtils.coins.put(uuid, (Butik.CoinDataYML.getLong("Konti." + uuid)));
            }
        }
        FreeCoins.start(event.getPlayer());
    }
}
