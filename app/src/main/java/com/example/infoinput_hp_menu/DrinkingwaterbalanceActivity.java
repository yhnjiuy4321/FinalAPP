package com.example.infoinput_hp_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class DrinkingwaterbalanceActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private Button buttonDrinkSelection;
    private Button buttonCupCount;
    private Button buttonCalculate;
    private Button buttonAddToGoal;
    private Button buttonBack;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private HashMap<String, Integer> drinkCalories;
    private String selectedDrink = "";
    private int selectedCupCount = 1;
    private int calculatedAmount; // 用于存储计算结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinkingwaterbalance);

        // 初始化组件
        editText1 = findViewById(R.id.Text1);
        editText2 = findViewById(R.id.Text2);
        editText3 = findViewById(R.id.Text3);
        imageButton = findViewById(R.id.imageButton);
        buttonDrinkSelection = findViewById(R.id.button2);
        buttonCupCount = findViewById(R.id.button);
        buttonCalculate = findViewById(R.id.button8);
     /*   buttonAddToGoal = findViewById(R.id.button7);*/
        buttonBack = findViewById(R.id.button6);

        // 初始化饮品卡路里数据
        drinkCalories = new HashMap<>();
        drinkCalories.put("茶類(1大卡)", 1);
        drinkCalories.put("咖啡(88大卡)", 88);
        drinkCalories.put("果汁(444大卡)", 444);
        drinkCalories.put("奶類(224大卡)", 224);
        drinkCalories.put("氣泡飲料(272大卡)", 272);
        drinkCalories.put("運動飲料(686大卡)", 686);

        // 设置按钮点击事件
        buttonDrinkSelection.setOnClickListener(this::showDrinkSelectionMenu);
        buttonCupCount.setOnClickListener(this::showCupCountMenu);
        buttonCalculate.setOnClickListener(v -> calculateAndDisplayResult());



        imageButton.setOnClickListener(v -> finish());
        buttonBack.setOnClickListener(v -> finish());
    }

    private void showDrinkSelectionMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.drink_selection_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            selectedDrink = item.getTitle().toString().trim();
            editText1.setText("選擇的飲品: " + selectedDrink);
            return true;
        });
        popupMenu.show();
    }

    private void showCupCountMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.cup_count_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            String cupCountStr = item.getTitle().toString().replace("杯", "").trim();
            selectedCupCount = Integer.parseInt(cupCountStr);
            editText2.setText("選擇的杯數: " + selectedCupCount);
            return true;
        });
        popupMenu.show();
    }

    private void calculateAndDisplayResult() {
        Integer caloriesPerCup = drinkCalories.get(selectedDrink);
        if (caloriesPerCup == null) {
            Toast.makeText(this, "選擇的飲品無法計算，請重新選擇", Toast.LENGTH_SHORT).show();
            return;
        }
        double requiredWater = WaterCalculator.calculateRequiredWater(caloriesPerCup, selectedCupCount);
        calculatedAmount = (int) requiredWater;
        String resultText = "總卡路里: " + (caloriesPerCup * selectedCupCount) + " kcal\n需要水量: 約 " + calculatedAmount + " ml";
        editText3.setText("計算結果: " + resultText);
    }
}
