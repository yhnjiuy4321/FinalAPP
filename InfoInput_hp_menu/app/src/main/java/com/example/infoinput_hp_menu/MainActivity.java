package com.example.infoinput_hp_menu;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private TextView Name;
    private int waterAmount;
    private int dailyAmount;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //設定日期
        Calendar calendar = Calendar.getInstance();
        TextView dateTextView = findViewById(R.id.dateTextView);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 EEEE", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());
        dateTextView.setText(date);


        //設定提醒
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Reminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);//FLAG_UPDATE_CURRENT:如果該PendingIntent已經存在，則用新的Intent更新它

        // 每10秒提醒一次
        long interval = 10 * 1000;//
        long triggerAtMillis = System.currentTimeMillis() + interval;

        /*long interval = AlarmManager.INTERVAL_HOUR;
        long triggerAtMillis = System.currentTimeMillis() + interval;*/

        // 使用定期提醒
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, interval, pendingIntent);

        // 或者使用精确提醒
        // alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);





        Intent intentW = getIntent();
        Bundle bundle = intentW.getExtras();
        if (bundle != null) {
            dailyAmount = bundle.getInt("daily_amount");
            String username = bundle.getString("name");
            title = findViewById(R.id.daliyAmount);
            Name = findViewById(R.id.User_name);
            title.setText("您每日需要飲水量為："+dailyAmount+"ml");
            Name.setText(username);
        }
       ImageButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddWaterDialog();
            }
        });

    }

    private void setDateTextView() {

    }

    private void showAddWaterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Create a LinearLayout to hold the title and input
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);



        // Create a TextView for the title
        TextView title = new TextView(this);
        builder.setTitle("輸入水量");
        title.setTextSize(20);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        final EditText input = new EditText(this);
        input.setGravity(Gravity.CENTER);
        layout.addView(input);

        builder.setView(layout);

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputText = input.getText().toString();
                if (!inputText.isEmpty()) {
                    int addedAmount = Integer.parseInt(inputText);
                    addWater(addedAmount);
                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void addWater(int amount) {
        waterAmount += amount;
        updateUI();
    }
    private void updateUI() {
        TextView amountShow = findViewById(R.id.amountShow);
        amountShow.setText(String.valueOf(waterAmount));
        //當喝水量達標時，將數字顯示為綠色
        if (waterAmount >= dailyAmount) {
            amountShow.setTextColor(getResources().getColor(R.color.green));
        }
    }

    public void menu(View view){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    private void setReminder() {
         }


}