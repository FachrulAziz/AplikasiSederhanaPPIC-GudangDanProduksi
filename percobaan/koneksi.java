/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package percobaan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */

    class koneksi {
    private static Connection Koneksi;
            
            public static Connection getConnection() throws SQLException {
        String db = "jdbc:mysql://localhost:3306/test";
        String user ="root";
        String pass = "";
        
        if (Koneksi == null) {
            Koneksi = DriverManager.getConnection(db, user, pass);
        }
        return Koneksi;
    }

    static Object createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

