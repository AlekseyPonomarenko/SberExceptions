package gen;

import impl.SumValidatorAble;

import java.math.BigDecimal;

public class SumValidator implements SumValidatorAble {
    public void validateSummForTerminal (BigDecimal summ) throws Throwable  {


        if (summ.doubleValue()%100 ==0) {

        } else {
            String err = "Сумма должна быть кратной 100";
            System.err.println(err);
            throw  new IllegalArgumentException(err);
        }

    }


}
