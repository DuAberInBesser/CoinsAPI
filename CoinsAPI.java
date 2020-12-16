package me.taniic.coins;

import me.taniic.coins.mysql.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

public class CoinsAPI extends JavaPlugin {

    public static String prefix = "§8•§7● §b§lPrime§3§lMC §8× §r";

    @Override
    public void onEnable() {
        MySQL.connect();
        MySQL.createTable();
    }

    @Override
    public void onDisable() {
        MySQL.disconnect();
    }
}
