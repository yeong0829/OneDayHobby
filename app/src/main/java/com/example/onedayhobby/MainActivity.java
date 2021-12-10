package com.example.onedayhobby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView hobbyListView;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 위젯과 멤버변수 참조 획득 */
        hobbyListView = (ListView)findViewById(R.id.hobby_list);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();

        // 뒤로가기
        backBtn = findViewById(R.id.tos_pass_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                startActivity(intent);
            }
        });


    }

    private void dataSetting(){
        // 취미 리스트
        HobbyAdapter hobbyAdapter = new HobbyAdapter();
        HobbyData hobbyData = new HobbyData();
        ArrayList<HobbyItem> hobbyItems = hobbyData.getHobbyItems();

        /* 리스트뷰에 어댑터 등록 */
        hobbyAdapter.setHobbyItems(hobbyItems);
        hobbyListView.setAdapter(hobbyAdapter);

        // listView의 ItemClickListener
        hobbyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // new Intent(현재 Activity의 Context, 시작할 Activity 클래스)
                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                intent.putExtra("title", hobbyItems.get(position).name);
                intent.putExtra("img", hobbyItems.get(position).getIcon());
                intent.putExtra("des", hobbyItems.get(position).longContents);
                startActivity(intent);
            }
        });
    }
}