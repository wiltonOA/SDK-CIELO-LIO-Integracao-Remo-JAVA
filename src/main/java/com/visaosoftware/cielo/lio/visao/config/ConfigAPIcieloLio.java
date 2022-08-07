package com.visaosoftware.cielo.lio.visao.config;

/**
 * @author WILTON OLIVEIRA www.visaoconsultoriaemti.com.br
 *
 */
public class ConfigAPIcieloLio {

    public static String URL_SANDBOX = "https://api.cielo.com.br/sandbox-lio/order-management/v1/";
    public static String URL_PRODUCAO = "https://api.cielo.com.br/order-management/v1/";

    public static String URL = URL_SANDBOX;

    //Essas informacões são geradas a partir do portal de desenvolvedores da Cielo (http://desenvolvedores.cielo.com.br/)
    public static String ClienteID = "CLIENTE_ID";
    public static String AcessToken = "ACESS_TOKEN";
    public static String MerchantID = "MERCHAN_ID";

    //DEFINE SE VAI USAR URL DE PRODUÇÃO OU DE SANDBOX
    public static Boolean PRODUCAO = false;

    public static void tipoAmbiente() {
        if (PRODUCAO) {
            URL = URL_PRODUCAO;
        } else {
            URL = URL_SANDBOX;
        }
    }
}
