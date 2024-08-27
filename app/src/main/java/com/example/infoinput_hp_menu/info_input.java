package com.example.infoinput_hp_menu;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 474e7dfaf93340674b7008f4a9a381b0fbbd032c

import com.google.android.material.textfield.TextInputEditText;

public class info_input extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


<<<<<<< HEAD

=======
>>>>>>> 474e7dfaf93340674b7008f4a9a381b0fbbd032c
    int daily_amount = 0;
    double Weight = 0;

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


        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
    }



    /*public void select(View view){
        String[] items = getResources().getStringArray(R.array.select_activity);
        Spinner spinner = findViewById(R.id.spinner);
        int position = spinner.getSelectedItemPosition();
        String selected = items[position];
        switch (selected){
            case "很多":
                Weight = 1.4;
                break;
            case "較多":
                Weight = 1.2;
                break;
            case "適中":
                Weight = 1;
                break;
            case "較少":
                Weight = 0.8;
                break;
            case "很少":
                Weight = 0.6;
                break;
        }
    }*/

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String[] items = getResources().getStringArray(R.array.select_activity);
        String selected = items[i];
        switch (selected){
            case "很多":
                Weight = 1.4;
                break;
            case "較多":
                Weight = 1.2;
                break;
            case "適中":
                Weight = 1;
                break;
            case "較少":
                Weight = 0.8;
                break;
            case "很少":
                Weight = 0.6;
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Weight = 1;
    }


    public void submit(View view){

   EditText name = findViewById(R.id.input_name);
   EditText height = findViewById(R.id.input_h);
   EditText weight = findViewById(R.id.input_w);

    String user_name = name.getText().toString();
    //把身高體重轉成int
    int user_height = Integer.parseInt(height.getText().toString());
    int user_weight = Integer.parseInt(weight.getText().toString());


    //計算每日所需水量
    daily_amount = (int) ((user_height + user_weight) *10 *Weight);
<<<<<<< HEAD
    Log.d("daily_amount", String.valueOf(daily_amount));
=======
>>>>>>> 474e7dfaf93340674b7008f4a9a381b0fbbd032c


    //Alter提醒是否確定送出資料
    new AlertDialog.Builder(this)
            .setTitle("確定送出資料")
            .setMessage("姓名: " + user_name + "\n" + "身高: " + user_height + "\n" + "體重: " + user_weight + "\n" + "每日所需水量: " + daily_amount + "ml")
            .setPositiveButton("確定", (dialogInterface, i) -> {
                //把daily_amount與name傳到下一個MainActivity
                Bundle bundle = new Bundle();
                bundle.putInt("daily_amount", daily_amount);
                bundle.putString("name", user_name);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            })
            .setNegativeButton("取消", (dialogInterface, i) -> {
                dialogInterface.cancel();
            })
            .show();
    }

   /* //把daily_amount與name傳到下一個MainActivity
    Bundle bundle = new Bundle();
    bundle.putInt("daily_amount", daily_amount);
    bundle.putString("name", user_name);
    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtras(bundle);
    startActivity(intent);
    }
*/

}