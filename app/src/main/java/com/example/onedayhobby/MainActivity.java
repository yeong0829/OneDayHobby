package com.example.onedayhobby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private boolean isFabOpen = false; // Fab 버튼 default 는 닫혀있음

    private FloatingActionButton fabMain;
    private FloatingActionButton fabDel;
    private FloatingActionButton fabEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabMain = findViewById(R.id.info_fab);
        fabDel = findViewById(R.id.info_fab_del);
        fabEdit = findViewById(R.id.info_fab_edit);

        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.hobby_list);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();

        // 메인플로팅 버튼 클릭
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFab();
            }
        });
        // 삭제 플로팅 버튼 클릭
        fabDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "삭제 버튼 클릭", Toast.LENGTH_SHORT).show();
            }
        });

        // 수정 플로팅 버튼 클릭
        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "수정 버튼 클릭", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dataSetting(){
        HobbyAdapter mMyAdapter = new HobbyAdapter();

        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_launcher_foreground), "name_" + i, "contents_" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
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