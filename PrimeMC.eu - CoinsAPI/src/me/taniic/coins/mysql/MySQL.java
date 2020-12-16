package me.taniic.coins.mysql;

import me.taniic.coins.CoinsAPI;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    public static String host = "localhost";
    public static String port = "3306";
    public static String database = "LobbySystem";
    public static String username = "saintrene";
    public static String password = "QuEdArEnE2002";

    public static Connection con;

    public static void connect(){
        if(!isConnected()){
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + port + database + "?autoReconnect=true", username, "");
                Bukkit.getConsoleSender().sendMessage(CoinsAPI.prefix + "§aMySQL verbunden!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void disconnect(){
        if(isConnected()){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Bukkit.getConsoleSender().sendMessage(CoinsAPI.prefix + "§cMySQL getrennt!");
        }

    }

    public static boolean isConnected(){
        return (con != null);
    }

    public static void createTable(){
        try {
            con.prepareStatement("CREATE TABLE IF NOT EXISTS coins (UUID VARCHAR(100), coins INT(16))").executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
