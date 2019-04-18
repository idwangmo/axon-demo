package top.idwangmo.axondemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountCreateDTO {

    private BigDecimal startingBalance;

    private String currency;

}
