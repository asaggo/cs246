package com.example.teamactivity06new;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createFile(View theButton) {

        String filename = "number.txt";
        File file = new File(getFilesDir(), filename);

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, MODE_PRIVATE);
            for (int i = 1; i <= 10; i++) {
                String temp = String.valueOf(i) + "\r\n";
                outputStream.write(temp.getBytes());
                Thread.sleep(250);
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFile(View theButton) {
        String filename = "number.txt";
        List<String> list = new ArrayList<String>();
        FileInputStream inputStream;
        try {
            inputStream = openFileInput(filename);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        list.add(line);
                        Thread.sleep(250);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }


        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
    }

    public void clearList(View theButton){
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(null);
    }
}
