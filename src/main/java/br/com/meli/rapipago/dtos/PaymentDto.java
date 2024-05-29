package br.com.meli.rapipago.dtos;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentDto {

  private BigDecimal transaction_amount;
 // private String description;
  private String payment_method_id;
  private PayerDto payerDto;
}
