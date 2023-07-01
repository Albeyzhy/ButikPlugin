package dk.yzhy.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BuyCoins {
    public static Map<UUID, Boolean> cdreq = new HashMap<>();
    public static void SendCoinsMessage(Player p) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("§eKlik her for at sende en besked til " +  player.getName())) {
                player.sendMessage(ConfigManager.getChatPrefix());
                player.sendMessage("§7 Spilleren §f" + p.getName() + "§7 vil gerne købe coins!");
                TextComponent subComponent = new TextComponent("§e Klik her for at sende en besked til spilleren!");
                subComponent.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§e Klik her for at sende en besked til spilleren!").create() ) );
                subComponent.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND,  "/coins req " + p.getName()));
                player.spigot().sendMessage( subComponent );
                cdreq.put(p.getUniqueId(), true);
            }
        }
    }
}
