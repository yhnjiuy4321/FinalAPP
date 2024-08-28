package com.example.infoinput_hp_menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    private ListView historyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = findViewById(R.id.historyListView);

        // 獲取 SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("WaterIntakeHistory", Context.MODE_PRIVATE);

        // 獲取所有已保存的記錄
        Map<String, ?> allEntries = sharedPref.getAll();
        ArrayList<String> historyList = new ArrayList<>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String date = entry.getKey();
            int amount = (int) entry.getValue();
            historyList.add(date + ": " + amount + "ml");
        }

        // 將歷史記錄顯示在 ListView 中
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        historyListView.setAdapter(adapter);
    }
    public void goBack(View view) {
        finish(); // 結束當前的 Activity，返回到上一頁
    }

}
