package bankservice.demo.service.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Component;

@Component
public class HttpClient {
    public BigDecimal sendHttpRequest(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonElement root = JsonParser
                    .parseReader(new InputStreamReader((InputStream) request.getContent()));
            return root.getAsJsonObject().get("result").getAsBigDecimal();
        } catch (IOException e) {
            throw new RuntimeException("Can`t parse json object" + e);
        }
    }
}
