package dk.yzhy.listeners;

import dk.manaxi.unikpay.plugin.event.OnBetaling;
import dk.yzhy.utils.CoinsUtils;
import dk.yzhy.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Betaling implements Listener {

    @EventHandler
    public void onBetaling(OnBetaling event) {

        if(event.getPakke().getName().contains("coins")) {
            CoinsUtils.addCoins(event.getPlayer(), Double.valueOf(event.getPakke().getPrice()));
            Bukkit.broadcastMessage(ConfigManager.getChatPrefix());

            Bukkit.broadcastMessage("§7 Spilleren §f" + event.getPlayer().getName() + "§7 købte lige§f " + event.getPakke().getPrice() + "§7 coins!");
            if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogBuyCoins"))) {
                System.out.println("[Butik] Spilleren " + event.getPlayer().getName() + " købte " + event.getPakke().getPrice() + " coins af " + "UnikPay" + "!");
            }
        }

    }
}
