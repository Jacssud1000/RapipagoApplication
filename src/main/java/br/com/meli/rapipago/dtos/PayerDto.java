package br.com.meli.rapipago.dtos;

import lombok.Data;

@Data
public class PayerDto {

  private String email;
 // private String first_name;
 // private String last_name;
 // private Identification identification;

  public PayerDto(String email, String first_name, String last_name) {
    this.email = email;
   // this.first_name = first_name;
   // this.last_name = last_name;
  }
}
