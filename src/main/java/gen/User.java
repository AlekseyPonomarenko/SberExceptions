package gen;

import java.math.BigDecimal;
import java.util.Map;

public class User {
    private String name;
    private String password;
    private BigDecimal balance;

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

        map.put(name, this);

    }


}
