package cn.com.njit.wd.provider.domain;

import org.hibernate.annotations.GenericGenerator;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by wangdi on 2017/5/16.
 */
@Entity
@Table(name = "T_TRADE_INFO")
public class TradeInfo {

    @Column(name = "TRADE_ID")
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String tradeId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "TRADE_TIME")
    private String tradeTime;

    @Column(name = "TRADE_MONEY")
    private String tradeMoney;

    @Column(name = "TRADE_TYPE")
    private String tradeType;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
}
