
package com.visaosoftware.cielo.lio.visao.controller;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.visaosoftware.cielo.lio.visao.config.ConfigAPIcieloLio;
import com.visaosoftware.cielo.lio.visao.entity.ErrorResponse;
import com.visaosoftware.cielo.lio.visao.entity.OrderVO;
import com.visaosoftware.cielo.lio.visao.entity.ResponseVO;
import com.visaosoftware.cielo.lio.visao.entity.TransactionVO;
import com.visaosoftware.cielo.lio.visao.util.StatusCode;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author WILTON OLIVEIRA www.visaosoftware.com.br
 */
public class ApiCieloLioController {

    Gson gson = null;

    //Esse recurso realiza a criação de um pedido no servidor do Order Manager.
    public ResponseVO postPedido(String pedidoJson) throws IOException {
        String retornoApi = "";
        ResponseVO retornoObj = null;

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, pedidoJson);

        Request request = new Request.Builder()
                .url(ConfigAPIcieloLio.URL + "orders")
                .post(body)
                .addHeader("client-id", ConfigAPIcieloLio.ClienteID)
                .addHeader("access-token", ConfigAPIcieloLio.AcessToken)
                .addHeader("merchant-id", ConfigAPIcieloLio.MerchantID)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();

        //CRIA O PEDIDO
        Response response = client.newCall(request).execute();
        //PEGA O CÓDIGO DE STATUS DE RETORNO
        Integer status = response.code();
        //PEGA O RETORNO DA API
        retornoApi = response.body().string();

        gson = new Gson();
        if (status.equals(StatusCode.STATUS_201)) {
            retornoObj = gson.fromJson(retornoApi, ResponseVO.class);
            retornoObj.setStatus(status);
        } else {
            retornoObj = new ResponseVO();
            ErrorResponse erro = gson.fromJson(retornoApi, ErrorResponse.class);
            retornoObj.setErrorResponse(erro);
            retornoObj.setStatus(status);
        }

