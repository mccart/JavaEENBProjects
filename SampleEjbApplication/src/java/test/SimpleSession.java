/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.ejb.*;

/**
 *
 * @author mccart
 */
@Stateless //@Remote
public class SimpleSession implements SimpleSessionRemote {

    public String getMessage() {
        return "Hello EJB World - Yay";
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
