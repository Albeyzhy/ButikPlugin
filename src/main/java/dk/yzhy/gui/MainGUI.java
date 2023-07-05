package dk.yzhy.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dk.yzhy.Butik;
import dk.yzhy.utils.CoinsUtils;
import dk.yzhy.utils.ConfigManager;
import dk.yzhy.utils.GetHead;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import dk.yzhy.utils.BuyCoins;
import org.bukkit.metadata.FixedMetadataValue;

public class MainGUI {
    public static void GUI(Player p) {
        p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        Gui gui = Gui.gui()
                .title(Component.text(ConfigManager.getPrefix() + "§7 - Menu"))
                .rows(5)
                .disableAllInteractions()
                .create();
        gui.getFiller().fillTop(ItemBuilder.from(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1)).name(Component.text("§f")).asGuiItem());
        gui.getFiller().fillBottom(ItemBuilder.from(new ItemStack(Material.STAINED_GLASS_PANE)).name(Component.text("§f")).asGuiItem());
        ItemStack back = new ItemStack(Material.INK_SACK,1, (short) 1);
        gui.setItem(36, ItemBuilder.from(back).name(Component.text("§fLuk menu.")).asGuiItem(event -> {
            event.getWhoClicked().closeInventory();
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        }));
        gui.setItem(4, ItemBuilder.from(Material.GOLD_NUGGET).name(Component.text("§e§lKØB COINS")).setLore("", "§8§l▎ §7Her kan du anmode", "§8§l▎ §7om at købe coins.", "§8§l▎ §71 em = 1 coin", "", "§8» §7Dine coins: §f" + CoinsUtils.getCoins(p), "", "§8§l【 §7Højre klik for at anmode med staff. §8§l】", "§8§l【 §7Højre klik for at anmode med unikpay. §8§l】").asGuiItem(event -> {

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
        gui.setItem(19, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Ranks.Preview"))).name(Component.text("§d§lRANKS")).setLore("", "§8§l▎ §7Her kan du se ranks,", "§8§l▎ §7og hvilke fordele de har.", "", "§8» §7Rabat: §f" + ConfigManager.getString("Rabat.Ranks") + "%", "", "§8§l【 §7Klik for at åbne. §8§l】").asGuiItem(event -> {
            RanksGUI.GUI(p);
        }));
        gui.setItem(21, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Kits.Preview"))).name(Component.text("§2§lKITS")).setLore("", "§8§l▎ §7Her kan du se kits,", "§8§l▎ §7og hvilke fordele de har.", "", "§8» §7Rabat: §f" + ConfigManager.getString("Rabat.Kits") + "%", "", "§8§l【 §7Klik for at åbne. §8§l】").asGuiItem(event -> {
            KitsGUI.GUI(p);
        }));
        gui.setItem(23, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Keys.Preview"))).name(Component.text("§b§lKEYS")).setLore("", "§8§l▎ §7Her kan du se keys,", "§8§l▎ §7og hvilke fordele de har.", "", "§8» §7Rabat: §f" + ConfigManager.getString("Rabat.Keys") + "%", "", "§8§l【 §7Klik for at åbne. §8§l】").asGuiItem(event -> {
            KeysGUI.GUI(p);
        }));
        gui.setItem(25, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Andet.Preview"))).name(Component.text("§f§lANDET")).setLore("", "§8§l▎ §7Her kan du se andet,", "§8§l▎ §7og hvilke fordele de har.", "", "§8» §7Rabat: §f" + ConfigManager.getString("Rabat.Andet")+ "%", "", "§8§l【 §7Klik for at åbne. §8§l】").asGuiItem(event -> {
            OtherGUI.GUI(p);
        }));
        gui.open(p);
}}
