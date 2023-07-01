package dk.yzhy.utils;

import ch.njol.skript.variables.Variables;
import dk.yzhy.Butik;
import dk.yzhy.gui.RanksGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class HandleBuy {
    static Long PriceForMint;
    static Long PriceForMintPlus;
    static Long PriceForSlik;
    static Long PriceForTwix;
    static Long PriceForKitHead;
    static Long PriceForKitKeys;
    static Long PriceForKitEnchanter;
    static Long PriceForKitFarmer;
    static Long PriceForVagt;
    public static void BuyProductRank(String s, Player p, String Groups) {
        if (Objects.equals(s, "Mint+Rank")) {
            if (Groups.equalsIgnoreCase("mint+") || RanksGUI.getGroupsOver(Groups)) {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede denne rank, og kan derfor ikke købe den!");
            } else {
                PriceForMintPlus = Math.round((ConfigManager.getInt("Prices.Mint+")) - ((double) (ConfigManager.getInt("Prices.Mint+")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                if (Groups.equalsIgnoreCase("twix")) {
                    PriceForMintPlus = Math.round((ConfigManager.getInt("Prices.Mint+") - ConfigManager.getInt("Prices.Twix")) - ((double) (ConfigManager.getInt("Prices.Mint+") - ConfigManager.getInt("Prices.Twix")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                } else if (Groups.equalsIgnoreCase("slik")) {
                    PriceForMintPlus = Math.round((ConfigManager.getInt("Prices.Mint+") - ConfigManager.getInt("Prices.Slik")) - ((double) (ConfigManager.getInt("Prices.Mint+") - ConfigManager.getInt("Prices.Slik")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                } else if (Groups.equalsIgnoreCase("mint")) {
                    PriceForMintPlus = Math.round((ConfigManager.getInt("Prices.Mint+") - ConfigManager.getInt("Prices.Mint")) - ((double) (ConfigManager.getInt("Prices.Mint+") - ConfigManager.getInt("Prices.Mint")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                }
                if (CoinsUtils.getCoins(p) >= PriceForMintPlus) {
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §b§lMINT§3§l+§7 rank!");
                    p.closeInventory();
                    CoinsUtils.removeCoins(p, PriceForMintPlus);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt ranken mint+, og du fik fjernet §f" + PriceForMintPlus + "§7 coins fra dig.");
                    Console.send("lp user " + p.getName() + " group set mint+");
                    if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                        System.out.println("[Butik] Spilleren " + p.getName() + " købte " + s + " for " + PriceForMintPlus + "!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            }
        } else if (Objects.equals(s, "MintRank")) {
            if (Groups.equalsIgnoreCase("mint+") || Groups.equalsIgnoreCase("mint") || RanksGUI.getGroupsOver(Groups)) {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede denne rank, og kan derfor ikke købe den!");
            } else {
                PriceForMint = Math.round((ConfigManager.getInt("Prices.Mint")) - ((double) (ConfigManager.getInt("Prices.Mint")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                if (Groups.equalsIgnoreCase("twix")) {
                    PriceForMint = Math.round((ConfigManager.getInt("Prices.Mint") - ConfigManager.getInt("Prices.Twix")) - ((double) (ConfigManager.getInt("Prices.Mint") - ConfigManager.getInt("Prices.Twix")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                } else if (Groups.equalsIgnoreCase("slik")) {
                    PriceForMint = Math.round((ConfigManager.getInt("Prices.Mint") - ConfigManager.getInt("Prices.Slik")) - ((double) (ConfigManager.getInt("Prices.Mint") - ConfigManager.getInt("Prices.Slik")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                }
                if (CoinsUtils.getCoins(p) >= PriceForMint) {
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §b§lMINT§7 rank!");
                    p.closeInventory();
                    CoinsUtils.removeCoins(p, PriceForMint);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt ranken mint, og du fik fjernet §f" + PriceForMint + "§7 coins fra dig.");
                    Console.send("lp user " + p.getName() + " group set mint");
                    if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                        System.out.println("[Butik] Spilleren " + p.getName() + " købte " + s + " for " + PriceForMint + "!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            }
        } else if (Objects.equals(s, "SlikRank")) {
            if (Groups.equalsIgnoreCase("mint+") || Groups.equalsIgnoreCase("mint") || Groups.equalsIgnoreCase("slik") || RanksGUI.getGroupsOver(Groups)) {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede denne rank, og kan derfor ikke købe den!");
            } else {
                PriceForSlik = Math.round((ConfigManager.getInt("Prices.Slik")) - ((double) (ConfigManager.getInt("Prices.Slik")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                if (Groups.equalsIgnoreCase("twix")) {
                    PriceForSlik = Math.round((ConfigManager.getInt("Prices.Slik") - ConfigManager.getInt("Prices.Twix")) - ((double) (ConfigManager.getInt("Prices.Slik") - ConfigManager.getInt("Prices.Twix")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                }
                if (CoinsUtils.getCoins(p) >= PriceForSlik) {
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §6§lSLIK§7 rank!");
                    p.closeInventory();
                    CoinsUtils.removeCoins(p, PriceForSlik);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt ranken slik, og du fik fjernet§f " + PriceForSlik + "§7 coins fra dig.");
                    Console.send("lp user " + p.getName() + " group set slik");
                    if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                        System.out.println("[Butik] Spilleren " + p.getName() + " købte " + s + " for " + PriceForSlik + "!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            }
        } else if (Objects.equals(s, "TwixRank")) {
            if (Groups.equalsIgnoreCase("mint+") || Groups.equalsIgnoreCase("mint") || Groups.equalsIgnoreCase("slik") || Groups.equalsIgnoreCase("twix") || RanksGUI.getGroupsOver(Groups)) {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede denne rank, og kan derfor ikke købe den!");
            } else {
                PriceForTwix = Math.round((ConfigManager.getInt("Prices.Twix")) - ((double) (ConfigManager.getInt("Prices.Twix")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                if (CoinsUtils.getCoins(p) >= PriceForTwix) {
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §a§lTWIX§7 rank!");
                    p.closeInventory();
                    CoinsUtils.removeCoins(p, PriceForTwix);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt ranken twix, og du fik fjernet§f " + PriceForTwix + "§7 coins fra dig.");
                    Console.send("lp user " + p.getName() + " group set twix");
                    if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                        System.out.println("[Butik] Spilleren " + p.getName() + " købte " + s + " for " + PriceForTwix + "!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            }
        } else if (Objects.equals(s, "VagtRank")) {
            if (Groups.equalsIgnoreCase("vagt") || Groups.equalsIgnoreCase("officer") || Groups.equalsIgnoreCase("inspektør") || Groups.equalsIgnoreCase("direktør")) {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede denne rank, og kan derfor ikke købe den!");
            } else {
                Object bande = Variables.getVariable("bande::" + p.getUniqueId(), null, false);
                if (bande == null) {
                    Object link = Variables.getVariable("link::minecraftid::" + p.getUniqueId(), null, false);
                    if (link != null) {
                        PriceForVagt = Math.round((ConfigManager.getInt("Prices.Vagt")) - ((double) (ConfigManager.getInt("Prices.Vagt")) / 100 * (ConfigManager.getInt("Rabat.Andet"))));
                        if (CoinsUtils.getCoins(p) >= PriceForVagt) {
                            Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                            Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §e§lVAGT§7 rank!");
                            p.closeInventory();
                            CoinsUtils.removeCoins(p, PriceForVagt);
                            p.sendMessage(ConfigManager.getChatPrefix());
                            p.sendMessage("§7 Du har nu købt vagt, og du fik fjernet §f" + PriceForVagt + "§7 coins fra dig.");
                            Console.send("lp user " + p.getName() + " group set vagt");
                            Bukkit.getScheduler().runTaskLater(Butik.getInstance(), () -> {
                                Console.send("eco give " + p.getName() + " " + ConfigManager.getInt("Vagtpenge"));
                                Console.send("ewarp vagtstuen " + p.getName());
                                Console.send("ci " + p.getName() + " **");
                                p.sendMessage("§7... Giver dig rank på discorden!");
                                Console.send("skript:opdater " + p.getName());
                                p.sendMessage("§aDu har fået vagt rank på discorden..! §7(Ellers brug /opdater på discorden)");
                            }, 10L);
                            if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                                System.out.println("[Butik] Spilleren " + p.getName() + " købte " + s + " for " + PriceForVagt + "!");
                            }
                        } else {
                            p.sendMessage(ConfigManager.getChatPrefix());
                            p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                        }
                    } else {
                        p.sendMessage(ConfigManager.getChatPrefix());
                        p.sendMessage("§7 Du skal være linket på discorden. (/link)");
                    }
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du skal forlade din bande først (/b forlad)");
                }
            }
        }
    }
    public static void BuyProductKit(String s, Player p) {
        if (Objects.equals(s, "KitHead")) {
            if (!p.hasPermission("essentials.kits.head")) {
                PriceForKitHead = Math.round((ConfigManager.getInt("Prices.KitHead")) - ((double) (ConfigManager.getInt("Prices.KitHead")) / 100 * (ConfigManager.getInt("Rabat.Kits"))));
                if (CoinsUtils.getCoins(p) >= PriceForKitHead) {
                    CoinsUtils.removeCoins(p, PriceForKitHead);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt kit head, og du fik fjernet §f" + PriceForKitHead + " §7coins fra dig.");
                    Console.send("lp user " + p.getName() + " permission set essentials.kits.head true");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §4§lKIT HEAD§7!");
                    p.closeInventory();
                    if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                        System.out.println("[Butik] Spilleren " + p.getName() + " købte " + s + " for " + PriceForKitHead + "!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            } else {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede dette kit, og kan derfor ikke købe det!");
            }
        } else if (Objects.equals(s, "KitFarmer")) {
            if (!p.hasPermission("essentials.kits.farmer")) {
                PriceForKitFarmer = Math.round((ConfigManager.getInt("Prices.KitFarmer")) - ((double) (ConfigManager.getInt("Prices.KitFarmer")) / 100 * (ConfigManager.getInt("Rabat.Kits"))));
                if (CoinsUtils.getCoins(p) >= PriceForKitFarmer) {
                    CoinsUtils.removeCoins(p, PriceForKitFarmer);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage(" §7Du har nu købt kit farmer, og du fik fjernet§f " + PriceForKitFarmer + "§7 coins fra dig.");
                    Console.send("lp user " + p.getName() + " permission set essentials.kits.farmer true");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §6§lKIT FARMER§7!");
                    p.closeInventory();
                    if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                        System.out.println("[Butik] Spilleren " + p.getName() + " købte " + s + " for " + PriceForKitFarmer + "!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            } else {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede dette kit, og kan derfor ikke købe det!");
            }
        } else if (Objects.equals(s, "KitKeys")) {
            if (!p.hasPermission("essentials.kits.keys")) {
                PriceForKitKeys = Math.round((ConfigManager.getInt("Prices.KitKeys")) - ((double) (ConfigManager.getInt("Prices.KitKeys")) / 100 * (ConfigManager.getInt("Rabat.Kits"))));
                if (CoinsUtils.getCoins(p) >= PriceForKitKeys) {
                    CoinsUtils.removeCoins(p, PriceForKitKeys);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt kit keys, og du fik fjernet §f" + PriceForKitKeys + "§7 coins fra dig.");
                    Console.send("lp user " + p.getName() + " permission set essentials.kits.keys true");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §c§lKIT KEYS§7!");
                    p.closeInventory();
                    if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                        System.out.println("[Butik] Spilleren " + p.getName() + " købte " + s + " for " + PriceForKitKeys + "!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            } else {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede dette kit, og kan derfor ikke købe det!");
            }
        } else if (Objects.equals(s, "KitEnchanter")) {
            if (!p.hasPermission("essentials.kits.enchanter")) {
                PriceForKitEnchanter = Math.round((ConfigManager.getInt("Prices.KitEnchanter")) - ((double) (ConfigManager.getInt("Prices.KitEnchanter")) / 100 * (ConfigManager.getInt("Rabat.Kits"))));
                if (CoinsUtils.getCoins(p) >= PriceForKitEnchanter) {
                    CoinsUtils.removeCoins(p, PriceForKitEnchanter);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt kit enchanter, og du fik fjernet §f" + PriceForKitEnchanter + "§7 coins fra dig.");
                    Console.send("lp user " + p.getName() + " permission set essentials.kits.enchanter true");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §9§lKIT ENCHANTER§7!");
                    p.closeInventory();
                    if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                        System.out.println("[Butik] Spilleren " + p.getName() + " købte " + s + " for " + PriceForKitEnchanter + "!");
                    }
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            } else {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede dette kit, og kan derfor ikke købe det!");
            }
        }
    }
    public static void BuyProductKey(String s, Player p, Integer a) {
        Long Price = ((ConfigManager.getInt("Prices.Key" + s) - ((long) (ConfigManager.getInt("Prices.Key" + s) / 100) * ConfigManager.getInt("Rabat.Keys")))*a);
        if (CoinsUtils.getCoins(p) >= Price) {
            CoinsUtils.removeCoins(p,  Price);
            p.sendMessage(ConfigManager.getChatPrefix());
            p.sendMessage("§7 Du har nu købt " + a + "x " + s + " keys, og du fik fjernet §f" + Price + "§7 coins fra dig.");
            Console.send("cc give p " + s + " " + a + " " + p.getName());
            Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
            Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §f" + a + "x " + s + "§7 keys!");
            if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                System.out.println("[Butik] Spilleren " + p.getName() + " købte " + a + "x " + s + " for " + Price + "!");
            }
        } else {
            p.sendMessage(ConfigManager.getChatPrefix());
            p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
        }
    }
    public static void BuyProductVipCelle(Player p) {
        if(!p.hasPermission("cells.rent.vipcelle")){
            Long Price = ((ConfigManager.getInt("Prices.VipCelle") - ((long) (ConfigManager.getInt("Prices.VipCelle") / 100) * ConfigManager.getInt("Rabat.Andet"))));
            if (CoinsUtils.getCoins(p) >= Price) {
                p.closeInventory();
                CoinsUtils.removeCoins(p,  Price);
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du har nu købt en vip celle, og du fik fjernet §f" + Price + "§7 coins fra dig.");
                Console.send("lp user " + p.getName() + " permission set cells.rent.vipcelle true");
                Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige en §2§lVIP CELLE§7!");
                if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                    System.out.println("[Butik] Spilleren " + p.getName() + " købte en vipcelle for " + Price + "!");
                }
            } else {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
            }
        } else {
            p.sendMessage(ConfigManager.getChatPrefix());
            p.sendMessage("§7 Du ejer allerede en vipcelle, og kan derfor ikke købe en!");
        }
    }
    public static void BuyProductBattlepass(Player p) {
        if (!p.hasPermission("battlepass.premium")) {
            Long Price = ((ConfigManager.getInt("Prices.Battlepass") - ((long) (ConfigManager.getInt("Prices.Battlepass") / 100) * ConfigManager.getInt("Rabat.Andet"))));
            if (CoinsUtils.getCoins(p) >= Price) {
                p.closeInventory();
                CoinsUtils.removeCoins(p,  Price);
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du har nu købt battlepasset, og du fik fjernet §f" + Price + "§7 coins fra dig.");
                Console.send("lp user " + p.getName() + " permission set battlepass.premium true");
                Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §6§lBATTLEPASS§7!");
                if (Boolean.parseBoolean(ConfigManager.getString("WebHook.LogButik"))) {
                    System.out.println("[Butik] Spilleren " + p.getName() + " købte en battlepass for " + Price + "!");
                }
            } else {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
            }
        } else {
            p.sendMessage(ConfigManager.getChatPrefix());
            p.sendMessage("§7 Du ejer allerede battlepasset, og kan derfor ikke købe det!");
        }
    }
}

