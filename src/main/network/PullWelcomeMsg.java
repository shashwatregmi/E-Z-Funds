package network;

import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PullWelcomeMsg {

    public PullWelcomeMsg() throws IOException {
        BufferedReader reader = null;

        try {
            String apiKey = "8868d57aeca1209a618a88073c82cd43";
            String weather = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,CAN&units=metric&&APPID=";
            String theURL = weather + apiKey;
            URL url = new URL(theURL);

            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }

            System.out.println(builder);



        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
