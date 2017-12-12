/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.ConnectionManager;
import beans.Account;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author iaw26540084
 */
public class AccountsDAO {

    static Connection con = null;
    static Logger log = (Logger) LogManager.getLogger(AccountsDAO.class);

    public static final String IBAN = "iban";
    public static final String SALDO = "saldo";
    public static final String CLIENTE = "cliente";

    public static List<Account> getAccounts(String dni) {
        List<Account> accounts = new ArrayList<>();

        con = ConnectionManager.getConnection();

        PreparedStatement stat = null;

        ResultSet rs = null;

        try {
            Properties prop = new Properties();

            InputStream is = AccountsDAO.class.getClassLoader().getResourceAsStream("sql.properties");
            prop.load(is);

            stat = con.prepareStatement(prop.getProperty("getAccount"));

            stat.setString(1, dni);

            rs = stat.executeQuery();
//Account tmp;
            while (rs.next()) {
                /*tmp = new Account();
                tmp.setDni(rs.getString("cliente"));
                tmp.setSaldo(rs.getLong("saldo"));
                accounts.add(tmp);/*/

                accounts.add(new Account(rs.getString("iban"), rs.getLong("saldo"),
                        rs.getString("cliente")));

            }
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
        return accounts;
    }

    public static Account getAccount(String iban) {
        con = ConnectionManager.getConnection();

        PreparedStatement stat = null;
        Account account = null;

        ResultSet rs = null;

        try {
            Properties prop = new Properties();

            InputStream is = AccountsDAO.class.getClassLoader().getResourceAsStream("sql.properties");
            prop.load(is);

            stat = con.prepareStatement(prop.getProperty("getAccountDetails"));

            stat.setString(1, iban);

            rs = stat.executeQuery();
            while (rs.next()) {
                account = new Account(rs.getString("iban"), rs.getLong("saldo"),
                        rs.getString("cliente"));
            }
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
        return account;
    }

    public static boolean insertAccount(Account account) {
        boolean cuentaInsertada = false;
        con = ConnectionManager.getConnection();

        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            Properties prop = new Properties();

            InputStream is = AccountsDAO.class.getClassLoader().getResourceAsStream("sql.properties");
            prop.load(is);
            if (con != null) {
                stat = con.prepareStatement(prop.getProperty("accountInsert"));

                stat.setString(1, account.getIban());
                stat.setLong(2, account.getSaldo());
                stat.setString(3, account.getDni());

                stat.executeUpdate();
                cuentaInsertada = true;
            }

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
        return cuentaInsertada;
    }

    public static boolean deleteAccount(Account account) {

        boolean cuentaBorrada = false;

        con = ConnectionManager.getConnection();

        PreparedStatement stat = null;

        try {
            Properties prop = new Properties();

            InputStream is = AccountsDAO.class.getClassLoader().getResourceAsStream("sql.properties");
            prop.load(is);

            stat = con.prepareStatement(prop.getProperty("deleteAccount"));

            stat.setString(1, account.getIban());

            stat.executeUpdate();
            cuentaBorrada = true;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
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

        return cuentaBorrada;
    }

    public static String getLastIbanNumber() {
        con = ConnectionManager.getConnection();
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            Properties prop = new Properties();

            InputStream is = AccountsDAO.class.getClassLoader().getResourceAsStream("sql.properties");
            prop.load(is);

            stat = con.prepareStatement(prop.getProperty("genIban"));
            rs = stat.executeQuery();
            while (rs.next()) {
                return rs.getString(1).substring(2);
            }
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
        return "0";
    }

}
