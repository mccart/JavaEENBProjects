package bank;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class MySessionBean implements MyInterface {
    
    @EJB private BankMgr bankMgr;
    @Resource private SessionContext context;

    public void myMethod() {
        BankMgr bankMgr = (BankMgr)context.lookup("BankMgr");
    }

    public String getString() {
        return "MySessionBean says Hello!";
    }
    
    
}