        return retornoObj;
    }

    //PUT- Alterar status de um pedido
    //Esse recurso realiza a alteração do status de um pedido criado. O id do pedido é utilizado para realizar a chamada.
    //
    //As possíveis alterações de status são:
    //
    //PLACE (Liberar um pedido para pagamento, exibir pedido na Cielo LIO)
    //PAY (Alterar um pedido para pago)
    //CLOSE (Fechar um pedido)
    public ResponseVO putPedido(String idPedido, String operation) throws IOException {

        String retornoApi = "";
        ResponseVO retornoObj = null;

        OkHttpClient client = new OkHttpClient();

        String urlPut = ConfigAPIcieloLio.URL + "orders/" + idPedido + "?operation=" + operation.toUpperCase();

        System.err.println("URL => " + urlPut);

        MediaType mediaType = MediaType.parse("");
        RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url(urlPut)
                .put(body)
                .addHeader("client-id", ConfigAPIcieloLio.ClienteID)
                .addHeader("access-token", ConfigAPIcieloLio.AcessToken)
                .addHeader("merchant-id", ConfigAPIcieloLio.MerchantID)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();

        Integer status = response.code();

        retornoApi = response.body().string();

        gson = new Gson();
        if (status.equals(StatusCode.STATUS_200)) {
            retornoObj = new ResponseVO();
            retornoObj.setStatus(status);
        } else {
            retornoObj = new ResponseVO();
            ErrorResponse erro = gson.fromJson(retornoApi, ErrorResponse.class);
            retornoObj.setErrorResponse(erro);
            retornoObj.setStatus(status);
        }

        return retornoObj;
    }

    //RETORNA UM PEDIDOS PELO ID DA API
    //Esse recurso é utilizado para obter informações sobre um pedido específico. 
    //O id do pedido é utilizado para realizar a chamada.
    public ResponseVO getPedido(String idPedido) throws IOException {

        String retornoApi = "";
        ResponseVO retornoObj = null;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(ConfigAPIcieloLio.URL + "orders/" + idPedido)
                .get()
                .addHeader("client-id", ConfigAPIcieloLio.ClienteID)
                .addHeader("access-token", ConfigAPIcieloLio.AcessToken)
                .addHeader("merchant-id", ConfigAPIcieloLio.MerchantID)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();

        Integer status = response.code();

        retornoApi = response.body().string();

        gson = new Gson();
        if (status.equals(StatusCode.STATUS_200)) {
            retornoObj = new ResponseVO();
            OrderVO order = gson.fromJson(retornoApi, OrderVO.class);
            retornoObj.setStatus(status);
            retornoObj.setOrder(order);
        } else {
            retornoObj = gson.fromJson(retornoApi, ResponseVO.class);
            retornoObj.setStatus(status);
        }

        return retornoObj;
    }

    /*
    Esse recurso é utilizado para obter as informações de todas as transações realizadas em um pedido. 
    O id do pedido é utilizado para realizar a chamada. Em ambiente de produção, 
    uma vez que um pagamento for realizado na Cielo LIO a transactions será adicionada 
    automaticamente no pedido e então, será possível obter as informações do pagamento realizado a partir da chamada deste recurso.
     */
    public ResponseVO getTransactionPedidos(String idPedido) throws IOException {

        String retornoApi = "";
        ResponseVO retornoObj = null;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(ConfigAPIcieloLio.URL + "orders/" + idPedido + "/transactions")
                .get()
                .addHeader("client-id", ConfigAPIcieloLio.ClienteID)
                .addHeader("access-token", ConfigAPIcieloLio.AcessToken)
                .addHeader("merchant-id", ConfigAPIcieloLio.MerchantID)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();

        Integer status = response.code();

        retornoApi = response.body().string();

        gson = new Gson();
        if (status.equals(StatusCode.STATUS_200)) {
            retornoObj = new ResponseVO();

            java.lang.reflect.Type tipoListaTransactions = new com.google.gson.reflect.TypeToken<ArrayList<TransactionVO>>() {
            }.getType();
            ArrayList<TransactionVO> listTransactions = gson.fromJson(retornoApi, tipoListaTransactions);

            retornoObj.setTransactions(listTransactions);
            retornoObj.setStatus(status);
        } else {
            retornoObj = new ResponseVO();
            ErrorResponse erro = gson.fromJson(retornoApi, ErrorResponse.class);
            retornoObj.setErrorResponse(erro);
            retornoObj.setStatus(status);
        }

        return retornoObj;
    }

    //RETORNA LISTA DE PEDIDOS
    public String getListaPedidos() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(ConfigAPIcieloLio.URL + "orders")
                .get()
                .addHeader("client-id", ConfigAPIcieloLio.ClienteID)
                .addHeader("access-token", ConfigAPIcieloLio.AcessToken)
                .addHeader("merchant-id", ConfigAPIcieloLio.MerchantID)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();

        String resposta = response.body().string();

        return resposta;
    }

    //Esse recurso é utilizado para consultar os itens presentes em um pedido. 
    //O id do pedido é utilizado para realizar a chamada.
    public String getItensDoPedido(String idPedido) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(ConfigAPIcieloLio.URL + "orders/" + idPedido + "/items")
                .get()
                .addHeader("client-id", ConfigAPIcieloLio.ClienteID)
                .addHeader("access-token", ConfigAPIcieloLio.AcessToken)
                .addHeader("merchant-id", ConfigAPIcieloLio.MerchantID)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();

        String resposta = response.body().string();

        System.err.println("*==> " + resposta);
        return resposta;
    }

    //Esse recurso é utilizado para excluir um pedido do servidor do Order Manager. 
    //O id do pedido é utilizado para realizar a chamada.
    public Boolean deletePedido(String idPedido) throws IOException {

        Boolean deleted = false;

        OkHttpClient client = new OkHttpClient();

        String urlDelete = ConfigAPIcieloLio.URL + "orders/" + idPedido;

        System.err.println("URL => " + urlDelete);

        Request request = new Request.Builder()
                .url(urlDelete)
                .delete()
                .addHeader("client-id", ConfigAPIcieloLio.ClienteID)
                .addHeader("access-token", ConfigAPIcieloLio.AcessToken)
                .addHeader("merchant-id", ConfigAPIcieloLio.MerchantID)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();

        Integer status = response.code();

        if (status.equals(StatusCode.STATUS_204)) {
            deleted = true;
        }

        String novaResposta = response.body().string();

        return deleted;
    }

}
