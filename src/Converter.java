import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Converter {
    private final HttpClient client;

    public Converter() {
        client = HttpClient.newHttpClient();
    }


    public void convert(double rubles, int currency) {
        if (currency == 1) {
            double rate = getRate("USD");
            System.out.println("Ваши сбережения в долларах: " + rubles * rate);
        } else if (currency == 2) {
            double rate = getRate("EUR");
            System.out.println("Ваши сбережения в евро: " + rubles * rate);
        } else if (currency == 3) {
            double rate = getRate("JPY");
            System.out.println("Ваши сбережения в иенах: " + rubles * rate);
        } else {
            System.out.println("Неизвестная валюта");
        }
    }

    private double getRate(String currencySMB) {
        URI url = URI.create("https://api.exchangerate.host/latest?base=RUB&symbols=" + currencySMB);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();

        double rate = 0.0;
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JsonElement jsonElement = JsonParser.parseString(response.body());
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonObject ratesObject = jsonObject.get("rates").getAsJsonObject();
                rate = ratesObject.get(currencySMB).getAsDouble();
            } else {
                System.out.println("Что-то пошло не так. Сервер вернул код состояния: " + response.statusCode());
            }
        } catch (NullPointerException | IOException | InterruptedException e) { // обрабатываем ошибки отправки запроса
            System.out.println("Во время выполнения запроса возникла ошибка.\n" +
                    "Проверьте, пожалуйста, адрес и повторите попытку.");
        }
        return rate;
    }
}