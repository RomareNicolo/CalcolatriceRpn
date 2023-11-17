import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class WebCrawler {
    public static void main(String[] args) {
        String url = "https://www.itisrossi.edu.it/"; // Replace with the URL you want to crawl
        crawl(url);
    }

    private static void crawl(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.select("a[href]");

            for (Element link : links) {
                String href = link.attr("href");
                System.out.println(href);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}