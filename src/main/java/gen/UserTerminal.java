package gen;

import impl.ServerAble;
import impl.SumValidatorAble;
import impl.UserTerminalAble;

import java.math.BigDecimal;

public class UserTerminal implements UserTerminalAble {

    private ServerAble server = null;
    private String userName;
    private String userPassword;

    private SumValidatorAble sumValidator;

    public void start() {
        server = Server.getServer();
        System.out.println("start(" + server + ")");
        sumValidator = new SumValidator();
    }
    public boolean authorization(String userName, String userPassword) throws Throwable {

      return server.authorization(userName, userPassword);

    }

    public void putMoney(String userName, String userPassword, BigDecimal summ) throws Throwable {

        String validateSumm = sumValidator.validateSummForTerminal(summ);
        if (!validateSumm.equals("")){
            throw new IllegalArgumentException(validateSumm);
        };

        server.putMoney(userName, userPassword, summ);
    }

    public void getMoney(String userName, String userPassword, BigDecimal summ) throws Throwable{

        String validateSumm = sumValidator.validateSummForTerminal(summ);

        if (!validateSumm.equals("")){
            throw new IllegalArgumentException(validateSumm);
        };

        server.getMoney(userName, userPassword, summ);
    }

    public void stop() {
        System.out.println("stop(" + server + ")");
        server = null;
    }

    public BigDecimal getBalance(String userName, String userPassword) throws Throwable{
        return server.getBalance(userName, userPassword);
    }
}
