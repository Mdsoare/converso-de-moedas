package service;

import com.google.gson.JsonObject;

public class ConverteDados {

    public static double converterMoeda(JsonObject jsonobj, String moedaDestino, double valor) {
        JsonObject rates = jsonobj.getAsJsonObject("conversion_rates");
        double taxaConversao = rates.get(moedaDestino).getAsDouble();
        return valor * taxaConversao;
    }
}