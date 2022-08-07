
package com.visaosoftware.cielo.lio.visao.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author WILTON OLIVEIRA 
 * www.visaoconsultoriaemti.com.br
 * 
 * O Payment Product é uma representação da forma de pagamento utilizada para realizar um pagamento. 
 * Ex.: Crédito, Débito, etc.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayamentProductVO implements Serializable{
    private String primary_product_name;            //Forma de pagamento (CREDITO ou DEBITO)
    private String secondary_product_name;          //Tipo de pagamento (A VISTA, PARCELADO LOJA ou PARCELADO ADM)
    private Integer number_of_quotas;               //Número de parcelas (0 para pagamentos à vista)
}
