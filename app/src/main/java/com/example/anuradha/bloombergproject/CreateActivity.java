package com.example.anuradha.bloombergproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity {

    public static boolean unless;
    Spinner spinner;
    ArrayList list;
    EditText typeVersion, typeUptime, typeHostname;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        list = new ArrayList();
        list.add("prod");
        list.add("storage");
        list.add("tdmz");
        list.add("bcloud");
        list.add("dev");
        list.add("admin");
        list.add("corp");
        list.add("inet");
        list.add("feed");
        list.add("apex");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, list);

        spinner = findViewById(R.id.id_spinner);
        spinner.setAdapter(adapter);

        typeVersion = findViewById(R.id.id_typeVersion);
        typeUptime = findViewById(R.id.id_typeUptime);
        typeHostname = findViewById(R.id.id_typeHost);
        add = findViewById(R.id.id_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj = new JSONObject();
                unless = true;
                try {
                    obj.put("hostname", String.valueOf(typeHostname.getText()));
                    obj.put("uptime", String.valueOf(typeUptime.getText()));
                    obj.put("version", String.valueOf(typeVersion.getText()));
                    obj.put("area", spinner.getSelectedItem().toString());

                    MainActivity.jsonArray.put(obj);
                    Log.d("REE", ""+MainActivity.jsonArray.get(MainActivity.jsonArray.length()-1));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(CreateActivity.this, MainActivity.class));
            }
        });



    }
}
