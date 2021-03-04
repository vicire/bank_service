package bankservice.demo.service.util;

import bankservice.demo.entity.Currency;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClientService {
    @Value(value = "$(service.url)")
    private String value;
    private String url;
    private final HttpClient httpClient;

    public ClientService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public BigDecimal getRate(LocalDate date, Currency from, Currency to) {
        String path = String.format(url + "=%s&to=%s&date=%s", from, to, date);
        return httpClient.sendHttpRequest(path);
    }
}
