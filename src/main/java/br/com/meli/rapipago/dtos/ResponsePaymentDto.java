package br.com.meli.rapipago.dtos;

import lombok.Data;

@Data
public class ResponsePaymentDto {
  private Long id;
  private String status;
  private String detail;
  private String external_resource_url;

  public ResponsePaymentDto(Long id, String status, String detail, String externalResourceUrl) {
    this.id = id;
    this.status = status;
    this.detail = detail;
    this.external_resource_url = externalResourceUrl;
  }
}
