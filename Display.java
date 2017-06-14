package com.example.testingcenterv01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String username = getIntent().getStringExtra("username");
        String firstname = getIntent().getStringExtra("firstname");
        String lastname = getIntent().getStringExtra("lastname");
        /*boolean isAdmin = getIntent().getStringExtra("isAdmin");*/

        TextView textView = (TextView)findViewById(R.id.usernameDisplayText);
        textView.setText(firstname);
    }


}
