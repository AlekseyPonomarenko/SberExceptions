package run;

import gen.UserTerminal;
import impl.UserTerminalAble;

public class Main {
    public static void main(String[] args) {

        UserTerminalAble userTerminal = new UserTerminal();

        userTerminal.start();
        userTerminal.authorization();
        userTerminal.balance();
        userTerminal.putMoney();
        userTerminal.balance();
        userTerminal.getMoney();
        userTerminal.balance();
        userTerminal.logout();
        userTerminal.stop();

    }
}
