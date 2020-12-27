package impl;

import java.math.BigDecimal;

public interface ServerAble {

    void putMoney(String userName, String userPassword, BigDecimal summ) throws Throwable;

    void getMoney(String userName, String userPassword, BigDecimal summ) throws Throwable;

    BigDecimal getBalance(String userName, String userPassword) throws Throwable;

    boolean authorization(String userName, String userPassword) throws Throwable;
}
