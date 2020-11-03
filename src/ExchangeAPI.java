import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExchangeAPI {


    //class attributes
    private String result;
    private String tine_last_update_utc;
    private String tine_next_update_utc;
    private String base_code;
    private JSONObject conversion_rates;

    private static String url_API = "https://v6.exchangerate-api.com/v6/7e9a5c7aded96f780711e0ea/latest/";

    private static JSONObject jsonObject;

    //connect server
    public boolean getConnection(String base_code){
        String json = "";
        URL url = null;
        HttpURLConnection request = null;

        try {
            url = new URL(url_API+ base_code);
            request= (HttpURLConnection) url.openConnection();

            // connect to server
            request.connect();

            //read data from server
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line = reader.readLine();


            if (line.length()>0){
                json+= line;

                //json to jsonObject
                jsonObject = new JSONObject(json);
                if (jsonObject == null) {
                    return false;

                }
                this.result = jsonObject.getString("result");
                this.tine_last_update_utc = jsonObject.getString("tine_lase_update_utc");
                this.tine_next_update_utc = jsonObject.getString("tine_next_update_utc");
                this.base_code = jsonObject.getString("base_code");
                this.conversion_rates = jsonObject.getJSONObject("conversion_rates");

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;

    }//getConnection

    public String getResult() {
        return result;
    }

    public String getTine_last_update_utc() {
        return tine_last_update_utc;
    }

    public String getTine_next_update_utc() {
        return tine_next_update_utc;
    }

    public JSONObject getConversion_rates() {
        return conversion_rates;
    }

    public String getBase_code() {
        return base_code;

    }

    public double getEachRete (String newCurrency){
        double eachRate = 0.0;

        try {
            eachRate = this.conversion_rates.getDouble(newCurrency);
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return eachRate;


    }



}//class
