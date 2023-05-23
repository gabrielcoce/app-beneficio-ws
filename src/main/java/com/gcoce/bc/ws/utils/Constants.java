package com.gcoce.bc.ws.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Gabriel Coc Estrada
 * @since 18/05/2023
 */
public class Constants {

    public static final String AUTHORIZATION = "Authorization";
    public static final String SYSTEM_USER = "AP_MS_BENEFICIO_WS";


    public static final Integer SOLICITUD_CREADA = 1;

    public static final Integer SOLICITUD_EN_PROCESO = 2;

    public static final Integer SOLICITUD_APROBADA = 3;

    public static final Integer CUENTA_CREADA = 4;

    public static final Integer CUENTA_ABIERTA = 5;

    public static final Integer CUENTA_CERRADA = 6;

    public static final Integer CUENTA_CONFIRMADA = 7;

    public static final Integer PESAJE_INICIADO = 8;

    public static final Integer PESAJE_FINALIZADO = 9;

    public static final Integer TIPO_SOLICITUD_CC = 10;

    public static final Integer TIPO_SOLICITUD_AP = 11;

    public static final Integer TIPO_SOLICITUD_IP = 12;

    public static final Integer TIPO_SOLICITUD_AT = 13;

    public static final Integer TIPO_SOLICITUD_IT = 14;

    public static String generateManagement(Integer type) {
        return Fechas.getDateGuatemala() + generateTypeManagement(type) + generateRandomNumber();
    }

    public static String generateAccount(){
        return Fechas.getDateGuatemala() + "ACCOUNT" + generateRandomNumber();
    }

    private static String generateTypeManagement(Integer type) {
        switch (type) {
            case 10 -> {
                return "CC";
            }
            case 11 -> {
                return "AP";
            }
            case 12 -> {
                return "IP";
            }
            case 13 -> {
                return "AT";
            }
            case 14 -> {
                return "IT";
            }
            default -> {
            }
        }
        return null;
    }

    private static String generateRandomNumber() {
        try {
            return RandomStringUtils.randomAlphanumeric(8).toUpperCase();
        } catch (Exception e) {
            System.out.println("ocurrio un error en generar numero random");
            return null;
        }
    }

}
