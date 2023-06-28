package br.com.apssystem.brasileiraoapi.util;

import br.com.apssystem.brasileiraoapi.dto.PartidaGoogleDTO;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ScrapingUtil {

    private static String URL_BASE = "https://futebolaovivo.com.br/";
    private static String COMPLEMENTO_URL = "&h1=pt-BR";

    public static void main(String[] args) {
        String url = URL_BASE;
        ScrapingUtil scraping = new ScrapingUtil();
        scraping.tblJogosAmanha(url);
        //PartidaGoogleDTO partida = scraping.statusJogo(url);

    }

    public void obtemInformacoesParitda(String url) {
        Document document;
        try {
            document = Jsoup.connect(url).get();
            log.info("title:: " + document.title());
        } catch (IOException ex) {
            log.error("Error ao conectar na url : {} usando Jsoup -> {}", url, ex.getMessage());
        }
    }

    public void tblJogosAmanha(String url) {
        Document doc;
        List<PartidaGoogleDTO> list = new ArrayList<>();
        try {
            doc = Jsoup.connect(url + "jogos-amanha" + COMPLEMENTO_URL).get();
            Elements elements = doc.select("#jogosLiga > nav");
            for (Element el : elements) {
                String id = UUID.randomUUID().toString().substring(0, 6);
                String text = el.getElementsByTag("a").first().text();
                String logo = el.select("noscript > img").attr("src");

                //As li
              /*  Elements rows = el.select("ul > li ");
                for (Element row : rows) {
                    String uuid = UUID.randomUUID().toString().substring(0, 6);
                    String visitante = row.select("div[class=logo-time-visitante] > span").text();
                    String dataFormatada = regex("futebolaovivo.*?(\\d.{4})", row.select("div > a").attr("href"));
                    PartidaGoogleDTO partida = PartidaGoogleDTO
                            .builder()
                            .id(uuid)
                            .horaPartida(row.selectFirst("div[class=logo-time-casa]").text())
                            .nomeEquipeCasa(row.select("div[class=logo-time-casa] > span").text())
                            .urlLogoEquipeCasa(row.select("div[class=logo-time-casa] > noscript > img").attr("src"))
                            .nomeEquipeVisitante(visitante)
                            .urlLogoEquipeVisitante(row.select("div[class=logo-time-visitante] > noscript > img").attr("src"))
                            .dataPartida("Dia " + dataFormatada.replace("-", "/"))
                            .build();
                    list.add(partida);

                }*/

                log.info("text : {} {} {}", id, text, logo);
            }
        } catch (IOException ex) {
            log.error("Error ao conectar na url : {} usando Jsoup -> {}", url, ex.getMessage());
        }
    }

    public static String regex(String regex, String texto) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(texto);
        if (!matcher.find()) {
            throw new RuntimeException("Error regex" + regex);
        }
        return matcher.group(1);
    }

    public PartidaGoogleDTO statusJogo(String url) {
        PartidaGoogleDTO dto = new PartidaGoogleDTO();
        String urlEspecifica = url + "superliga/belgrano-x-banfield-26-06-2023-18723060/" + COMPLEMENTO_URL;
        Document doc;
        try {
            doc = Jsoup.connect(urlEspecifica).get();
            Elements element = doc.select("#cover > div > section > small");
            for (Element el : element) {
                boolean empty = el.select("span[class=v-info]").isEmpty();
                if (!empty) {
                    String text = el.select("span[class=v-info]").first().text();
                    if (text.contains("{{local.score}}")) {
                        dto.setUrlLogoEquipeCasa(el.select("noscript > picture > img").attr("src"));
                        dto.setNomeEquipeCasa(el.getElementsByTag("b").text());
                        dto.setPlacarEquipeCasa(el.select("span[class=temp-info]").first().text());
                    } else {
                        dto.setUrlLogoEquipeVisitante(el.select("noscript > picture > img").attr("src"));
                        dto.setNomeEquipeVisitante(el.getElementsByTag("b").text());
                        dto.setPlacarEquipeVisitante(el.select("span[class=temp-info]").first().text());
                    }
                }
            }
            return dto;
        } catch (IOException ex) {
            log.error("Error ao conectar na url : {} usando Jsoup -> {}", url, ex.getMessage());
        }
        return dto;
    }
}
