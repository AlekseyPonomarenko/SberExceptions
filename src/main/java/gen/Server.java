package gen;

import impl.ServerAble;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Server implements ServerAble {

    private Map<String, User> users;
    private MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

    private static ServerAble staticServer = null;

    public Server() {
        //Первичная инициализация
        users = new HashMap();
        new User("Алексей", "1234567", new BigDecimal(1000, mc), users);
        new User("Иван", "1234", new BigDecimal(1000, mc), users);
        new User("Сергей", "1234", new BigDecimal(1000, mc), users);
        new User("1", "1", new BigDecimal(1000, mc), users);
    }

    public static ServerAble getServer() {

        if (staticServer==null){
            staticServer = new Server();
        }
        return staticServer;
    }

    public void putMoney(String userName, String userPassword, BigDecimal summ) {
        //нужна валидация + авторизация
        User user = users.get(userName);
        BigDecimal balanceSumm = getBalance(userName, userPassword);
        user.setBalance(balanceSumm.add(summ, mc));
    }

    public void getMoney(String userName, String userPassword, BigDecimal summ) {

        //нужна валидация + авторизация
        User user = users.get(userName);
        BigDecimal balanceSumm = getBalance(userName, userPassword);
        user.setBalance(balanceSumm.subtract(summ, mc));

    }

    public BigDecimal getBalance(String userName, String userPassword) {
        User user = users.get(userName);
        return user.getBalance();
    }
}
