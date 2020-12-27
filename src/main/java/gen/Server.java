package gen;

import impl.ServerAble;
import impl.SumValidatorAble;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Server implements ServerAble {

    private Map<String, User> users;
    private MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

    private static ServerAble staticServer = null;
    private SumValidatorAble sumValidator;



    public Server() {
        //Первичная инициализация
        users = new HashMap();
        new User("Алексей", "1234567", new BigDecimal(1000, mc), users);
        new User("Иван", "1234", new BigDecimal(1000, mc), users);
        new User("Сергей", "1234", new BigDecimal(1000, mc), users);
        new User("1", "1", new BigDecimal(1000, mc), users);

        sumValidator = new SumValidator();

    }

    public static ServerAble getServer() {

        if (staticServer==null){
            staticServer = new Server();
        }
        return staticServer;
    }

    public void putMoney(String userName, String userPassword, BigDecimal summ) throws Throwable {

        authorization(userName, userPassword);

        User user = users.get(userName);
        BigDecimal balanceSumm = getBalance(userName, userPassword);
        user.setBalance(balanceSumm.add(summ, mc));
    }

    public void getMoney(String userName, String userPassword, BigDecimal summ) throws Throwable {

        authorization(userName, userPassword);

        //нужна валидация + авторизация
        User user = users.get(userName);
        BigDecimal balanceSumm = getBalance(userName, userPassword);

        if (balanceSumm.doubleValue()<summ.doubleValue()){
           throw new IllegalArgumentException("Недостаточно средств на счете");
        }

        user.setBalance(balanceSumm.subtract(summ, mc));

    }

    public BigDecimal getBalance(String userName, String userPassword) throws Throwable {
        authorization(userName, userPassword);
        User user = users.get(userName);
        return user.getBalance();
    }
    
    
    public boolean authorization(String userName, String userPassword) throws Throwable {

        User user = users.get(userName);
        String stringError="";

        if (user == null) {
            throw new IllegalAccessError("Такого пользователя нет!");
        }

        Calendar currentDate = new GregorianCalendar();
        if (user.getBlockDate().after(currentDate)){
            throw new IllegalAccessError("Ещё заблокировано: " + ((long) (user.getBlockDate().getTime().getTime() - currentDate.getTime().getTime())/1000));
        }

        int countOfIncorrectPass = user.getCountOfIncorrectPass();


        if (!user.getPassword().equals(userPassword)){
            stringError = "Авторизация не прошла";
            user.setCountOfIncorrectPass(++countOfIncorrectPass);

            if (countOfIncorrectPass >3){

                //Устанавливаем блокировку на 10 сек
              stringError = "Заблокировано на 10 секунд";

              Calendar blockDate = new GregorianCalendar();
              blockDate.add(Calendar.SECOND, 10);
              user.setBlockDate(blockDate);

            }

            throw new IllegalAccessError(stringError);
        };


        if (countOfIncorrectPass > 0){
            user.setCountOfIncorrectPass(0);
        }


        return true;
    }
    
    
}
