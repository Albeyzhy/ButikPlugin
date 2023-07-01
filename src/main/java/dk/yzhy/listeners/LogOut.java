package dk.yzhy.listeners;

import dk.yzhy.Butik;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogOut implements Listener {
    @EventHandler
    public void LeaveEvent(PlayerQuitEvent event) {
        event.getPlayer().removeMetadata("SessionID", Butik.getInstance());
    }
}
