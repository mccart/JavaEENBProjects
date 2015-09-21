package bank;

import java.rmi.Remote;
import java.util.Collection;

public interface BankMgr {
    Customer getCustomer(int custId) throws BankException;

    Collection getCustomerByBalance(double amount) throws BankException;

    Customer getMyData() throws BankException;
    
    Collection getAccountsByCustomerId(int userId) throws BankException;
    
    Account getAccount(int accountId) throws BankException;
    
    void updateAccount(Account account) throws BankException;
}
