package gen;

import impl.ServerAble;
import impl.SumValidatorAble;
import impl.UserTerminalAble;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Scanner;

public class UserTerminal implements UserTerminalAble {

    private ServerAble server = null;
    private String userName;
    private String userPassword;
    private boolean validate = false;
    Scanner in;

    private SumValidatorAble sumValidator;

    public void start() {
        server = Server.getServer();
        System.out.println("start(" + server + ")");
        in = new Scanner(System.in);

        sumValidator = new SumValidator();
    }

    public void balance() {
        BigDecimal summ = server.getBalance(userName, userPassword);
        System.out.println("Ваш баланс:" + summ);
    }

    public void authorization() {

        System.out.println("Авторизация!");
        System.out.print("Введите логин: ");
        userName = in.nextLine();

        System.out.print("Введите пароль: ");
        userPassword = in.nextLine();
        System.out.println("Добро пожаловать " + userName);
        validate = true;
    }

    public void putMoney() {

        System.out.println("Внесение суммы!");
        System.out.print("Введите сумму: ");

        BigDecimal summ = in.nextBigDecimal();

        try {
            sumValidator.validateSummForTerminal(summ);
        }
        catch (IllegalArgumentException e) {
            putMoney();
            return;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        server.putMoney(userName, userPassword, summ);

    }

    public void getMoney() {

        System.out.println("Получение суммы!");
        System.out.print("Введите сумму: ");
        BigDecimal summ = in.nextBigDecimal();

        try {
            sumValidator.validateSummForTerminal(summ);
        }
        catch (IllegalArgumentException e) {
            getMoney();
            return;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        server.getMoney(userName, userPassword, summ);

    }

    public void stop() {
        logout();
        System.out.println("stop(" + server + ")");
        server = null;

        in.close();
    }

    public void logout() {

        if (validate){
            System.out.printf("До свидания: %s!%n", userName);
        }
        userName = null;
        userPassword=null;
        validate = false;
    }

}
