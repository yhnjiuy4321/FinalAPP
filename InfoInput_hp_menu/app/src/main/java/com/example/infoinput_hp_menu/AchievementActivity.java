package com.example.infoinput_hp_menu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AchievementActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        ImageView rewardImageView = findViewById(R.id.rewardImageView);
        TextView congratsTextView = findViewById(R.id.congratsTextView);

        rewardImageView.setImageResource(R.drawable.good); // 獎勵圖片
        congratsTextView.setText("恭喜達成今日飲水目標！繼續保持！");
    }

    public void backToMain(View view){
        finish();
    }

}
