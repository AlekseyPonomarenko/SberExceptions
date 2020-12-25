package impl;

import java.math.BigDecimal;

public interface ServerAble {

    void putMoney(String userName, String userPassword, BigDecimal summ);

    void getMoney(String userName, String userPassword, BigDecimal summ);

    BigDecimal getBalance(String userName, String userPassword);
}
