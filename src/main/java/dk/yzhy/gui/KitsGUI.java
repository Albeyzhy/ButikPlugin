package dk.yzhy.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dk.yzhy.Butik;
import dk.yzhy.utils.*;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
public class KitsGUI {
    static String PriceForKitHead;
    static String PriceForKitEnchanter;
    static String PriceForKitFarmer;
    static String PriceForKitKeys;
    public static void GUI(Player p) {
        if (p.hasPermission("essentials.kits.head")) {
            PriceForKitHead = "§8» §7Du ejer allerede dette!";
        } else {
            PriceForKitHead = "§8» §7Pris: §f" + Math.round(ConfigManager.getInt("Prices.KitHead") - (((double) ConfigManager.getInt("Prices.KitHead")) / 100) * (ConfigManager.getInt("Rabat.Kits")));
        }
        if (p.hasPermission("essentials.kits.Farmer")) {
            PriceForKitFarmer = "§8» §7Du ejer allerede dette!";
        } else {
            PriceForKitFarmer = "§8» §7Pris: §f" + Math.round(ConfigManager.getInt("Prices.KitFarmer") - (((double) ConfigManager.getInt("Prices.KitFarmer")) / 100) * (ConfigManager.getInt("Rabat.Kits")));
        }
        if (p.hasPermission("essentials.kits.Enchanter")) {
            PriceForKitEnchanter = "§8» §7Du ejer allerede dette!";
        } else {
            PriceForKitEnchanter = "§8» §7Pris: §f" + Math.round(ConfigManager.getInt("Prices.KitEnchanter") - (((double) ConfigManager.getInt("Prices.KitEnchanter")) / 100) * (ConfigManager.getInt("Rabat.Kits")));
        }
        if (p.hasPermission("essentials.kits.Keys")) {
            PriceForKitKeys = "§8» §7Du ejer allerede dette!";
        } else {
            PriceForKitKeys = "§8» §7Pris: §f" + Math.round(ConfigManager.getInt("Prices.KitKeys") - (((double) ConfigManager.getInt("Prices.KitKeys")) / 100) * (ConfigManager.getInt("Rabat.Kits")));
        }
        p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        Gui gui = Gui.gui()
                .title(Component.text(ConfigManager.getPrefix() + "§7 - Kits"))
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
        gui.setItem(19, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Kits.Head"))).name(Component.text("§4§lKIT HEAD")).setLore("", PriceForKitHead, "", "§8§l▎ §7I kit §4Head§7, har du", "§8§l▎ §75% chance for at få head hver dag.", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            HandleBuy.BuyProductKit("KitHead", p);
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        }));
        gui.setItem(21, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Kits.Farmer"))).name(Component.text("§6§lKIT FARMER")).setLore("", PriceForKitFarmer, "", "§8§l▎ §7I kit §6Farmer§7, får du", "§8§l▎ §7bread, wheat og chance for diamonds.", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            HandleBuy.BuyProductKit("KitFarmer", p);
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        }));
        gui.setItem(23, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Kits.Enchanter"))).name(Component.text("§9§lKIT ENCHANTER")).setLore("", PriceForKitEnchanter, "", "§8§l▎ §7I kit §9Enchanter§7, får du", "§8§l▎ §7lapis, exp bottles, diamonds og chance for anvil.", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            HandleBuy.BuyProductKit("KitEnchanter", p);
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        }));
        gui.setItem(25, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Kits.Keys"))).name(Component.text("§c§lKIT KEYS")).setLore("", PriceForKitKeys, "", "§8§l▎ §7I kit §cKeys§7, får du", "§8§l▎ §7jern, guld, diamond, twix, slik og chance for mint keys.", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            HandleBuy.BuyProductKit("KitKeys", p);
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        }));
        gui.open(p);
}}

