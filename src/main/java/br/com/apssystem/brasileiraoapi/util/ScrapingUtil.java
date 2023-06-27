package br.com.apssystem.brasileiraoapi.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class ScrapingUtil {

    private static String URL_BASE = "http://www.google.com.br/search?q=";
    private static String COMPLEMENTO_URL_GOOGLE = "&h1=pt-BR";

    public static void main(String[] args) {
        String url = URL_BASE + "palmeira+x+corinthians+08/08/2020" + COMPLEMENTO_URL_GOOGLE;
        ScrapingUtil scraping = new ScrapingUtil();
        scraping.obtemInformacoesParitda(url);
    }

    public void obtemInformacoesParitda(String url) {
        Document document;
        try {
            document = Jsoup.connect(url).get();
            log.info("title:: "+document.title());
        } catch (IOException ex) {
            log.error("Error ao conectar na url : {} usando Jsoup -> {}", url, ex.getMessage());
        }
    }
}
