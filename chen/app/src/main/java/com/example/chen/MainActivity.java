package com.example.chen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private Button buttonDrinkSelection;
    private Button buttonCupCount;
    private Button buttonCalculate;
    private Button buttonAddToGoal; // 新增加入今日目标按钮
    private Button buttonBack; // 返回按钮
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private HashMap<String, Integer> drinkCalories;
    private String selectedDrink = "";
    private int selectedCupCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取EditText的引用
        editText1 = findViewById(R.id.Text1);
        editText2 = findViewById(R.id.Text2);
        editText3 = findViewById(R.id.Text3);

        // 初始化按钮
        imageButton = findViewById(R.id.imageButton);
        buttonDrinkSelection = findViewById(R.id.button2);
        buttonCupCount = findViewById(R.id.button);
        buttonCalculate = findViewById(R.id.button8);
        buttonAddToGoal = findViewById(R.id.button7); // 初始化加入今日目标按钮
        buttonBack = findViewById(R.id.button6); // 返回按钮

        // 初始化饮品卡路里数据
        drinkCalories = new HashMap<>();
        drinkCalories.put("茶類(1大卡)",1);
        drinkCalories.put("咖啡(88大卡)",88);
        drinkCalories.put("果汁(444大卡)",444);
        drinkCalories.put("奶類(224大卡)",224);
        drinkCalories.put("氣泡飲料(272大卡)",272);
        drinkCalories.put("運動飲料(686大卡)",686);

        // 饮品选择按钮点击事件
        buttonDrinkSelection.setOnClickListener(this::showDrinkSelectionMenu);

        // 杯数选择按钮点击事件
        buttonCupCount.setOnClickListener(this::showCupCountMenu);

        // 计算按钮点击事件
        buttonCalculate.setOnClickListener(v -> {
            if (!selectedDrink.isEmpty() && selectedCupCount > 0) {
                calculateAndDisplayResult(); // 调用计算方法
            } else {
                Toast.makeText(MainActivity.this, "請先選擇飲品和杯數", Toast.LENGTH_SHORT).show();
            }
        });

        // 返回按钮点击事件（imageButton）
        imageButton.setOnClickListener(v -> {
            finish(); // 结束当前Activity，返回到主画面
        });

        // 返回按钮点击事件（button6）
        buttonBack.setOnClickListener(v -> {
            finish(); // 结束当前Activity，返回到主画面
        });

        // 加入今日目標按钮点击事件
        buttonAddToGoal.setOnClickListener(v -> showAddToGoalDialog());
    }

    private void showDrinkSelectionMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.drink_selection_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            selectedDrink = item.getTitle().toString().trim();
            Log.d("MainActivity", "Drink selected: " + selectedDrink);
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
        Log.d("MainActivity", "Calculate button clicked");

        // 输出 selectedDrink 的实际值
        Log.d("MainActivity", "Selected Drink: '" + selectedDrink + "'");

        // 输出字典中的所有键
        for (String key : drinkCalories.keySet()) {
            Log.d("MainActivity", "Available Drink: '" + key + "'");
        }

        Integer caloriesPerCup = drinkCalories.get(selectedDrink);

        if (caloriesPerCup == null) {
            Log.d("MainActivity", "Calories per Cup is null for selected drink");
            Toast.makeText(MainActivity.this, "選擇的飲品無法計算，請重新選擇", Toast.LENGTH_SHORT).show();
            return;
        }

        // 继续计算逻辑
        double requiredWater = WaterCalculator.calculateRequiredWater(caloriesPerCup, selectedCupCount);

        String resultText = "總卡路里: " + (caloriesPerCup * selectedCupCount) + " kcal\n需要水量: 約 " + (int)requiredWater + " ml";
        editText3.setText("計算結果: " + resultText);

        Log.d("MainActivity", "Calculation Result: " + resultText);
    }

    // 显示加入今日目标完成的对话框
    private void showAddToGoalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("加入完成")
                .setMessage("您已成功將飲品加入今日目標。")
                .setPositiveButton("返回當前頁面", (dialog, id) -> dialog.dismiss()) // 只关闭对话框
                .setNegativeButton("返回主頁面", (dialog, id) -> {
                    dialog.dismiss();
                    finish(); // 结束当前Activity，返回主页面
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
