package dk.yzhy.utils;

import dk.yzhy.Butik;
import org.bukkit.Bukkit;

public class Console {
    public static void send(final String command) {
        Bukkit.getScheduler().runTask(Butik.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command));
    }
}

