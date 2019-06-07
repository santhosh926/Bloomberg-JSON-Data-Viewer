package com.example.anuradha.bloombergproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    static TextView area, version, uptime, hostname, currIndex;
    EditText jump, nameSearch;
    Button prev, next, go, piechart, search, create;
    String json;
    static JSONArray jsonArray;
    static JSONObject obj;
    static int index;
    static double storage, prod, bcloud, dev, admin, corp, inet, feed, apex, tdmz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        area = findViewById(R.id.id_area);
        version = findViewById(R.id.id_version);
        uptime = findViewById(R.id.id_uptime);
        hostname = findViewById(R.id.id_hostname);
        jump = findViewById(R.id.id_jump);
        currIndex = findViewById(R.id.id_currIndex);
        create = findViewById(R.id.id_create);
        nameSearch = findViewById(R.id.id_nameSearch);
        search = findViewById(R.id.id_search);
        prev = findViewById(R.id.id_prev);
        next = findViewById(R.id.id_next);
        go = findViewById(R.id.id_go);
        piechart = findViewById(R.id.id_piechart);
        index = 0;

        try {
            InputStream is = getAssets().open("SBHSData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            
            if(!CreateActivity.unless)
            jsonArray = new JSONArray(json);

            for(int i = 0; i <jsonArray.length(); i++){
                switch(jsonArray.getJSONObject(i).getString("area")){
                    case "storage": storage++;
                        break;
                    case "prod": prod++;
                        break;
                    case "bcloud": bcloud++;
                        break;
                    case "dev": dev++;
                        break;
                    case "admin": admin++;
                        break;
                    case "corp": corp++;
                        break;
                    case "inet": inet++;
                        break;
                    case "feed": feed++;
                        break;
                    case "apex": apex++;
                        break;
                    case "tdmz": tdmz++;
                    break;
                }
            }

            storage /= jsonArray.length() / 100;
            prod /= jsonArray.length() / 100;
            bcloud /= jsonArray.length() / 100;
            dev /= jsonArray.length() / 100;
            admin /= jsonArray.length() / 100;
            corp /= jsonArray.length() / 100;
            inet /= jsonArray.length() / 100;
            feed /= jsonArray.length() / 100;
            apex /= jsonArray.length() / 100;
            tdmz /= jsonArray.length() /100;

            obj = jsonArray.getJSONObject(index);
            area.setText(obj.getString("area"));
            version.setText(obj.getString("version"));
            uptime.setText(obj.getString("uptime"));
            hostname.setText(obj.getString("hostname"));
            currIndex.setText("index: " + index);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((index+1)<jsonArray.length()) index++;

                else Toast.makeText(getApplicationContext(), "INVALID INDEX!", Toast.LENGTH_SHORT).show();

                try {
                    obj = jsonArray.getJSONObject(index);
                    area.setText(obj.getString("area"));
                    version.setText(obj.getString("version"));
                    uptime.setText(obj.getString("uptime"));
                    hostname.setText(obj.getString("hostname"));
                    currIndex.setText("index: " + index);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index!=0) index--;

                else Toast.makeText(getApplicationContext(), "INVALID INDEX!", Toast.LENGTH_SHORT).show();

                try {
                    obj = jsonArray.getJSONObject(index);
                    area.setText(obj.getString("area"));
                    version.setText(obj.getString("version"));
                    uptime.setText(obj.getString("uptime"));
                    hostname.setText(obj.getString("hostname"));
                    currIndex.setText("index: " + index);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(String.valueOf(jump.getText())) > 0 || Integer.parseInt(String.valueOf(jump.getText())) < jsonArray.length())
                    index = Integer.parseInt(String.valueOf(jump.getText()));

                else Toast.makeText(getApplicationContext(), "INVALID INDEX!", Toast.LENGTH_SHORT).show();

                try {
                    obj = jsonArray.getJSONObject(index);
                    area.setText(obj.getString("area"));
                    version.setText(obj.getString("version"));
                    uptime.setText(obj.getString("uptime"));
                    hostname.setText(obj.getString("hostname"));
                    currIndex.setText("index: " + index);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            boolean found = false;
            @Override
            public void onClick(View v) {
                for(int i = 0; i <jsonArray.length(); i++) {
                    try {
                        if (jsonArray.getJSONObject(i).getString("hostname").equals(String.valueOf(nameSearch.getText()))) {
                            found = true;
                            index = i;
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if(found){
                    try {
                        obj = jsonArray.getJSONObject(index);
                        area.setText(obj.getString("area"));
                        version.setText(obj.getString("version"));
                        uptime.setText(obj.getString("uptime"));
                        hostname.setText(obj.getString("hostname"));
                        currIndex.setText("index: " + index);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                else Toast.makeText(getApplicationContext(), "INVALID!", Toast.LENGTH_SHORT).show();

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
            }
        });

        piechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PieChartActivity.class));
            }
        });

    }

}