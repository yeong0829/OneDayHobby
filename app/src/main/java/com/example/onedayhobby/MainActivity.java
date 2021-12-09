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
    private ListView doListView;

    private boolean isFabOpen = false; // Fab 버튼 default 는 닫혀있음

    private FloatingActionButton fabMain;
    private FloatingActionButton fabDel;
    private FloatingActionButton fabEdit;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 위젯과 멤버변수 참조 획득 */
        hobbyListView = (ListView)findViewById(R.id.hobby_list);

        /* 위젯과 멤버변수 참조 획득 */
        doListView = (ListView)findViewById(R.id.do_list);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();

        // 메인 플로팅 버튼 클릭
        fabMain = findViewById(R.id.info_fab);
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFab();
            }
        });

        // 삭제 플로팅 버튼 클릭
        fabDel = findViewById(R.id.info_fab_del);
        fabDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "삭제 버튼 클릭", Toast.LENGTH_SHORT).show();
            }
        });

        // 추가 플로팅 버튼 클릭
        fabEdit = findViewById(R.id.info_fab_edit);
        // final TextView plan_test = (TextView)findViewById(R.id.plan_test);
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(MainActivity.this);

                // customDialog.callFunction(plan_test);
            }
        });

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

        ArrayList<ListItem> DoList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            DoList.add(new ListItem("dd"));
        }

        // 할일 리스트
//        ListItemAdapter listItemAdapter = new ListItemAdapter(this, DoList);
//        doListView.setAdapter(listItemAdapter);

    }

    //fab 나오게하는 애니메이션
    private void toggleFab() {
        Log.d("TAG", "toggleFab: fab 애니메이션 실행");
        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (isFabOpen) {
            ObjectAnimator fd_animation = ObjectAnimator.ofFloat(fabDel, "translationY", 0f);
            fd_animation.start();
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fabEdit, "translationY", 0f);
            fe_animation.start();
            ObjectAnimator fm_animation = ObjectAnimator.ofFloat(fabMain, View.ROTATION, -85f, 0f);
            fm_animation.start();
        } else {
            // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator fd_animation = ObjectAnimator.ofFloat(fabDel, "translationY", -360f);
            fd_animation.start();
            ObjectAnimator fe_animation = ObjectAnimator.ofFloat(fabEdit, "translationY", -180f);
            fe_animation.start();
            ObjectAnimator fm_animation = ObjectAnimator.ofFloat(fabMain, View.ROTATION, 0f, -85f);
            fm_animation.start();
        }
        isFabOpen = !isFabOpen;
    }
}