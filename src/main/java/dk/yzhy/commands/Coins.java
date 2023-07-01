package dk.yzhy.commands;

import dk.yzhy.utils.CoinsUtils;
import dk.yzhy.utils.ConfigManager;
import dk.yzhy.utils.Leaderboards;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static dk.yzhy.utils.BuyCoins.cdreq;

public class Coins implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(ConfigManager.getCoinPrefix());
            p.sendMessage("§7 Du har §f" + CoinsUtils.getCoins(p) + "§7 coins!");
        } else if ((args[0].equalsIgnoreCase("spørg") || args[0].equalsIgnoreCase("ask") || args[0].equalsIgnoreCase("req")) && p.hasPermission("coins.admin")) {
            OfflinePlayer offlineplayer = Bukkit.getOfflinePlayer(args[1]);
            if (offlineplayer.hasPlayedBefore() || offlineplayer.isOnline()) {
                if (cdreq.containsKey(offlineplayer.getUniqueId())) {
                    p.sendMessage(ConfigManager.getCoinPrefix());
                    p.sendMessage("§7 Du tager nu imod spilleren §f" + offlineplayer.getName() + "'s §7anmodning!");
                    offlineplayer.getPlayer().sendMessage(ConfigManager.getCoinPrefix());
                    offlineplayer.getPlayer().sendMessage(" §7Administratoren §f" + p.getName() + "§7 vil tage imod dit køb!");
                    offlineplayer.getPlayer().sendMessage(" §7Pay med §f/money pay " + p.getName() + " <antal> §7i en lobby!");
                    cdreq.remove(offlineplayer.getUniqueId());
                    System.out.println("[Butik] Administratoren " + p.getName() + " tager imod spillerens " + offlineplayer.getName() + "'s køb");
                } else {
                    p.sendMessage(ConfigManager.getCoinPrefix());
                    p.sendMessage(" §7En anden administrator har allerede taget imod dette køb!");
                }
            } else {
                p.sendMessage(ConfigManager.getCoinPrefix());
                p.sendMessage(" §7Denne spiller har aldrig spillet på serveren før!");
            }
        } else if ((args[0].equalsIgnoreCase("bal") || args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("info")) && p.hasPermission("coins.admin")) {
            if (args.length >= 2) {
                OfflinePlayer offlineplayer = Bukkit.getOfflinePlayer(args[1]);
                if (offlineplayer.hasPlayedBefore() || offlineplayer.isOnline()) {
                    p.sendMessage(ConfigManager.getCoinPrefix());
                    p.sendMessage("§7 Spilleren §f" + offlineplayer.getName() + "§7 har §f" + CoinsUtils.getCoins(offlineplayer) + "§7 coins!");
                } else {
                    p.sendMessage(ConfigManager.getCoinPrefix());
                    p.sendMessage(" §7Denne spiller har aldrig spillet på serveren før!");
                }
            } else {
                p.sendMessage(ConfigManager.getCoinPrefix());
                p.sendMessage(" §7Forkert brug: §f/coins " + args[0].toLowerCase() +  " <spiller>");
            }
        } else if ((args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("b+") || args[0].equalsIgnoreCase("tilføj") || args[0].equalsIgnoreCase("give")) && p.hasPermission("coins.admin")) {
            if (args.length >= 3) {
                OfflinePlayer offlineplayer = Bukkit.getOfflinePlayer(args[1]);
                if (offlineplayer.hasPlayedBefore() || offlineplayer.isOnline()) {
                    long antal = Long.parseLong(args[2]);
                    if (antal > 0) {
                        CoinsUtils.addCoins(offlineplayer, antal);
                        p.sendMessage(ConfigManager.getCoinPrefix());
                        p.sendMessage(" §7Tilføjet §f" + antal + "§7 coins til spilleren §f" + offlineplayer.getName() + "§7!");
                        if (args[0].equalsIgnoreCase("b+")) {
                            Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                            Bukkit.broadcastMessage("§7 Spilleren §f" + offlineplayer.getName() + "§7 købte lige§f " + antal + "§7 coins!");
                            if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogBuyCoins"))) {
                                System.out.println("[Butik] Spilleren " + offlineplayer.getName() + " købte " + antal + " coins af " + p.getName() + "!");
                            }
                        }
                    } else {
                        p.sendMessage(ConfigManager.getCoinPrefix());
                        p.sendMessage(" §7Antallet du vil tilføje skal være over 0!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getCoinPrefix());
                    p.sendMessage(" §7Denne spiller har aldrig spillet på serveren før!");
                }
            } else {
                p.sendMessage(ConfigManager.getCoinPrefix());
                p.sendMessage(" §7Forkert brug: §f/coins " + args[0].toLowerCase() +  " <spiller> <antal>");
            }
        } else if ((args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("fjern") || args[0].equalsIgnoreCase("subtract")) && p.hasPermission("coins.admin")) {
            if (args.length >= 3) {
                OfflinePlayer offlineplayer = Bukkit.getOfflinePlayer(args[1]);
                if (offlineplayer.hasPlayedBefore() || offlineplayer.isOnline()) {
                    long antal = Long.parseLong(args[2]);
                    if (antal > 0) {
                        CoinsUtils.removeCoins(offlineplayer, antal);
                        p.sendMessage(ConfigManager.getCoinPrefix());
                        p.sendMessage("§7Fjernet §f" + antal + "§7 coins til spilleren §f" + offlineplayer.getName() + "§7!");
                    } else {
                        p.sendMessage(ConfigManager.getCoinPrefix());
                        p.sendMessage("§7Antallet du vil fjerne skal være over 0!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getCoinPrefix());
                    p.sendMessage("§7Denne spiller har aldrig spillet på serveren før!");
                }
            } else {
                p.sendMessage(ConfigManager.getCoinPrefix());
                p.sendMessage("§7Forkert brug: §f/coins " + args[0].toLowerCase() + " <spiller> <antal>");
            }
        } else if (args[0].equalsIgnoreCase("set") && p.hasPermission("coins.admin")) {
            if (args.length >= 3) {
                OfflinePlayer offlineplayer = Bukkit.getOfflinePlayer(args[1]);
                if (offlineplayer.hasPlayedBefore() || offlineplayer.isOnline()) {
                    long antal = Long.parseLong(args[2]);
                    if (antal >= 0) {
                        CoinsUtils.setCoins(offlineplayer, antal);
                        p.sendMessage(ConfigManager.getCoinPrefix());
                        p.sendMessage(" §7Sat §f" + offlineplayer.getName() + "'s§7 coins til §f" + antal + "§7!");
                    } else {
                        p.sendMessage(ConfigManager.getCoinPrefix());
                        p.sendMessage(" §7Antallet du vil sætte skal være 0 eller over!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getCoinPrefix());
                    p.sendMessage(" §7Denne spiller har aldrig spillet på serveren før!");
                }
            } else {
                p.sendMessage(ConfigManager.getCoinPrefix());
                p.sendMessage(" §7Forkert brug: §f/coins set <spiller> <antal>");
            }
        } else if (args[0].equalsIgnoreCase("top") && p.hasPermission("coins.admin")) {
            Leaderboards.print(p);
        } else if (args[0].equalsIgnoreCase("pay") || args[0].equalsIgnoreCase("overfør")) {
            if (args.length >= 3) {
                OfflinePlayer offlineplayer = Bukkit.getOfflinePlayer(args[1]);
                if ((offlineplayer.hasPlayedBefore() || offlineplayer.isOnline()) && !offlineplayer.equals(p)) {
                    long antal = Long.parseLong(args[2]);
                    if (antal > 0) {
                        if (CoinsUtils.getCoins(p) >= antal) {
                            CoinsUtils.addCoins(offlineplayer, antal);
                            CoinsUtils.removeCoins(p, antal);
                            p.sendMessage(ConfigManager.getCoinPrefix());
                            p.sendMessage(" §7Du har nu payet §f" + antal + "§7 coins til §f" + offlineplayer.getName() + "§7!");
                            if (offlineplayer.isOnline()) {
                                offlineplayer.getPlayer().sendMessage(ConfigManager.getCoinPrefix());
                                offlineplayer.getPlayer().sendMessage("§7 Du fik payet §f" + antal + "§7 coins fra §f" + p.getName() + "§7!");
                            }
                        } else {
                            p.sendMessage(ConfigManager.getCoinPrefix());
                            p.sendMessage(" §7Du har ikke nok coins til dette!");
                        }
                    } else {
                        p.sendMessage(ConfigManager.getCoinPrefix());
                        p.sendMessage(" §7Antallet du vil overføre skal være over 0!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getCoinPrefix());
                    p.sendMessage(" §7Denne spiller har aldrig spillet på serveren før, ellers er det ikke muligt at paye denne spiller!");
                }
            } else {
                p.sendMessage(ConfigManager.getCoinPrefix());
                p.sendMessage(" §7Forkert brug: §f/coins " + args[0].toLowerCase() + " <spiller> <antal>");
            }
        } else {
            p.sendMessage(ConfigManager.getCoinPrefix());
            p.sendMessage(" §7Forkert brug: §f/coins overfør <spiller> <antal>");
        }
        return true;
    }
}