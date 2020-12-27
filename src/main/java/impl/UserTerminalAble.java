package impl;

import java.math.BigDecimal;

public interface UserTerminalAble {
    void start();
    boolean authorization(String userName, String userPassword) throws Throwable;
    void putMoney(String userName, String userPassword, BigDecimal summ)throws Throwable;
    void getMoney(String userName, String userPassword, BigDecimal summ)throws Throwable;
    void stop();
    BigDecimal getBalance(String userName, String userPassword) throws Throwable;
}
