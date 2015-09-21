package trader.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.ejb.*;
import trader.*;

public class SchedulerContextListener implements ServletContextListener {

    @EJB private StockMessageGeneratorRemote msgGen;
    
    /**
     */
    public void contextInitialized(ServletContextEvent evt) {
        ServletContext ctx = evt.getServletContext();
        String interval = ctx.getInitParameter("interval");
        
        if(interval == null) {
            interval = "10";
        }
        
        String delemitedStocks = ctx.getInitParameter("stocks");
        String[] stocks;
        if(delemitedStocks == null) {
            stocks = new String[1];
            stocks[0] = "SUNW";
        } else {
            stocks = delemitedStocks.split(",");
        }
        
        msgGen.scheduleTimer(stocks, interval);
    }

    public void contextDestroyed(ServletContextEvent evt) {
    }

}
