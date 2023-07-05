package dk.yzhy.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dk.yzhy.Butik;
import dk.yzhy.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class OtherGUI {
    static String PriceForVagt;
    static String PriceForVipCelle;
    static String PriceForBattlepass;
    public static void GUI(Player p) {
        if(p.hasPermission("cells.rent.vipcelle")){
            PriceForVipCelle = "§8» §7Du ejer allerede dette!";
        }else{
            PriceForVipCelle = "§8» §7Pris: §f" + Math.round(ConfigManager.getInt("Prices.VipCelle")-(((double) ConfigManager.getInt("Prices.VipCelle")) /100)*(ConfigManager.getInt("Rabat.Andet")));
        }
        if(p.hasPermission("battlepass.premium")){
            PriceForBattlepass = "§8» §7Du ejer allerede dette!";
        }else{
            PriceForBattlepass = "§8» §7Pris: §f" + Math.round(ConfigManager.getInt("Prices.Battlepass")-(((double) ConfigManager.getInt("Prices.Battlepass")) /100)*(ConfigManager.getInt("Rabat.Andet")));
        }
        String Groups = PlaceholderAPI.setPlaceholders(p, "%luckperms_primary_group_name%");
        if (Groups.contains("vagt") || Groups.contains("officer") || Groups.contains("inspektør") || Groups.contains("direktør")) {
            PriceForVagt = "§8» §7Du ejer allerede dette!";
        } else {
            PriceForVagt = "§8» §7Pris: §f" + Math.round(ConfigManager.getInt("Prices.Vagt")-(((double) ConfigManager.getInt("Prices.Vagt")) /100)*(ConfigManager.getInt("Rabat.Andet")));
        }
        p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
        Gui gui = Gui.gui()
                .title(Component.text(ConfigManager.getPrefix() + "§7 - Andet"))
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
        gui.setItem(20, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Andet.Vagt"))).name(Component.text("§e§lVAGT")).setLore("", PriceForVagt, "", "  §8§l▎ §7§lINFORMATION", "", "§8§l▎ §7Som vagt, skal du følge vagtreglerne", "§8§l▎ §7og kunne sidde i vagtcall, når du er online.", "§8§l▎ §7Du skal også være linket på discorden, inden du køber.", "", "§8§l▎ §7Hvis du bryder nogle regler,", "§8§l▎ §7har vi ret til at fjerne din vagtrank.", "", "  §8§l▎ §7§lBEMÆRK", "", "§8§l▎ §7I særtilfælde, er du ikke garanteret at kunne joine serveren,", "§8§l▎ §7hvis der er for mange vagter online.", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
            HandleBuy.BuyProductRank("VagtRank", p, Groups);
        }));
        gui.setItem(22, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Andet.VipCelle"))).name(Component.text("§2§lVIP CELLE")).setLore("", PriceForVipCelle, "", "§8§l▎ §7Vip cellerne ligger i en portal.", "§8§l▎ §7Du får automatisk din celle.", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
            HandleBuy.BuyProductVipCelle(p);
        }));
        gui.setItem(24, ItemBuilder.from(GetHead.getHead(ConfigManager.getString("Heads.Andet.Battlepass"))).name(Component.text("§6§lBATTLEPASS")).setLore("", PriceForBattlepass, "", "§8§l▎ §7Køb premium versionen af", "§8§l▎ §7vores battlepass for fordele.", "", "§8§l▎ §7Du mister premium status ved sæsonskift!", "§8§l▎ §7For mere information se §b/bp§7.", "", "§8§l【 §7Klik for at købe. §8§l】").asGuiItem(event -> {
            p.playSound(p.getLocation(), Sound.CLICK, 1, 10);
            HandleBuy.BuyProductBattlepass(p);
        }));
        gui.open(p);
}}
