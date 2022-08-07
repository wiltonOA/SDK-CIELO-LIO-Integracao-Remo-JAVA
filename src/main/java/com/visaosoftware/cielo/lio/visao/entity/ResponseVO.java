/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visaosoftware.cielo.lio.visao.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author WILTON OLIVEIRA 
 * www.visaoconsultoriaemti.com.br
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO {
    private Integer status;
    private String id;
    private ErrorResponse errorResponse;
    private OrderVO order;
    private List<TransactionVO> transactions;
}
