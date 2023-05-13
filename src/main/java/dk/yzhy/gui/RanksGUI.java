package dk.yzhy.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dk.yzhy.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
public class RanksGUI {
    static String PriceForMint;
    static String PriceForSlik;
    static String PriceForTwix;
    public static void GUI(Player p) {
        String Groups = PlaceholderAPI.setPlaceholders(p, "%luckperms_groups%");
        if (Groups.contains("twix")) {
            PriceForTwix = "§8» §7Du ejer allerede dette!";
            PriceForSlik = "§8» §7Pris: §f" + "§m" + Math.round((ConfigManager.getInt("Prices.Slik")) - ((double) ConfigManager.getInt("Prices.Slik") / 100 * (ConfigManager.getInt("Rabat.Ranks")))) + "§r " + Math.round((ConfigManager.getInt("Prices.Slik")-ConfigManager.getInt("Prices.Twix"))-((double) (ConfigManager.getInt("Prices.Slik")-ConfigManager.getInt("Prices.Twix")) /100*(ConfigManager.getInt("Rabat.Ranks"))));
            PriceForMint = "§8» §7Pris: §f" + "§m" + Math.round((ConfigManager.getInt("Prices.Mint")) - ((double) ConfigManager.getInt("Prices.Mint") / 100 * (ConfigManager.getInt("Rabat.Ranks")))) + "§r " + Math.round((ConfigManager.getInt("Prices.Mint")-ConfigManager.getInt("Prices.Twix"))-((double) (ConfigManager.getInt("Prices.Mint")-ConfigManager.getInt("Prices.Twix")) /100*(ConfigManager.getInt("Rabat.Ranks"))));
        } else if (Groups.contains("slik")) {
            PriceForTwix = "§8» §7Du ejer allerede dette!";
            PriceForSlik = "§8» §7Du ejer allerede dette!";
            PriceForMint = "§8» §7Pris: §f" + "§m" + Math.round((ConfigManager.getInt("Prices.Mint")) - ((double) ConfigManager.getInt("Prices.Mint") / 100 * (ConfigManager.getInt("Rabat.Ranks")))) + "§r " + Math.round((ConfigManager.getInt("Prices.Mint") - ConfigManager.getInt("Prices.Slik")) - ((double) (ConfigManager.getInt("Prices.Mint") - ConfigManager.getInt("Prices.Slik")) / 100 * (ConfigManager.getInt("Rabat.Ranks"))));
        }  else if (Groups.contains("mint")) {
            PriceForTwix = "§8» §7Du ejer allerede dette!";
            PriceForSlik = "§8» §7Du ejer allerede dette!";
            PriceForMint = "§8» §7Du ejer allerede dette!";
        } else {
            PriceForMint = "§8» §7Pris: §f" + Math.round(ConfigManager.getInt("Prices.Mint") - (((double) ConfigManager.getInt("Prices.Mint")) / 100) * (ConfigManager.getInt("Rabat.Ranks")));
            PriceForSlik = "§8» §7Pris: §f" + Math.round(ConfigManager.getInt("Prices.Slik") - (((double) ConfigManager.getInt("Prices.Slik")) / 100) * (ConfigManager.getInt("Rabat.Ranks")));
            PriceForTwix = "§8» §7Pris: §f" + Math.round(ConfigManager.getInt("Prices.Twix") - (((double) ConfigManager.getInt("Prices.Twix")) / 100) * (ConfigManager.getInt("Rabat.Ranks")));
        }
        p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        Gui gui = Gui.gui()
            .title(Component.text(ConfigManager.getPrefix() + "§7 - Ranks"))
            .rows(5)
            .disableAllInteractions()
            .create();
        gui.getFiller().fillTop(ItemBuilder.from(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1)).name(Component.text("§f")).asGuiItem());
        gui.getFiller().fillBottom(ItemBuilder.from(new ItemStack(Material.STAINED_GLASS_PANE)).name(Component.text("§f")).asGuiItem());
        gui.setItem(4, ItemBuilder.from(Material.GOLD_NUGGET).name(Component.text("§e§lKØB COINS")).setLore("", "§8§l▎ §7Her kan du anmode", "§8§l▎ §7om at købe coins.", "§8§l▎ §71 em = 1 coin", "", "§8» §7Dine coins: §f" + CoinsUtils.getCoins(p), "", "§8§l【 §7Klik for at anmode. §8§l】").asGuiItem(event -> {
            BuyCoins.SendCoinsMessage(p);
            p.closeInventory();
            p.sendMessage(ConfigManager.getChatPrefix());
            p.sendMessage("§7 En administrator eller højere vil snart kontakte dig, vent venligst.");
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        }));
        ItemStack back = new ItemStack(Material.INK_SACK,1, (short) 1);
        gui.setItem(36, ItemBuilder.from(back).name(Component.text("§fTilbage.")).asGuiItem(event -> {
            MainGUI.GUI(p);
        }));
        gui.setItem(20, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Ranks.Twix"))).name(Component.text("§a§lTWIX")).setLore("", PriceForTwix, "", "§8§l▎ §7Som §aTwix§7, får du adgang til:", "", "§8§l▎ §7Et §a§lTWIX§7 prefix", "§8§l▎ §7Adgang til donator shop, mine og celler", "§8§l▎ §7Adgang til /eat (2m cooldown)", "§8§l▎ §7Adgang til kit twix (1t cooldown)", "§8§l▎ §7Adgang til kit twix+ (24t cooldown)", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            HandleBuy.BuyProductRank("TwixRank", p, Groups);
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        }));
        gui.setItem(22, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Ranks.Slik"))).name(Component.text("§6§lSLIK")).setLore("", PriceForSlik, "", "§8§l▎ §7Som §6Slik§7, får du adgang til:", "", "§8§l▎ §7Et §6§lSLIK§7 prefix", "§8§l▎ §7Adgang til donator shop, mine og celler", "§8§l▎ §7Adgang til /eat (2m cooldown)", "§8§l▎ §7Adgang til kit slik (1t cooldown)", "§8§l▎ §7Adgang til kit slik+ (24t cooldown)", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            HandleBuy.BuyProductRank("SlikRank", p, Groups);
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        }));
        gui.setItem(24, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Ranks.Mint"))).name(Component.text("§b§lMINT")).setLore("", PriceForMint, "", "§8§l▎ §7Som §bMint§7, får du adgang til:", "", "§8§l▎ §7Et §b§lMINT§7 prefix", "§8§l▎ §7Adgang til donator shop, mine og celler", "§8§l▎ §7Adgang til /eat (2m cooldown)", "§8§l▎ §7Adgang til kit mint (1t cooldown)", "§8§l▎ §7Adgang til kit mint+ (24t cooldown)", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            HandleBuy.BuyProductRank("MintRank", p, Groups);
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        }));
        gui.open(p);
}}
