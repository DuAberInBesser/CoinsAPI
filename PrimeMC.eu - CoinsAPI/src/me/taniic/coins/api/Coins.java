package me.taniic.coins.api;

import me.taniic.coins.mysql.MySQL;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Coins {

    public static int getCoins(String uuid){
        try {
            PreparedStatement st = MySQL.con.prepareStatement("SELECT * FROM coins WHERE UUID = ?");
            st.setString(1, uuid);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return rs.getInt("coins");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public static void addCoins(String uuid, int coins){
        int current = getCoins(uuid);
        setCoins(uuid, coins+current);
    }

    public static void removeCoins(String uuid, int coins){
        int current = getCoins(uuid);
        setCoins(uuid, current-coins);
    }

    public static void setCoins(String uuid, int coins){
        if(getCoins(uuid) == -1){
            try {
                PreparedStatement st = MySQL.con.prepareStatement("INSERT INTO coins (UUID,coins) VALUES(?,?)");
                st.setString(1, uuid);
                st.setInt(2, coins);
                st.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            PreparedStatement st = null;
            try {
                st = MySQL.con.prepareStatement("UPDATE coins SET coins = ? WHERE UUID = ?");
                st.setString(2, uuid);
                st.setInt(1, coins);
                st.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
