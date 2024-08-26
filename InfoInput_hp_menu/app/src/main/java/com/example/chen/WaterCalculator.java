package com.example.chen;

public class WaterCalculator {

    public static double calculateRequiredWater(int caloriesPerCup, int cupCount) {
        // 计算总卡路里
        int totalCalories = caloriesPerCup * cupCount;
        // 根据总卡路里计算所需的水量
        return totalCalories * 1.5; // 每大卡需要1.5ml的水
    }
}
