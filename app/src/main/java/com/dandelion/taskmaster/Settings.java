package com.dandelion.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.core.Amplify;

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
                RadioButton firstSet = findViewById(R.id.firstSettings);
                RadioButton secondSet    = findViewById(R.id.secondSettings);
                RadioButton thirdSet      = findViewById(R.id.thirdSettings);

                // share preferences
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
                SharedPreferences.Editor SharedPreferencesEditor = sharedPreferences.edit();
                // get name
                EditText userNameField = findViewById(R.id.nameField);
                String username = userNameField.getText().toString();

                if (firstSet.isChecked()){
                    SharedPreferencesEditor.putString("team", firstSet.getText().toString());
                }else if(secondSet.isChecked()){
                    SharedPreferencesEditor.putString("team", secondSet.getText().toString());
                }else if(thirdSet.isChecked()){
                    SharedPreferencesEditor.putString("team", thirdSet.getText().toString());
                }
                //save to shared
                SharedPreferencesEditor.putString("username", username);
                SharedPreferencesEditor.apply();

                Intent intent = new Intent(Settings.this , MainActivity.class);
                startActivity(intent);

            }
        });

    }
}