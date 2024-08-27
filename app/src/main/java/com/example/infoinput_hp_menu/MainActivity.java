package com.example.infoinput_hp_menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
public class MainActivity extends AppCompatActivity {

    private TextView title;
    private TextView Name;
    private EditText Texttext1; // 初始化 Texttext1
    private int waterAmount;
    private int dailyAmount;
    private boolean getgoal = true;
    private int ADDAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //time:yyyy-MM-dd 星期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E", Locale.getDefault());
        String currentDate = sdf.format(Calendar.getInstance().getTime());
        TextView date = findViewById(R.id.dateTextView);
        date.setText(currentDate);

        Intent intentW = getIntent();
        Bundle bundle = intentW.getExtras();
        if (bundle != null) {
            dailyAmount = bundle.getInt("daily_amount");
            String username = bundle.getString("name");
            title = findViewById(R.id.daliyAmount);
            Name = findViewById(R.id.User_name);
            title.setText("您每日需要飲水量為：" + dailyAmount + "ml");
            Name.setText(username);
        }


        ImageButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> showAddWaterDialog());

        // 新增的 "記錄今日飲水量" 按鈕
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> saveWaterIntake());
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
            if (getgoal) {
                Intent intent = new Intent(this, AchievementActivity.class);
                startActivity(intent);
                getgoal = false;
            }
        }
    }

    private void saveWaterIntake() {
        // 獲取 SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("WaterIntakeHistory", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // 保存今日的飲水量
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = sdf.format(Calendar.getInstance().getTime());
        editor.putInt(currentDate, waterAmount);
        editor.apply();

        // 顯示提示
        Toast.makeText(this, "今日飲水量已記錄", Toast.LENGTH_SHORT).show();
    }

    public void menu(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}
