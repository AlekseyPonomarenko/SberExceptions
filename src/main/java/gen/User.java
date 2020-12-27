package gen;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class User {
    private int countOfIncorrectPass = 0;

    private Calendar blockDate;

    private BigDecimal balance;
    private String name;
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public User(String name, String password, BigDecimal balance, Map<String, User> map) {
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.blockDate = new GregorianCalendar();
        map.put(name, this);

    }

    public int getCountOfIncorrectPass() {
        return countOfIncorrectPass;
    }

    public void setCountOfIncorrectPass(int countOfIncorrectPass) {
        this.countOfIncorrectPass=countOfIncorrectPass;
    }


    public Calendar getBlockDate() {
        return blockDate;
    }

    public void setBlockDate(Calendar blockDate) {
        this.blockDate=blockDate;
    }
}
