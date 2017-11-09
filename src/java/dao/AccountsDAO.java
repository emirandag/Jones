/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Account;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author iaw26540084
 */
public class AccountsDAO {
    
    static Connection con = null;
    static Logger log = (Logger) LogManager.getLogger(AccountsDAO.class);
    
    public static List<Account> getAccounts(String dni) {
        List<Account> accounts = new ArrayList<>();
        return accounts;
    }
    
}
