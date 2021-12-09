package com.example.onedayhobby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.inflate;

public class DescriptionActivity extends AppCompatActivity {

    private Intent intent;
    private TextView topTitle;
    private ImageView imageView;
    private TextView title;
    private TextView des;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        intent = getIntent();
        String titletext = intent.getStringExtra("title");
        int imageV = intent.getIntExtra("img", R.drawable.hobby01);
        String desText = intent.getStringExtra("des");
        Log.i("TAG", "onItemClick: icon2"+imageV);

        topTitle = (TextView) findViewById(R.id.top_title);
        imageView = (ImageView) findViewById(R.id.des_image);
        title = (TextView) findViewById(R.id.des_title);
        des = (TextView) findViewById(R.id.long_des);

        des.setMovementMethod(new ScrollingMovementMethod());

        topTitle.setText(titletext);
        imageView.setImageResource(imageV);
        title.setText(titletext);
        des.setText(desText);

        // 뒤로가기
        backBtn = findViewById(R.id.tos_pass_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setTopTitle(TextView topTitle) {
        this.topTitle = topTitle;
    }
}