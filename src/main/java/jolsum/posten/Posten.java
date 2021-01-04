package jolsum.posten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Posten {

  private static final String URL =
      "https://www.posten.no/levering-av-post-2020/_/component/main/1/leftRegion/1?postCode=%d";

  public static boolean deliveryToday(int postcode) throws IOException {

    URL url = new URL(String.format(URL, postcode));

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setConnectTimeout(20 * 1000);
    conn.setReadTimeout(20 * 1000);
    conn.setRequestProperty("x-requested-with", "XMLHttpRequest");

    String line;
    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      line = in.readLine();
    }

    return line.contains("i dag");
  }

  public static void main(String[] args) throws IOException {
    System.out.println(deliveryToday(5020));
    System.out.println(deliveryToday(7045));
  }
}
