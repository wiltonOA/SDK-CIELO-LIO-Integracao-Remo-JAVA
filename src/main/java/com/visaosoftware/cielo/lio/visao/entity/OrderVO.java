
package com.visaosoftware.cielo.lio.visao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author WILTON OLIVEIRA 
 * www.visaoconsultoriaemti.com.br
 * 
 * A Order é uma representação de um pedido para a venda de um ou mais produtos e/ou serviços. 
 * É fundamental que exista uma Order para que um pagamento seja realizado na Cielo LIO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO implements Serializable{
    
    private String id;
    private String number;
    private String reference;
    private String status;
    private String created_at;
    private String updated_at;
    private List<ItensVO> items;
    private String notes;
    private List<TransactionVO> transactions;
    private BigDecimal price;
    private BigDecimal remaining;  
}
