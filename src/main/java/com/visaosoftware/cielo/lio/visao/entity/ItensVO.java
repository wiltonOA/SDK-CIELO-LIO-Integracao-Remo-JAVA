
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
 * O Order Item é uma representação dos itens presentes em uma Order. 
 * É obrigatória a existência de, no mínimo, um Item para uma Order.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItensVO implements Serializable{
    
    private String id;
    private String sku;
    private String name;
    private String description;
    private BigDecimal unit_price;
    private Integer quantity;
    private String unit_of_measure; //Unidade de medida (EACH, HOURS, DAYS, SECONDS, CRATE_OF_12, SIX_PACK, GALLON e LITRE).
    private String details;
    private String created_at;
    private String updated_at;      //Data de última atualização do pedido. A data deve estar no formato: YYYY-MM-DDThh:mm:ssZ (Exemplo: 20151020T13:13:29.000Z).
}
