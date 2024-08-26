package com.example.waterreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText waterAmountEditText;
    private TextView totalWaterTextView;
    private int totalWaterAmount;
    private final int waterGoal = 2000; // 每日飲水目標（毫升）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化視圖
        waterAmountEditText = findViewById(R.id.waterAmountEditText);
        totalWaterTextView = findViewById(R.id.totalWaterTextView);
        Button recordButton = findViewById(R.id.recordButton);
        Button viewReportButton = findViewById(R.id.viewReportButton);

        // 從 Intent 獲取累積的總水量
        totalWaterAmount += getIntent().getIntExtra("totalWaterAmount", 0);
        updateWaterProgress();

        // 設置記錄按鈕的點擊事件
        recordButton.setOnClickListener(v -> recordWaterIntake());

        // 設置查看報告按鈕的點擊事件
        viewReportButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReportActivity.class)));
    }

    private void recordWaterIntake() {
        String waterAmountStr = waterAmountEditText.getText().toString();
        if (!waterAmountStr.isEmpty()) {
            int waterAmount = Integer.parseInt(waterAmountStr);
            totalWaterAmount += waterAmount;
            updateWaterProgress();

            // 跳轉到成就頁面
            Intent intent = new Intent(MainActivity.this, AchievementActivity.class);
            intent.putExtra("totalWaterAmount", totalWaterAmount);
            intent.putExtra("waterGoal", waterGoal);
            startActivity(intent);

            Toast.makeText(MainActivity.this, "已記錄飲水量", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "請輸入飲水量", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateWaterProgress() {
        totalWaterTextView.setText(String.format("今日總飲水量：%d 毫升", totalWaterAmount));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("totalWaterAmount", totalWaterAmount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            totalWaterAmount = savedInstanceState.getInt("totalWaterAmount", 0);
            updateWaterProgress();
        }
    }


    //(身高+體重)*10cc
//    protected  int GetTotalWaterAmount(int height,int weight){
//        return  (height+weight)*10;
//    }

    //體重*30cc
//    protected  int GetTotalWaterAmount(int weight){
//        return  weight*30;
//    }
}
