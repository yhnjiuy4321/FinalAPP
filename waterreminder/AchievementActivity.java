package com.example.waterreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AchievementActivity extends AppCompatActivity {

    private int TotalWater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        ImageView rewardImageView = findViewById(R.id.rewardImageView);
        TextView congratsTextView = findViewById(R.id.congratsTextView);
        Button backToMainButton = findViewById(R.id.backToMainButton);

        // 獲取從主頁面傳遞過來的飲水進度
        int totalWaterAmount = getIntent().getIntExtra("totalWaterAmount", 0);
        int waterGoal = getIntent().getIntExtra("waterGoal", 2000);

        TotalWater+=totalWaterAmount;
        int difference = waterGoal - TotalWater;


        // 根據飲水進度顯示不同的圖片和訊息
        if(difference <= 0){
            rewardImageView.setImageResource(R.drawable.reward_image); // 獎勵圖片
           congratsTextView.setText("恭喜達成今日飲水目標！繼續保持！");
        } else if (difference <= waterGoal/2) {
            rewardImageView.setImageResource(R.drawable.motivational_image); // 加油圖片
            congratsTextView.setText("你還差 " + difference + " 毫升！快達標了，加油！");
        }
        else {
            rewardImageView.setImageResource(R.drawable.punishment_image); // 懲罰圖片
            congratsTextView.setText("你還差 " + difference + " 毫升就能達標！加油！");
        }

        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AchievementActivity.this, MainActivity.class);
               intent.putExtra("totalWaterAmount", TotalWater);
                startActivity(intent);
            }
        });
    }
}
