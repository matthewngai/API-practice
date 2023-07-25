package org.example;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ParseException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://jsonmock.hackerrank.com/api/food_outlets?page=1"))
                .headers("key1", "value1", "key2", "value2")
                .GET()
                .build();
        // java 8
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        Gson gson = new Gson();
//        Foo result = gson.fromJson(response.body(), Foo.class);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.body());
        List<Object> list = (List<Object>) json.get("data");
        for (Object o: list) {
//            System.out.println(o);
        }


        String path = "/Users/matthewngai/Documents/JsonParserHttpAndFileRead/src/main/resources/data.json";

        try {

            // default StandardCharsets.UTF_8
            String content = Files.readString(Paths.get(path));
            JSONObject jsonFile = (JSONObject) parser.parse(content);
//            System.out.println(content);
            List<Object> listFromFile = (List<Object>) json.get("data");
            for (Object o: list) {
                System.out.println(o);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}