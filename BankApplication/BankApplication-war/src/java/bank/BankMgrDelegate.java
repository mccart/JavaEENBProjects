package bank;

import javax.ejb.*;
import javax.rmi.*;
import java.rmi.*;
import javax.naming.*;
import java.util.*;

public class BankMgrDelegate {
    
    /** Creates a new instance of BankMgrDelegate */
    public BankMgrDelegate() {
    }

    private BankMgr bankMgr = null;

    public void init() throws BankException {
        try {
            Context ctx = new InitialContext();
            Object obj = ctx.lookup("java:comp/env/ejb/BankMgr");
            bankMgr = (BankMgr) PortableRemoteObject.narrow(obj, BankMgr.class);
        } catch (Exception e) {
            throw new BankException(e.getMessage());
        }
    }
    
    public Customer getCustomer(int userId) throws BankException {
        Customer cd = bankMgr.getCustomer(userId);
        return cd;
    }
    
    public Customer getMyData() throws BankException {
        Customer cd = bankMgr.getMyData();
        return cd;
    }
    
    public Collection getCustomerDataByBalance(double amount) throws BankException {
        Collection customers = bankMgr.getCustomerByBalance(amount);
        return customers;     
    }
    
    public Collection getAccountsByCustomerId(int userId) throws BankException {
        Collection accounts = bankMgr.getAccountsByCustomerId(userId);
        return accounts;     
    }
    
    public void updateAccount(Account account) throws BankException {
        bankMgr.updateAccount(account);   
    }
}
