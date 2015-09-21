package bank;

import java.security.Principal;
import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Remote @Stateless
public class BankMgrSLSB implements BankMgr {
    
    @Resource private SessionContext context;
    @PersistenceContext private EntityManager em;

    public Customer getCustomer(int custId) throws BankException {
        Customer cust = em.find(Customer.class, custId);
        if(cust != null) {
            return cust;
        } else {
            throw new BankException("Customer not found");
        }
    }

    public Customer getMyData() throws BankException {
        Principal p = context.getCallerPrincipal();
        if(p == null) {
            throw new BankException("not logged in");
        }
        String userIdStr = p.getName();
        if(userIdStr.equalsIgnoreCase("guest") || userIdStr.equalsIgnoreCase("anonymous")) {
            throw new BankException("not logged in");
        }
        try {
            int userId = Integer.parseInt(userIdStr);
            return getCustomer(userId);
        } catch (NumberFormatException nfe) {
            throw new BankException("unrecognized user id");
        }
    }

    public Collection getCustomerByBalance(double amount) throws BankException {
        Query query = em.createNativeQuery("SELECT * FROM Customer WHERE balance > " + amount, Customer.class);
        return query.getResultList();
    }

    public void updateBalance(int custId, double amount) throws BankException {
        Customer cust = getCustomer(custId);
        double balance = cust.getBalance();
        cust.setBalance(balance + amount);
    }

    public Collection getAccountsByCustomerId(int userId) throws BankException {
        Query query = em.createNativeQuery("SELECT * FROM Account WHERE customerId = " + userId, Account.class);
        return query.getResultList();
    }

    public Account getAccount(int accountId) throws BankException {
        Account account = em.find(Account.class, accountId);
        if(account != null) {
            return account;
        } else {
            throw new BankException("Account not found");
        }
    }
        
    public void updateAccount(Account account) throws BankException {
        Account a = em.find(Account.class, account.getAccountId());
        if(a == null) {
            throw new BankException("Account : " + account.getAccountId() + " not found");
        } else {
            double balanceDiff = account.getBalance() - a.getBalance() ;
            try {
                em.merge(account);
                updateBalance(account.getCustomerId(), balanceDiff);
            } catch(OptimisticLockException ole) {
                throw new BankException("Account " + account.getAccountId() + " has been modified since retrieval");    
            }
        }
    }
    
}
