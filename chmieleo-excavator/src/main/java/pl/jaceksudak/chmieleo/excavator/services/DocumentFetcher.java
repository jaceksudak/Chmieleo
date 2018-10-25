package pl.jaceksudak.chmieleo.excavator.services;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Stateless
@Slf4j
public class DocumentFetcher {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0";
    private static final String REFERRER = "http://www.google.com";
    private static final int TIMEOUT = 10 * 1000;

    private static final String FIRST_PAGE_NUMBER = "1";

    public Document fetch(String url) {
        try {
            log.debug("Fetching {}", url);
            return Jsoup.connect(url)
                    .timeout(TIMEOUT)
                    .userAgent(USER_AGENT)
                    .referrer(REFERRER)
                    .validateTLSCertificates(false)
                    .get();
        } catch (IOException e) {
            log.error("Fetching {} failed!");
            e.printStackTrace();
            return new Document(url);
        }
    }

    public Document fetchListDocument(String listUrl, int i) {
        return fetch(String.format(listUrl, i));
    }
}
