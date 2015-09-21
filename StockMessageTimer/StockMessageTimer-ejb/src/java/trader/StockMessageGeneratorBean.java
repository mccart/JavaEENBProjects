package trader;

import javax.ejb.*;
import javax.annotation.*;
import java.util.Collection;
import javax.jms.*;
import java.text.DecimalFormat;

@Stateless()
public class StockMessageGeneratorBean implements StockMessageGeneratorRemote {
    
    @Resource private SessionContext ctx;
    
    @Resource(mappedName="jms/UpdateStockFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName="jms/UpdateStock")
    private Queue queue;
    @EJB private BrokerModel model;
    
    public void scheduleTimer(String[] stocks, String seconds) {
        
        int millis = Integer.parseInt(seconds) * 1000;
        
        TimerService tService = ctx.getTimerService();
        Collection<Timer> timers = tService.getTimers();
        
        for(Timer timer: timers) {
            timer.cancel();
        }
   
        for(String stock : stocks) {
            Timer timer = tService.createTimer(0, millis, stock);
        }
            
    }
    
    public void cancelTimer() {
        TimerService tService = ctx.getTimerService();
        Collection<Timer> timers = tService.getTimers();
        
        for(Timer timer: timers) {
            timer.cancel();
        }  
    }
    
    @Timeout
    public void sendMessage(Timer timer) {
        String stk = (String)timer.getInfo();
        Stock stock = null;
        try {
            stock = model.getStock(stk);
        } catch(BrokerException be) {
            return;
        }
        double price = stock.getPrice();
        double percentChange = 0;
        switch((int)(Math.random() * 10 + 1)) {
            case 1: percentChange = -0.10; break;
            case 2: percentChange = -0.05; break;
            case 3: percentChange = -0.02; break;
            case 4: percentChange = 0; break;
            case 5: 
            case 6: percentChange = 0.02; break;
            case 7: 
            case 8: percentChange = 0.05; break;
            case 9: 
            case 10: percentChange = 0.10; break;
        }
        
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            messageProducer.setTimeToLive(300000);
            TextMessage message = session.createTextMessage();
            double newPrice = price + price * percentChange;
            DecimalFormat dFormat = new DecimalFormat("0.00");
            String text = stk + "," + dFormat.format(newPrice);
            message.setText(text);
            messageProducer.send(message);
            connection.close();
            System.out.println("Sent Message: " + text);
        } catch (JMSException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
