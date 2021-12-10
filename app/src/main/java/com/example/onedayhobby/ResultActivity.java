package com.example.onedayhobby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private Button homeBtn;
    private ImageView resImg;
    private TextView resTitle;
    private TextView resDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resImg = findViewById(R.id.res_image);
        resTitle = findViewById(R.id.res_title);
        resDes = findViewById(R.id.res_des);

        HobbyData hobbyData = new HobbyData();
        ArrayList<HobbyItem> hobbyItems = hobbyData.getHobbyItems();

        Random random = new Random();
        int index = random.nextInt(hobbyItems.size());

        resImg.setImageResource(hobbyItems.get(index).getIcon());
        resTitle.setText(hobbyItems.get(index).name);
        resDes.setText(hobbyItems.get(index).contents);

        // 시작화면으로
        homeBtn = findViewById(R.id.home_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                startActivity(intent);
            }
        });

        // 뒤로가기버튼
        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RandomActivity.class);
                startActivity(intent);
            }
        });
    }
}