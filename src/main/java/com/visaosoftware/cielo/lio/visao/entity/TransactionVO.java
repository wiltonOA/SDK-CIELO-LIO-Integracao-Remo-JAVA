
package com.visaosoftware.cielo.lio.visao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author WILTON OLIVEIRA 
 * www.visaoconsultoriaemti.com.br
 * 
 * A Transaction é uma representação com todos os pagamentos que foram realizados em uma Order. 
 * O objetivo é utilizá-la para consultar os pagamentos (transactions) que foram efetuados em uma Order.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionVO implements Serializable{
    private String id;                      //“UUID” que identifica unicamente a transação.
    private String external_id;
    private String status;                  //Status da transação (CONFIRMED, PENDING e CANCELLED).
    private Integer terminal_number;
    private Integer authorization_code;     //Código de autorização da transação.
    private String number;                  //Número Sequencial Único (NSU) da transação.
    private BigDecimal amount;
    private String transaction_type;        //Tipo da transação (PAYMENT e CANCELLATION).
    private PayamentProductVO payment_fields;
    private CardVO card;
}
