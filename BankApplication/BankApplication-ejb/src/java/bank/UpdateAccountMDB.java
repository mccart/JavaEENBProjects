package bank;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;


@MessageDriven(mappedName = "jms/UpdateBalanceMDB", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
    })
public class UpdateAccountMDB implements MessageListener {
    
    @EJB BankMgr bankMgr;
    
    public UpdateAccountMDB() {
    }

    public void onMessage(Message message) {
        try {
            javax.jms.MapMessage mm = (javax.jms.MapMessage) message;
            int accountId = mm.getInt("acountId");
            double amount = mm.getDouble("amount");
            System.out.println("accountId = " + accountId + ", amount = " + amount);
            Account account = bankMgr.getAccount(accountId);
            System.out.println("balance before = " + account.getBalance());
            account.setBalance(account.getBalance() + amount);
            bankMgr.updateAccount(account);
            account = bankMgr.getAccount(accountId);
            System.out.println("balance after = " + account.getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
}
