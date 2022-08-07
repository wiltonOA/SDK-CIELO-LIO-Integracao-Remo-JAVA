
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
 * O Card é uma representação do cartão que foi utilizado para realizar o pagamento/transação.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardVO implements Serializable{
    private String brand;       //Bandeira do cartão (VISA e MASTER...).
    private String mask;        //Número do cartão mascarado
}
