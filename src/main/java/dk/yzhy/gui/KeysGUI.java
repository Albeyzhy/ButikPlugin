package dk.yzhy.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dk.yzhy.Butik;
import dk.yzhy.utils.BuyCoins;
import dk.yzhy.utils.CoinsUtils;
import dk.yzhy.utils.ConfigManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class KeysGUI {
    public static void GUI(Player p) {
        p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        Gui gui = Gui.gui()
                .title(Component.text(ConfigManager.getPrefix() + "§7 - Keys"))
                .rows(5)
                .disableAllInteractions()
                .create();
        gui.getFiller().fillTop(ItemBuilder.from(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1)).name(Component.text("§f")).asGuiItem());
        gui.getFiller().fillBottom(ItemBuilder.from(new ItemStack(Material.STAINED_GLASS_PANE)).name(Component.text("§f")).asGuiItem());
        gui.setItem(4, ItemBuilder.from(Material.GOLD_NUGGET).name(Component.text("§e§lKØB COINS")).setLore("", "§8§l▎ §7Her kan du anmode", "§8§l▎ §7om at købe coins.", "§8§l▎ §71 em = 1 coin", "", "§8» §7Dine coins: §f" + CoinsUtils.getCoins(p), "", "§8§l【 §7Højreklik for bruge staff/paybot. §8§l】", "§8§l【 §7Højre klik for at bruge §2§lUNIKPAY§7. §8§l】").asGuiItem(event -> {
            if(event.isLeftClick()) {
                BuyCoins.SendCoinsMessage(p);
                p.closeInventory();
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 En administrator eller højere vil snart kontakte dig, vent venligst.");
                p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
            } else if (event.isRightClick()) {
                p.closeInventory();
                p.sendMessage(ConfigManager.getChatPrefix());
                p.sendMessage("§7 Skriv antal coins, du vil købe i chatten.");
                p.setMetadata("BuyCoins", new FixedMetadataValue(Butik.getInstance(), true));
            }
        }));
        ItemStack back = new ItemStack(Material.INK_SACK,1, (short) 1);
        gui.setItem(36, ItemBuilder.from(back).name(Component.text("§fTilbage.")).asGuiItem(event -> {
            MainGUI.GUI(p);
        }));
        gui.setItem(20, ItemBuilder.from(Material.RECORD_4).name(Component.text("§c§lREDSTONE KEY")).setLore("", "§8» §7Pris: §f" + (ConfigManager.getInt("Prices.KeyRedstone") - (ConfigManager.getInt("Prices.KeyRedstone") / 100 * ConfigManager.getInt("Rabat.Keys"))), "", "§8§l▎ §7Denne key indeholder rare items,", "§8§l▎ §7som du kan få i §c§lREDSTONE§7 craten.", "§8§l▎ §7Du kan se craten i casino.", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
            p.closeInventory();
            p.setMetadata("ChatButik",  new FixedMetadataValue(Butik.getInstance(), "Redstone"));
            p.sendMessage(ConfigManager.getChatPrefix());
            p.sendMessage("§7 Skriv antallet du vil købe i chatten, annuller ved at skrive noget andet!");
        }));
        gui.setItem(22, ItemBuilder.from(Material.RECORD_6).name(Component.text("§b§lMINT KEY")).setLore("", "§8» §7Pris: §f" + (ConfigManager.getInt("Prices.KeyMint") - (ConfigManager.getInt("Prices.KeyMint") / 100 * ConfigManager.getInt("Rabat.Keys"))), "", "§8§l▎ §7Denne key indeholder dbs og penge,", "§8§l▎ §7som du kan få i §b§lMINT§7 craten.", "§8§l▎ §7Du kan se craten i casino.", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
            p.closeInventory();
            p.setMetadata("ChatButik",  new FixedMetadataValue(Butik.getInstance(), "Mint"));
            p.sendMessage(ConfigManager.getChatPrefix());
            p.sendMessage("§7 Skriv antallet du vil købe i chatten, annuller ved at skrive noget andet!");
        }));
        gui.setItem(24, ItemBuilder.from(Material.RECORD_12).name(Component.text("§9§lLAPIS KEY")).setLore("", "§8» §7Pris: §f" + (ConfigManager.getInt("Prices.KeyLapis") - (ConfigManager.getInt("Prices.KeyLapis") / 100 * ConfigManager.getInt("Rabat.Keys"))), "", "§8§l▎ §7Denne key indeholder seje items,", "§8§l▎ §7som du kan få i §9§lLAPIS§7 craten.", "§8§l▎ §7Du kan se craten i casino.", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
            p.closeInventory();
            p.setMetadata("ChatButik",  new FixedMetadataValue(Butik.getInstance(), "Lapis"));
            p.sendMessage(ConfigManager.getChatPrefix());
            p.sendMessage("§7 Skriv antallet du vil købe i chatten, annuller ved at skrive noget andet!");
        }));
        gui.open(p);
}}

