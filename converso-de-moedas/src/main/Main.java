package main;

import com.google.gson.JsonObject;
import service.ApiUrl;
import service.ConsumoApi;
import service.ConverteDados;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static double valorParaConverter, valorConvertido;
    private static ApiUrl apiUrl = new ApiUrl();
    private static String moedaOrigem, moedaDestino;
    private static JsonObject jsonobj;

    //private static final String API_KEY = System.getenv("API_KEY");
    private static String getMoedaOrigem(int opcao) {
        switch (opcao) {
            case 1:
            case 3:
            case 5:
                return "USD";
            case 2:
                return "ARS";
            case 4:
                return "BRL";
            case 6:
                return "COP";
            default:
                return "";
        }
    }

    private static String getMoedaDestino(int opcao) {
        switch (opcao) {
            case 1:
                return "ARS";
            case 2:
            case 4:
                return "USD";
            case 3:
                return "BRL";
            case 5:
                return "COP";
            case 6:
                return "USD";
            default:
                return "";
        }
    }

    public static void main(String[] args) {

        int opcao;
        do {
            System.out.println("#########################################");
            System.out.println("Seja bem-vindo(a) ao Conversor de Moeda!");
            System.out.println("#########################################");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real");
            System.out.println("4) Real => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Sair");
            System.out.println("#########################################");
            System.out.print("Escolha uma opção válida: ");
            opcao = scanner.nextInt();

            if (opcao >= 1 && opcao <= 6) {
                System.out.println("Digite o valor que deseja converter:");
                while (!scanner.hasNextDouble()) {
                    System.out.println("Por favor, insira um valor numérico válido.\n");
                    scanner.next();
                }
                valorParaConverter = scanner.nextDouble();
                moedaOrigem = getMoedaOrigem(opcao);
                moedaDestino = getMoedaDestino(opcao);

                String url_str = apiUrl.getApiUrl() + "/latest/" + moedaOrigem;
                jsonobj = ConsumoApi.getJsonResponse(url_str);

                if (jsonobj != null) {
                    valorConvertido = ConverteDados.converterMoeda(jsonobj, moedaDestino, valorParaConverter);
                    System.out.printf("Valor %.2f [%s] corresponde ao valor final de %.2f [%s]%n"
                            , valorParaConverter, moedaOrigem, valorConvertido, moedaDestino);
                    System.out.println("\n");
                }
            } else if (opcao == 7) {
                System.out.println("Obrigado por usar o conversor de moedas!");
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                System.out.println("\n");
            }
        } while (opcao != 7);
    }

}
