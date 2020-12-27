package run;

import gen.UserTerminal;
import impl.UserTerminalAble;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Throwable {

        UserTerminalAble userTerminal = new UserTerminal();
        userTerminal.start();

        Scanner in;
        in=new Scanner(System.in);
        boolean authorizationComplete = false;

        String userName = "";
        String userPassword="";

        while (authorizationComplete==false){

            System.out.print("Введите логин: ");
            userName=in.nextLine();

            System.out.print("Введите пароль: ");
            userPassword=in.nextLine();

            try {
                authorizationComplete = userTerminal.authorization(userName, userPassword);

            }
            catch (IllegalAccessError e){
                System.out.println(e.getMessage());
            }
            catch (Throwable throwable) {
               throw throwable;
            }

            if (authorizationComplete == false){
                System.out.println("Попробовать снова авторизоваться (д - да/ н - нет)?");
                String res = in.nextLine();
                if (res.equals("н")){
                    break;
                }
            }
        }

        if (authorizationComplete == false) {
            stop(in, userTerminal);
            return;
        }
        System.out.println("Добро пожаловать " + userName);

        //switch использовать не хочу
        while (true){

            showMenu();
            String res=in.nextLine();

            //баланс
            if (res.equals("1")) {
                BigDecimal summ = userTerminal.getBalance(userName, userPassword);
                System.out.println("Ваш баланс:" + summ);
            }

            //2. Снятие денег.
            if (res.equals("2")) {
                System.out.println("Снятие суммы!");
                System.out.print("Введите сумму: ");
                BigDecimal summ=in.nextBigDecimal();
                res=in.nextLine();

                try {
                   userTerminal.getMoney(userName, userPassword, summ);
                } catch (IllegalArgumentException e) {

                    System.out.println(e.getMessage());

                } catch (Throwable throwable) {
                    throw throwable;
                }
            }

            //3. Внесение денег.
            if (res.equals("3")) {

                System.out.println("Внесение суммы!");
                System.out.print("Введите сумму: ");
                BigDecimal summ = in.nextBigDecimal();
                res=in.nextLine();

                try {
                    userTerminal.putMoney(userName, userPassword, summ);
                }
                catch (IllegalArgumentException e) {

                    System.out.println(e.getMessage());

                } catch (Throwable throwable) {
                    throw throwable;
                }
            }

            if (res.equals("0")) {
                break;
            }
        }

        if (authorizationComplete ) {
            System.out.println("Досвидания " + userName);
        }

        stop(in, userTerminal);
    }

    public static void showMenu() {
        System.out.println("Меню:");
        System.out.println("1. Показать баланс.");
        System.out.println("2. Снятие денег.");
        System.out.println("3. Внесение денег.");
        System.out.println("0. Выход.");
    }

    public static void stop(Scanner in, UserTerminalAble userTerminal) {
        in.close();
        userTerminal.stop();
    }
}
