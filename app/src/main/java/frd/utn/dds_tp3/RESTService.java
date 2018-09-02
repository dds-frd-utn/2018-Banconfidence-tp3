package frd.utn.dds_tp3;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RESTService {
    public static List<Movimiento> makeGetRequest(String restURL){
        List<Movimiento> listOfItems = new ArrayList<>();
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            url = new URL(restURL);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
            StringBuilder sBuilder = new StringBuilder();

            String line = "";
            while ((line = reader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }

            inputStream.close();
            result = sBuilder.toString();
            JSONArray jsonArray = new JSONArray(result);
            Log.i(result, "JSON Object");

            for ( int i = 0; i < jsonArray.length(); i++ ) {
                Movimiento movimiento = new Movimiento( jsonArray.getJSONObject(i) );
                listOfItems.add(movimiento);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return listOfItems;
    }
}
