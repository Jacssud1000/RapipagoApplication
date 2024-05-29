package br.com.meli.rapipago.controller;

import br.com.meli.rapipago.dtos.PaymentDto;
import br.com.meli.rapipago.dtos.ResponsePaymentDto;
import br.com.meli.rapipago.service.RapipagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:63342")
@RequestMapping()
public class RapipagoController {

  @Autowired
  private RapipagoService rapipagoService;

  @PostMapping(path = "/v1/payments")
  ResponseEntity<ResponsePaymentDto> payment(@Valid @RequestBody PaymentDto paymentDto) {
    ResponsePaymentDto paymentRequest = rapipagoService.payment(paymentDto);
    return ResponseEntity.status(HttpStatus.OK).body(paymentRequest);
  }
}