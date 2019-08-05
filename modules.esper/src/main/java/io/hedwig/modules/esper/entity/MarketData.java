package io.hedwig.modules.esper.entity;



import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: patrick the demo event
 */
@Data
@NoArgsConstructor
@ToString
public class MarketData {

    private String ticker;
    private double price;
    private int volume;
    private long updateTime = new Date().getTime() ;

    public MarketData(String ticker, double price, int volume) {
        this.ticker = ticker;
        this.price = price;
        this.volume = volume;
    }
}
