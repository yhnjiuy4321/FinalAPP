package com.example.infoinput_hp_menu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class info_input extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_input);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void submit(View view){

   TextView name = findViewById(R.id.name);
   TextView height = findViewById(R.id.input_h);
   TextView weight = findViewById(R.id.input_w);
   Spinner Activity = findViewById(R.id.spinner);

    String user_name = name.getText().toString();
    //把身高體重轉成int
    int user_height = Integer.parseInt(height.getText().toString());
    int user_weight = Integer.parseInt(weight.getText().toString());
    //把運動量轉成int


    }



}