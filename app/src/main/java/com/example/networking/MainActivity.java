package com.example.networking;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";
    private ArrayList<Mountain> listOfMountains = new ArrayList<>();
    private com.example.networking.RecyclerViewAdapter RecyclerViewAdapter;
    private List<RecyclerViewItem> items = new ArrayList<>();

    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(RecyclerViewItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);


        items.add(new RecyclerViewItem("hello"));

    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);


        Gson gson = new Gson();
        json = gson.toJson(listOfMountains);
        Mountain mountain = gson.fromJson(json, Mountain.class);
        Type type = new TypeToken<List<Mountain>>() {}.getType();
        List<Mountain> listOfMountains = gson.fromJson(json, type);



        try {
            JSONArray jsonHolder = new JSONArray(json);

            //JSONObject kinnekulle = (JSONObject) jsonHolder.get(0);
            //Log.d("MainActivity_Kinnekulle", kinnekulle.getString("name"));

            for(int i = 0; i < jsonHolder.length(); i++){
                JSONObject elements = (JSONObject) jsonHolder.get(i);
                Log.d("MainActivity_JSON", elements.getString("name"));

                String name = elements.getString("name");
                String type1 = elements.getString("type");
                listOfMountains.add(new Mountain(name, type1));
            }
            Log.d("MainActivity_Mountains", listOfMountains.size() + "");
            for(Mountain m : listOfMountains){
                Log.d("MainActivity_Mountains", m.getName() + " ");
                items.add(new RecyclerViewItem(m.getName()));

            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("MainActivity_JSONException", "fel");

        }
    }



}

