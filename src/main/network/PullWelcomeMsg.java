package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class PullWelcomeMsg {

    public PullWelcomeMsg() throws IOException {
        BufferedReader reader = null;

        try {
            String theURL = "https://www.students.cs.ubc.ca/~cs-210/2018w1/welcomemsg.html";
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
