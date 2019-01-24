package com.example.priyanka.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefs";
    ListView listView;
    String getLanguages = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sharedPreferences = this.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        ArrayList<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("Html");
        languages.add("JQuery");
        languages.add("PHP");
        languages.add("JavaScript");

        //Converting ArrayList to String using serializer and Saving data in SharedPrefrences
        try {
            editor.putString("languages",ObjectSerializer.serialize(languages));
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }


       //getting string value from SharedPrefrences
       try {
            getLanguages = sharedPreferences.getString("languages",ObjectSerializer.serialize(new ArrayList<String>()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        listView=findViewById(R.id.listView);

        //deserializing the ArrayList(converting String to ArrayList) and showing in listview
       ArrayList<String> newLanguages = new ArrayList<>();

        try {
           newLanguages = (ArrayList<String>) ObjectSerializer.deserialize(getLanguages);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,newLanguages);
        listView.setAdapter(arrayAdapter);

        final ArrayList<String> finalNewLanguages = newLanguages;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

         @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),finalNewLanguages.get(i),Toast.LENGTH_LONG).show();

            }

        });
    }



}
