package dk.yzhy.listeners;

import dk.manaxi.unikpay.plugin.API.Internal;
import dk.yzhy.Butik;
import dk.yzhy.utils.ConfigManager;
import dk.yzhy.utils.HandleBuy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class Chat implements Listener {

    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        if (p.hasMetadata("ChatButik")) {
            event.setCancelled(true);
            List<MetadataValue> values = p.getMetadata("ChatButik");
            try {
                int antal = Integer.parseInt(event.getMessage());
                if (antal >= 1) {
                    HandleBuy.BuyProductKey(values.get(0).asString(), p, antal);
                    p.removeMetadata("ChatButik", Butik.getInstance());
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage(" §7Dette er ikke et gydligt antal.");
                    p.removeMetadata("ChatButik", Butik.getInstance());
                }
            } catch (NumberFormatException e) {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage(" §7Dette er ikke et gydligt antal.");
                p.removeMetadata("ChatButik", Butik.getInstance());
            }
        } else if (p.hasMetadata("BuyCoins")) {
            event.setCancelled(true);
            List<MetadataValue> values = p.getMetadata("BuyCoins");
            try {
                int antal = Integer.parseInt(event.getMessage());
                if (antal >= 1) {
                    Internal.sendPackageRequest(p,  antal+" coins", antal, null);
                    p.removeMetadata("BuyCoins", Butik.getInstance());
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage(" §7Dette er ikke et gydligt antal.");
                    p.removeMetadata("BuyCoins", Butik.getInstance());
                }
            } catch (NumberFormatException e) {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage(" §7Dette er ikke et gydligt antal.");
                p.removeMetadata("BuyCoins", Butik.getInstance());
            }
    }
    }
}
