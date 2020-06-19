package com.example.anik.weatherv3;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Anik on 12/4/2017.
 */

public class ResultActivity extends Activity {

    private  TextView Description, Temp, Location;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);
        Location=(TextView) findViewById(R.id.Locationid);
        Description=(TextView)findViewById(R.id.Descriptionid);
        Temp=(TextView)findViewById(R.id.Tempid);

        class My_json extends AsyncTask<String, String,String> {

            private Exception error;
            @Override
            protected void onPreExecute() {

            }
            protected String doInBackground(String... strings) {

                String urlStr=strings[0];  //the sent url is loaded to urlStr from strings array


                try {

                    URL url = new URL(urlStr);  //string converted to URL type
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();  //as HTTPS
                    connection.connect();   //connecting to the server

                    InputStream stream = connection.getInputStream();  //downloading the file
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));  //store the read data from server line by line to memory

                    StringBuilder StrBfr = new StringBuilder();  //store the value as String
                    String line = "";

                    while ((line = reader.readLine()) != null)  //reading the file line by line
                    {
                        StrBfr.append(line);   //adding the line to Stringbuffer
                    }

                    String jsonStr = StrBfr.toString();   //convert Stringbuffer to pure String
                    return jsonStr;


                } catch (Exception e) {
                    error=e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject JObj = new JSONObject(s);


                    JSONObject Jquery = JObj.getJSONObject("query");
                    JSONObject Jresults = Jquery.getJSONObject("results");
                    JSONObject Jchannel=Jresults.getJSONObject("channel");
                    JSONObject Jlocation=Jchannel.getJSONObject("location");
                    Location.setText(Jlocation.getString("city"));
                    JSONObject Jitem=Jchannel.getJSONObject("item");
                    JSONObject Jcondition=Jitem.getJSONObject("condition");
                    Temp.setText(Jcondition.getString("temp"));
                    Description.setText(Jcondition.getString("text"));

                } catch (Exception e)
                {
                    return;
                }


            }
        }
        My_json example=new My_json();
        example.execute("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json");


    }

}
