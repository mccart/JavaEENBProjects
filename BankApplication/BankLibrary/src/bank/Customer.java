package bank;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {
    @Id
    private int userid;
    private String firstname;
    private String lastname;
    private String address;
    private String phone;
    private double balance;
    
    public Customer() {
    }

    public Customer(int userid, String firstname, String lastname, String address, String phone, double balance) {
        this.setUserid(userid);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setAddress(address);
        this.setPhone(phone);
        this.setBalance(balance);
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
}
