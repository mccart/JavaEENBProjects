/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trader;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.*;

/**
 *
 * @author mccart
 */
public class BrokerDelegate {

    //private BrokerModel model = BrokerModelImpl.getInstance();
    // ejb lab session...
    private BrokerModel model;
    
    // Singleton design - instantiated only once with empty constructor
    private static BrokerDelegate instance = new BrokerDelegate();
    
    public static BrokerDelegate getInstance() {
        return instance;
    }
    
    private BrokerDelegate() {
        // added for ejb lab session...
        try {
            Context ctx = new InitialContext();
            model = (BrokerModel) ctx.lookup("java:comp/env/BrokerLookup");
        } catch (NamingException ex) {
            Logger.getLogger(BrokerDelegate.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
        
    public Customer addCustomer(String customerId, String name, String address) throws BrokerException {
        model.addCustomer(new Customer(customerId, name, address));
        return model.getCustomer(customerId);
    }

    public Customer getCustomer(String customerId) throws BrokerException {
        return model.getCustomer(customerId);
    }

    public Customer updateCustomer(String customerId, String name, String address) throws BrokerException {
        Customer cust = model.getCustomer(customerId);
        cust.setName(name);
        cust.setAddr(address);
        model.updateCustomer(cust);
        return cust;
    }
    
    public void deleteCustomer(String customerId) throws BrokerException {
        model.deleteCustomer(model.getCustomer(customerId));
    }

    public Customer[] getAllCustomers() throws BrokerException {
        return model.getAllCustomers();
    }
   
    public Stock getStock(String symbol) throws BrokerException {
        return model.getStock(symbol);
    }
    
    public Stock[] getAllStocks() throws BrokerException {
        return model.getAllStocks();
    }
        
    public CustomerShare addCustomerShare(String customerId, String stockSymbol, int quantity) throws BrokerException {
        CustomerShare share = new CustomerShare(customerId, stockSymbol, quantity);
        model.addCustomerShare(share);
        return share;
    }

    public CustomerShare updateCustomerShare(String customerId, String stockSymbol, int quantity) throws BrokerException {
        CustomerShare share = new CustomerShare(customerId, stockSymbol, quantity);
        model.updateCustomerShare(share);
        return share;
    }
             
    public CustomerShare[] getAllCustomerShares(String customerId) throws BrokerException {
        return model.getAllCustomerShares(customerId);
    }

}
