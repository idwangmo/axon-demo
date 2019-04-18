package top.idwangmo.axondemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MoneyDebitDTO {

    private BigDecimal debitAmount;

    private String currency;

}
