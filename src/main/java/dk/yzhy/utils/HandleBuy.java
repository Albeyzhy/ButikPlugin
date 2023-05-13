package dk.yzhy.utils;

import ch.njol.skript.lang.function.Function;
import ch.njol.skript.variables.Variables;
import dk.yzhy.Butik;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HandleBuy {
    static Long PriceForMint;
    static Long PriceForSlik;
    static Long PriceForTwix;
    static Long PriceForKitHead;
    static Long PriceForKitKeys;
    static Long PriceForKitEnchanter;
    static Long PriceForKitFarmer;
    static Long PriceForVagt;
    public static void BuyProductRank(String s, Player p, String Groups) {
        if (Objects.equals(s, "MintRank")) {
            if (Groups.contains("mint")) {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede denne rank, og kan derfor ikke købe den!");
            } else {
                PriceForMint = Math.round((ConfigManager.getInt("Prices.Mint")) - ((double) (ConfigManager.getInt("Prices.Mint")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                if (Groups.contains("twix")) {
                    PriceForMint = Math.round((ConfigManager.getInt("Prices.Mint") - ConfigManager.getInt("Prices.Twix")) - ((double) (ConfigManager.getInt("Prices.Mint") - ConfigManager.getInt("Prices.Twix")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                } else if (Groups.contains("slik")) {
                    PriceForMint = Math.round((ConfigManager.getInt("Prices.Mint") - ConfigManager.getInt("Prices.Slik")) - ((double) (ConfigManager.getInt("Prices.Mint") - ConfigManager.getInt("Prices.Slik")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                }
                if (CoinsUtils.getCoins(p) >= PriceForMint) {
                    CoinsUtils.removeCoins(p, PriceForMint);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt ranken mint, og du fik fjernet §f" + PriceForMint + "§7 coins fra dig.");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " group set mint");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §b§lMINT§7 rank!");
                    p.closeInventory();
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            }
        } else if (Objects.equals(s, "SlikRank")) {
            if (Groups.contains("mint") || Groups.contains("slik")) {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede denne rank, og kan derfor ikke købe den!");
            } else {
                PriceForSlik = Math.round((ConfigManager.getInt("Prices.Slik")) - ((double) (ConfigManager.getInt("Prices.Slik")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                if (Groups.contains("twix")) {
                    PriceForSlik = Math.round((ConfigManager.getInt("Prices.Slik") - ConfigManager.getInt("Prices.Twix")) - ((double) (ConfigManager.getInt("Prices.Slik") - ConfigManager.getInt("Prices.Twix")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                }
                if (CoinsUtils.getCoins(p) >= PriceForSlik) {
                    CoinsUtils.removeCoins(p, PriceForSlik);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt ranken slik, og du fik fjernet§f " + PriceForSlik + "§7 coins fra dig.");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " group set slik");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §6§lSLIK§7 rank!");
                    p.closeInventory();
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            }
        } else if (Objects.equals(s, "TwixRank")) {
            if (Groups.contains("mint") || Groups.contains("slik") || Groups.contains("twix")) {
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Du ejer allerede denne rank, og kan derfor ikke købe den!");
            } else {
                PriceForTwix = Math.round((ConfigManager.getInt("Prices.Twix")) - ((double) (ConfigManager.getInt("Prices.Twix")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
                if (CoinsUtils.getCoins(p) >= PriceForTwix) {
                    CoinsUtils.removeCoins(p, PriceForTwix);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt ranken twix, og du fik fjernet§f " + PriceForTwix + "§7 coins fra dig.");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " group set twix");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §a§lTWIX§7 rank!");
                    p.closeInventory();
                } else {
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
                }
            }
        } else if (Objects.equals(s, "VagtRank")) {
            if (Groups.contains("vagt") || Groups.contains("officer") || Groups.contains("inspektør") || Groups.contains("direktør")) {
                p.sendMessage("§cDu ejer allerede denne rank, og kan derfor ikke købe den!");
            } else {
                Object bande = Variables.getVariable("bande::" + p.getUniqueId(), null, false);
                if (bande == null) {
                    Object link = Variables.getVariable("link::minecraftid::" + p.getUniqueId(), null, false);
                    if (link != null) {
                        PriceForVagt = Math.round((ConfigManager.getInt("Prices.Vagt")) - ((double) (ConfigManager.getInt("Prices.Vagt")) / 100 * (ConfigManager.getInt("Rabat.Andet"))));
                        if (CoinsUtils.getCoins(p) >= PriceForVagt) {
                            CoinsUtils.removeCoins(p, PriceForVagt);
                            p.sendMessage(ConfigManager.getChatPrefix());
                            p.sendMessage("§7 Du har nu købt vagt, og du fik fjernet §f" + PriceForVagt + "§7 coins fra dig.");
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " group set vagt");
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " " + ConfigManager.getInt("Vagtpenge"));
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "ewarp vagtstuen " + p.getName());
                            Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                            Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §e§lVAGT§7 rank!");
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "opdater " + p.getName());
                            p.closeInventory();
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
                PriceForKitHead = Math.round((Butik.mainConfigYML.getInt("Prices.KitHead")) - ((double) (Butik.mainConfigYML.getInt("Prices.KitHead")) / 100 * (ConfigManager.getInt("Rabat.Kits"))));
                if (CoinsUtils.getCoins(p) >= PriceForKitHead) {
                    CoinsUtils.removeCoins(p, PriceForKitHead);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt kit head, og du fik fjernet §f" + PriceForKitHead + " §7coins fra dig.");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission set essentials.kits.head true");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §4§lKIT HEAD§7!");
                    p.closeInventory();
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
                PriceForKitFarmer = Math.round((Butik.mainConfigYML.getInt("Prices.KitFarmer")) - ((double) (Butik.mainConfigYML.getInt("Prices.KitFarmer")) / 100 * (ConfigManager.getInt("Rabat.Kits"))));
                if (CoinsUtils.getCoins(p) >= PriceForKitFarmer) {
                    CoinsUtils.removeCoins(p, PriceForKitFarmer);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage(" §7Du har nu købt kit farmer, og du fik fjernet§f " + PriceForKitFarmer + "§7 coins fra dig.");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission set essentials.kits.farmer true");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §6§lKIT FARMER§7!");
                    p.closeInventory();
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
                PriceForKitKeys = Math.round((Butik.mainConfigYML.getInt("Prices.KitKeys")) - ((double) (Butik.mainConfigYML.getInt("Prices.KitKeys")) / 100 * (ConfigManager.getInt("Rabat.Kits"))));
                if (CoinsUtils.getCoins(p) >= PriceForKitKeys) {
                    CoinsUtils.removeCoins(p, PriceForKitKeys);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt kit keys, og du fik fjernet §f" + PriceForKitKeys + "§7 coins fra dig.");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission set essentials.kits.keys true");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §c§lKIT KEYS§7!");
                    p.closeInventory();
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
                PriceForKitEnchanter = Math.round((Butik.mainConfigYML.getInt("Prices.KitEnchanter")) - ((double) (Butik.mainConfigYML.getInt("Prices.KitEnchanter")) / 100 * (ConfigManager.getInt("Rabat.Kits"))));
                if (CoinsUtils.getCoins(p) >= PriceForKitEnchanter) {
                    CoinsUtils.removeCoins(p, PriceForKitEnchanter);
                    p.sendMessage(ConfigManager.getChatPrefix());
                    p.sendMessage("§7 Du har nu købt kit enchanter, og du fik fjernet §f" + PriceForKitEnchanter + "§7 coins fra dig.");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission set essentials.kits.enchanter true");
                    Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
                    Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §9§lKIT ENCHANTER§7!");
                    p.closeInventory();
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
        Long Price = (long) ((Butik.mainConfigYML.getInt("Prices.Key" + s) - ((Butik.mainConfigYML.getInt("Prices.Key" + s) / 100) * ConfigManager.getInt("Rabat.Keys")))*a);
        if (CoinsUtils.getCoins(p) > Price) {
            CoinsUtils.removeCoins(p,  Price);
            p.sendMessage(ConfigManager.getChatPrefix());
            p.sendMessage("§7 Du har nu købt " + a + "x " + s + " keys, og du fik fjernet §f" + Price + "§7 coins fra dig.");
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "cc give p " + s + " " + a + " " + p.getName());
            Bukkit.broadcastMessage(ConfigManager.getChatPrefix());
            Bukkit.broadcastMessage(" §7Spilleren §f" + p.getName() + " §7købte lige §f" + a + "x " + s + "§7 keys!");

        } else {
            p.sendMessage(ConfigManager.getChatPrefix());
            p.sendMessage("§7 Du har ikke nok coins til at købe dette.");
        }
    }
}

