package br.com.meli.rapipago.service;

import br.com.meli.rapipago.dtos.PaymentDto;
import br.com.meli.rapipago.dtos.ResponsePaymentDto;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.client.paymentmethod.PaymentMethodClient;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class RapipagoService {
  public ResponsePaymentDto payment(@Valid PaymentDto paymentDto) {
    try {
      MercadoPagoConfig.setAccessToken("APP_USR-4186747889975909-052910-4eefa0f3845688188e6d9f3761d23960-1832581269");

      PaymentMethodClient client = new PaymentMethodClient();

      client.list();

      OffsetDateTime dateOfExpiration = OffsetDateTime.now(ZoneOffset.UTC).plusHours(72);

      PaymentCreateRequest paymentCreateRequest = PaymentCreateRequest.builder()
          .transactionAmount(paymentDto.getTransaction_amount())
          .paymentMethodId(paymentDto.getPayment_method_id())
          .dateOfExpiration(dateOfExpiration)
          .payer(
              PaymentPayerRequest.builder()
                  .email(paymentDto.getPayerDto().getEmail()).build()
          ).build();

      Map<String, String> customHeaders = new HashMap<>();
      customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

      MPRequestOptions requestOptions = MPRequestOptions.builder()
          .accessToken("APP_USR-4186747889975909-052910-4eefa0f3845688188e6d9f3761d23960-1832581269")
          .customHeaders(customHeaders).build();

      PaymentClient clients = new PaymentClient();

      Payment payment = clients.create(paymentCreateRequest, requestOptions);

      String external_resource_url = payment.getTransactionDetails().getExternalResourceUrl();

      return new ResponsePaymentDto(
          payment.getId(),
          payment.getStatus(),
          payment.getStatusDetail(),
          external_resource_url
      );

    } catch (MPException | MPApiException e) {
      throw new RuntimeException(e);
    }
  }
}
