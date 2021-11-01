package com.dandelion.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        // save
        Button saveName = findViewById(R.id.userNameSave);
        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
                // share preferences
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
                SharedPreferences.Editor SharedPreferencesEditor = sharedPreferences.edit();
                // get name
                EditText userNameField = findViewById(R.id.nameField);
                String username = userNameField.getText().toString();
                //save to shared
                SharedPreferencesEditor.putString("username", username);
                SharedPreferencesEditor.apply();

            }
        });

    }
}