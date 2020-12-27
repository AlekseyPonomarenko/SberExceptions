package gen;

import impl.SumValidatorAble;

import java.math.BigDecimal;

public class SumValidator implements SumValidatorAble {
    public String validateSummForTerminal (BigDecimal summ)  {
        String result = "";

        //На кратность 100
        if (summ.doubleValue()%100 ==0) {

        } else
            {
            return "Сумма должна быть кратной 100";
        }

        if (summ.doubleValue()> 0){

        }else {
            return "Сумма должна быть больше 0";
        }

        return "";
    }
}
