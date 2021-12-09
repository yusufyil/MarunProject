package com.example.marunproject;
import com.sun.jdi.connect.Connector;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class Database {
    public static Connection getConnection(){
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/javadb?characterEncoding=utf8&useConfigs=maxPerformance";
            String userName = "root";
            String password = "Y46513y";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,userName,password);
            System.out.println("Database bağlantısı kuruldu");
            return conn;
        }catch (Exception e){
            System.out.println("Bir hata oluştu\n" + e);
        }
        return null;
    }

    public static void post(){
        final int var0 = 42;
        final String var1 = "Yusuf";
        final String var2 = "Yıldırım";
        try{
            Connection conn = getConnection();
            PreparedStatement posted = conn.prepareStatement("INSERT INTO test(no, ad, soyad) VALUES ('"+var0+"','"+var1+"','"+var2+"')");
            posted.executeUpdate();
            conn.close();
        }catch (Exception e){
            System.out.println("Bir hata oluştu.\n" + e);
        }
        finally {
            System.out.println("işlem bitti.");
        }
    }
    public static ArrayList<String> get(){
        try{
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT no, ad, soyad FROM test ");
            ResultSet result = statement.executeQuery();
            ArrayList<String> array = new ArrayList<String>();
            while (result.next()){
                System.out.print(result.getInt("no"));
                System.out.print(" ");
                System.out.print(result.getString("ad"));
                System.out.print(" ");
                System.out.println(result.getString("soyad"));
                array.add(result.getString("soyad"));
            }
            System.out.println("her sey secildi");
            return array;
        }catch (Exception e){
            System.out.println("Bir hata olustu.\n" + e);
        }
        return null;
    }
}
