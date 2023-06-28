package br.com.apssystem.brasileiraoapi.bot;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class BotCampeonatoMain {

    private static String URL_BASE = "https://futebolaovivo.com.br";

    public static void main(String[] args) {
        BotCampeonatoMain main = new BotCampeonatoMain();
    }

    public static List<String> getListaDeCampeonatos() {
        List<String> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(URL_BASE).get();
            Elements elements = doc.select("span[class=campeonatos]").select("a");
            for (Element el : elements) {
                if (!el.text().isEmpty()) {
                    result.add(el.text());
                }
            }
        } catch (IOException ex) {
            log.error("Error ao conectar na url : {} usando Jsoup -> {}", URL_BASE, ex.getMessage());
        }
        return result;
    }
}
