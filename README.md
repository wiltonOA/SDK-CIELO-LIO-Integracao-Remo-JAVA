## SDK-CIELO-LIO-Integracao-Remota-JAVA-V1.0
SDK para realizar chamadas na API de Integração Remota da Cielo LIO

## Requisitos                                     
- Java >= 8  
## Instalação
Instale o CIELO-LIO-VISAO-1.0-SNAPSHOT.jar em seu projeto.
## Chamadas ja implementadas
- POST /orders                                                                                                         
- DELETE /orders/{id}                                                                                                                     
- GET /orders/{id}                                                                                                                               
- GET /orders
- GET /orders/{id}/transactions
- PUT /orders/{id}

## Exemplo de como usar o SDK.

```java
import com.google.gson.Gson;
import com.visaosoftware.cielo.lio.visao.config.ConfigAPIcieloLio;
import com.visaosoftware.cielo.lio.visao.controller.ApiCieloLioController;
import com.visaosoftware.cielo.lio.visao.entity.ItensVO;
import com.visaosoftware.cielo.lio.visao.entity.OrderVO;
import com.visaosoftware.cielo.lio.visao.entity.ResponseVO;
import com.visaosoftware.cielo.lio.visao.util.StatusCode;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WILTON OLIVEIRA www.visaosoftware.com.br
 * 
 * Nessa classe configuramos o SDK com as credenciais de desenvolvedores criadas na Cielo (http://desenvolvedores.cielo.com.br/)
 * Definimos o ambiente que vamos utilizar SANDBOX || PRODUÇÃO.
 * Criamos um pedido e enviamos para API Order Manager que sera enviado para o terminal da LIO.
 */
public class Test {

    public static void main(String[] args) {
        float valorPedido = (float) 4.5;

        //Se PRODUCAO for igual a TRUE ele vai usar a API de produção, por padrão
        //essa variavel recebe FALSE por padão.
        ConfigAPIcieloLio.PRODUCAO = false;
        //Setar suas crendenciais.
        ConfigAPIcieloLio.ClienteID = "SUA_CLIENTE_ID";
        ConfigAPIcieloLio.AcessToken = "SUA_ACESS_TOKEN";
        ConfigAPIcieloLio.MerchantID = "SUA_MERCHANT_ID";
        //Retorna qual abiente ira utilizar.
        ConfigAPIcieloLio.tipoAmbiente();

        //Criando um pedido.
        valorPedido = 25000;
        String pedidoJson = new setaDadosOrder().populaOrder("1010", "CONSUMIDOR", (int) valorPedido);
        
        try {
            //Enviando pedito para API Order Manager da Cielo LIO.
            ResponseVO retornoJson = new ApiCieloLioController().postPedido(pedidoJson);
            
            //Se tudo correu bem, imprime no console o ID do pedido criado.
            if (retornoJson.getStatus().equals(StatusCode.STATUS_201)) {
                System.out.println("Pedido enviado para LIO com sucesso!");
                System.out.println("ID do pedido: " + retornoJson.getId());
            } else {
                System.out.println("Erro ao enviar pedido para LIO");
                System.out.println("Erro: " + retornoJson.getErrorResponse().getCode() + " " + retornoJson.getErrorResponse().getDetail());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Classe que seta os dados do pedido e retorna uma String Json
    public static class setaDadosOrder {

        public String populaOrder(String numPedido, String nomeCli, int valorPedido) {
            OrderVO order = new OrderVO();
            order.setNumber(numPedido);
            order.setReference("VENDA " + nomeCli.toUpperCase());
            order.setStatus("ENTERED");
            order.setItems(retornaListaItens(valorPedido));
            order.setNotes(order.getReference());
            order.setPrice(new BigDecimal(valorPedido));

            Gson gson = new Gson();
            String json = gson.toJson(order);

            return json;
        }

        private List<ItensVO> retornaListaItens(int preco) {
            List<ItensVO> listaDeItens = new ArrayList<>();
            ItensVO itens = new ItensVO();

            itens.setName("VENDA NO PDV 01");
            itens.setSku("0000001");
            itens.setUnit_price(new BigDecimal(preco));
            itens.setQuantity(1);
            itens.setUnit_of_measure("EACH");

            listaDeItens.add(itens);

            return listaDeItens;
        }
    }
}
```

#### [Manual da documenação no swagger](https://desenvolvedores.cielo.com.br/api-portal/pt-br/swagger/cielo-lio-order-manager-integrao-remota/1.0.0#/)

#### [Manual da documenação da integração remota aqui!](https://developercielo.github.io/manual/cielo-lio#integra%C3%A7%C3%A3o-remota)


## Autor

Wilton Oliveira

[linkedin.com/in/wilton-oliveira-a40abb5b](https://www.linkedin.com/in/wilton-oliveira-a40abb5b/)
