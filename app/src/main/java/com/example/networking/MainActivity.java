package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";
    private ArrayList<Mountain> listOfMountains = new ArrayList<>();
    private com.example.networking.RecyclerViewAdapter RecyclerViewAdapter;
    private List<RecyclerViewItem> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       new JsonFile(this, this).execute(JSON_FILE);


       
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        try {
            JSONArray jsonHolder = new JSONArray(json);
            
            //JSONObject kinnekulle = (JSONObject) jsonHolder.get(0);
            //Log.d("MainActivity_Kinnekulle", kinnekulle.getString("name"));

            for(int i = 0; i < jsonHolder.length(); i++){
                JSONObject elements = (JSONObject) jsonHolder.get(i);
                Log.d("MainActivity_JSON", elements.getString("name"));
                Log.d("MainActivity_JSON", elements.getString("type"));
                String name = elements.getString("name");
                String type = elements.getString("type");
                listOfMountains.add(new Mountain(name, type));
            }
            Log.d("MainActivity_Mountains", listOfMountains.size() + "");
            for(Mountain m : listOfMountains){
                Log.d("MainActivity_Mountains", m.getName() + " " + m.getType());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("MainActivity_JSONException", "fel");
        }
    }

}
