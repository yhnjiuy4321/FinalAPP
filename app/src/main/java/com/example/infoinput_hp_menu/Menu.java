package com.example.infoinput_hp_menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
<<<<<<< HEAD
=======
import androidx.activity.result.ActivityResultLauncher;
>>>>>>> 474e7dfaf93340674b7008f4a9a381b0fbbd032c
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Menu extends AppCompatActivity {

<<<<<<< HEAD
=======
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

>>>>>>> 474e7dfaf93340674b7008f4a9a381b0fbbd032c
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
<<<<<<< HEAD
=======
       /* Intent intent = new Intent(this, info_input.class);
        //intent.putExtra("daily_amount", waterAmount);
        //回到info_input，而不適重新開始
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//解釋:意思是當info_input已經存在時，就不要再重新開啟一個新的info_input
        startActivity(intent);*/

        //Alter提醒是否確定要重設
>>>>>>> 474e7dfaf93340674b7008f4a9a381b0fbbd032c
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("確定要重設嗎?");
        builder.setMessage("注意!!重設後將重新計算今日飲水量!!");
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Menu.this, info_input.class);
<<<<<<< HEAD
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
=======
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//當info_input已經存在時，就不要再重新開啟一個新的info_input
>>>>>>> 474e7dfaf93340674b7008f4a9a381b0fbbd032c
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
<<<<<<< HEAD
                // 取消操作
=======
                //取消
>>>>>>> 474e7dfaf93340674b7008f4a9a381b0fbbd032c
            }
        });
        builder.show();
    }

<<<<<<< HEAD
    // 这个方法启动 DrinkingwaterbalanceActivity 并等待结果返回
    public void balance(View view){
        Intent intent = new Intent(this, DrinkingwaterbalanceActivity.class);
        startActivityForResult(intent, 1);  // 请求代码为 1
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // 接收从 DrinkingwaterbalanceActivity 返回的 daily_amount
            int updatedDailyAmount = data.getIntExtra("daily_amount", 0);

            // 创建一个 Intent 将结果传递给 MainActivity
            Intent intent = new Intent(Menu.this, MainActivity.class);
            intent.putExtra("daily_amount", updatedDailyAmount);
            startActivity(intent);  // 启动 MainActivity 并传递数据
        }
    }

=======


    /*public void balance(View view){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    public void introduce(View view){
        Intent intent = new Intent(this, xxx.class);
        startActivity(intent);
    }

    }*/
>>>>>>> 474e7dfaf93340674b7008f4a9a381b0fbbd032c
    public void history(View view) {
        Intent intent = new Intent(Menu.this, HistoryActivity.class);
        startActivity(intent);
    }
<<<<<<< HEAD
}
=======

}
>>>>>>> 474e7dfaf93340674b7008f4a9a381b0fbbd032c
