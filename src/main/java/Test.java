
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
 */
public class Test {

    public static void main(String[] args) {
        float valorPedido = (float) 4.5;

        //Se PRODUCAO for igual a TRUE ele vai usar a API de produção, por padrão
        //essa variavel recebe FALSE.
        ConfigAPIcieloLio.PRODUCAO = false;
        //Setar suas crendenciais.
        ConfigAPIcieloLio.ClienteID = "SUA_CLIENTE_ID";
        ConfigAPIcieloLio.AcessToken = "SUA_ACESS_TOKEN";
        ConfigAPIcieloLio.MerchantID = "SUA_MERCHANT_ID";
        //Retorna qual abiente ira utilizar.
        ConfigAPIcieloLio.tipoAmbiente();

        valorPedido = 25000;
        String pedidoJson = new setaDadosOrder().populaOrder("1010", "CONSUMIDOR", (int) valorPedido);
        
        try {
            ResponseVO retornoJson = new ApiCieloLioController().postPedido(pedidoJson);
            
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
