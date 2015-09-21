package bank;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class ServletEndpoint {
    
    @WebMethod
    public String getCustomerName(int id) {
        try {
            BankMgrDelegate bankMgrDelegate = new BankMgrDelegate();
            bankMgrDelegate.init();
            Customer customer = bankMgrDelegate.getCustomer(id);
            return customer.getFirstname() + " " + customer.getLastname();
        } catch (Exception ex) {
            return "Not Found";
        }
    }
    
}
