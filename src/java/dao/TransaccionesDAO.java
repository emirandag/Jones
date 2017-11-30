/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Transaccion;
import bd.ConnectionManager;
import beans.Account;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author iaw26540084
 */
public class TransaccionesDAO {

    public static final String ID = "id";
    public static final String FECHA = "fecha";
    public static final String CANTIDAD = "cantidad";
    public static final String ORIGEN = "origen";
    public static final String DESTINO = "destino";
    
    public static final String ENVIOS = "envios";
    public static final String RECIBIDOS = "recibidos";

    static Connection con = null;
    static Logger log = (Logger) LogManager.getLogger(AccountsDAO.class);

    public static boolean realizaTransaccion(String origen, String destino, long cantidad) {

        boolean transaccionRealizada = false;

        con = ConnectionManager.getConnection();

        PreparedStatement stat = null;

        try {
            Properties prop = new Properties();

            InputStream is = TransaccionesDAO.class.getClassLoader().getResourceAsStream("sql.properties");
            prop.load(is);
            if (con != null) {
                stat = con.prepareStatement(prop.getProperty("makeTransaction"));
                stat.setLong(1, cantidad);
                stat.setString(2, origen);
                stat.setString(3, destino);
                stat.executeUpdate();
                transaccionRealizada = true;
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }

        return transaccionRealizada;
    }

    public static HashMap<String, List<Transaccion>> listaTransacciones(String iban) {
        HashMap<String, List<Transaccion>> transacciones = new HashMap<>();
        ArrayList<Transaccion> envios = new ArrayList<>();
        ArrayList<Transaccion> recibidos = new ArrayList<>();
        con = ConnectionManager.getConnection();
        PreparedStatement stat = null;

        ResultSet rs = null;
        try {
            Properties prop = new Properties();

            InputStream is = AccountsDAO.class.getClassLoader().getResourceAsStream("sql.properties");
            prop.load(is);

            stat = con.prepareStatement(prop.getProperty("envioTransaccion"));
            stat.setString(1, iban);

            rs = stat.executeQuery();
            //envios
            while (rs.next()) {
                envios.add(new Transaccion(rs.getString(ID), rs.getString(FECHA), rs.getString(CANTIDAD), iban, rs.getString(DESTINO)));
            }

            stat = con.prepareStatement(prop.getProperty("reciboTransaccion"));
            stat.setString(1, iban);

            rs = stat.executeQuery();
            //reciboTransaccion
            while (rs.next()) {
                recibidos.add(new Transaccion(rs.getString(ID), rs.getString(FECHA), rs.getString(CANTIDAD), rs.getString(ORIGEN), iban));
            }

            transacciones.put(ENVIOS, envios);
            transacciones.put(RECIBIDOS, recibidos);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

            if (stat != null) {
                try {
                    stat.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

        }

        return transacciones;
    }

}
