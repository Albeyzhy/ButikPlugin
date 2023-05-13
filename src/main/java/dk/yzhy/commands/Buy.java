package dk.yzhy.commands;

import dk.yzhy.Butik;
import dk.yzhy.utils.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import dk.yzhy.gui.MainGUI;

public class Buy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length >= 1 && args[0].equalsIgnoreCase("reload") && p.hasPermission("butik.admin")){
            try {
                long tidBefore = System.currentTimeMillis();
                Butik.mainConfig.reloadConfig();
                Butik.mainConfigYML = Butik.mainConfig.getConfig();
                ConfigManager.loadALL();
                Butik.SaveToYAML();
                p.sendMessage("§aDu genindlæste configurationen & alle beskederne. §7(" + (System.currentTimeMillis() - tidBefore) + "ms)");
            } catch (Exception e) {
                p.sendMessage("§cNoget gik galt, tjek loggen! Kør evt. din config igennem en YML parser online!");
                e.printStackTrace();
            }
        } else {
            MainGUI.GUI(p);
        }
        return true;
    }
}
