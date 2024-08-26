package com.example.infoinput_hp_menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {

    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void back(View view){
        finish();
    }

    public void reset(View view){
       /* Intent intent = new Intent(this, info_input.class);
        //intent.putExtra("daily_amount", waterAmount);
        //回到info_input，而不適重新開始
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//解釋:意思是當info_input已經存在時，就不要再重新開啟一個新的info_input
        startActivity(intent);*/

        //Alter提醒是否確定要重設
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("確定要重設嗎?");
        builder.setMessage("注意!!重設後將重新計算今日飲水量!!");
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Menu.this, info_input.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//當info_input已經存在時，就不要再重新開啟一個新的info_input
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //取消
            }
        });
        builder.show();
    }

    /*public void history(View view){
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }


    public void balance(View view){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    public void introduce(View view){
        Intent intent = new Intent(this, xxx.class);
        startActivity(intent);
    }

    }*/



}