package com.example.numad21su_mikaylawerzanski;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class WebService extends AppCompatActivity {
    private final String TAG = "WebServiceActivity";
    private String categoryId;
    private String url;
    private TextView view;
    private Spinner spinner;
    private String response;
    private static final String[][] categories = {{"9","General Knowledge"},{"10","Entertainment: Books"},
            {"11","Entertainment: Film"},{"12","Entertainment: Music"},{"13","Entertainment: Musicals & Theatres"},
            {"14","Entertainment: Television"},{"15","Entertainment: Video Games"},{"16","Entertainment: Board Games"},
            {"17","Science & Nature"},{"18","Science: Computers"},{"19","Science: Mathematics"},{"20","Mythology"},
            {"21","Sports"},{"22","Geography"},{"23","History"},{"24","Politics"},{"25","Art"},{"26","Celebrities"},
            {"27","Animals"},{"28","Vehicles"},{"29","Entertainment: Comics"},{"30","Science: Gadgets"},
            {"31","Entertainment: Japanese Anime & Manga"},{"32","Entertainment: Cartoon & Animations"}};
    private static String[] paths = {"General Knowledge","Entertainment: Books",
            "Entertainment: Film","Entertainment: Music","Entertainment: Musicals & Theatres",
            "Entertainment: Television","Entertainment: Video Games","Entertainment: Board Games",
            "Science & Nature","Science: Computers","Science: Mathematics","Mythology",
            "Sports","Geography","History","Politics","Art","Celebrities",
            "Animals","Vehicles","Entertainment: Comics","Science: Gadgets",
            "Entertainment: Japanese Anime & Manga","Entertainment: Cartoon & Animations"};
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.print("onCreate.\n");
        setContentView(R.layout.activity_web_service);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(WebService.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    default:
                        for(int i = 0; i < categories.length; i++) {
                            if (paths[position] == categories[i][1]) {
                                categoryId = categories[i][0];
                            }
                        }
                        url = "https://opentdb.com/api_count.php?category=" + categoryId;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categoryId = categories[0][0];
                url = "https://opentdb.com/api_count.php?category=" + categoryId;
            }
        });

//        Button getQuestions = findViewById(R.id.getQuestions);
//        getQuestions.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                System.out.println("Web service screen appears");
//            }
//        });
    }

//    public void pingWebServiceHandler(View view) {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: "+ response.substring(0,500));
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
//            }
//        });
//    }

    public void pingWebServiceHandler(View view) {
        PingWebService service = new PingWebService();
        service.onPostExecute(url);
    }

    private class PingWebService extends AsyncTask<String, Integer, String> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            System.out.print("HI.\n");
            try {
                url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setDoInput(true);
                con.connect();
                System.out.print("HELLOOOOO");

                InputStream stream = con.getInputStream();
                response = stream.toString();
//
//                System.out.print("before json.\n");
//                JSONObject jsonObject = new JSONObject(response);
//                String title = jsonObject.getString("title");
//                String body = jsonObject.getString("body");
//                String result = body;
//                System.out.print("JSON OBJ" + jsonObject);
//                return result;
                return response;

            } catch (MalformedURLException e) {
                System.out.print(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            return null;
        }

        protected void onPostExecute(String... s) {
            super.onPostExecute(s[0]);
            System.out.print(s.length);
            TextView result = findViewById(R.id.result);
            result.setText(s[0]);
        }
    }
}
