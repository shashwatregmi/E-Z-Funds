package network;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class PullWelcomeMsg {
    public String finalWeather;
    private double temp;

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
            /////////////////////// Snipped enclosed by brackets taken from https://www.youtube.com/watch?v=umZ_KdcXRAQ
            JSONObject myResponse = new JSONObject(builder.toString());

            String city = myResponse.getString("name");
            JSONObject mainObject = new JSONObject(myResponse.getJSONObject("main").toString());
            ////////////////////////////////////////////////////////////////////


            temp = mainObject.getDouble("temp");
            double minTemp = mainObject.getDouble("temp_min");
            double maxTemp = mainObject.getDouble("temp_max");

            finalWeather = "It is " + temp + " degrees C in " + city  + ". Max/Min are " + minTemp + "/" + maxTemp;
            System.out.println("It is " + temp + " degrees C in " + city  + ". Max/Min is " + minTemp + "/" + maxTemp);

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    // EFFECTS: return temp value
    public double getTemp() {
        return temp;
    }
}
