
package trader;

import javax.ejb.Remote;


/**
 * This is the business interface for StockMessageGenerator enterprise bean.
 */
@Remote
public interface StockMessageGeneratorRemote {
    void scheduleTimer(String[] stocks, String seconds);
    void cancelTimer();
}
