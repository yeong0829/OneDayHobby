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
        ArrayList<HobbyItem> hobbyItems = new ArrayList<>();

        hobbyItems.add(new HobbyItem(R.drawable.hobby01, "식물 키우기", "정신건강에 좋은 식물 키우기",
                        "준비물: 모종삽, 분무기, 화분 받침대, 원예용 가위 등\n" +
                                        "장점\n" +
                                        "- 정신건장에 좋습니다.\n" +
                                        "- 공기를 정화합니다.\n" +
                                        "- 실내의 습도 조절이 됩니다.\n" +
                                        "- 인테리어 효과를 줄 수 있습니다.\n" +
                                        "방법: 키우는 식물의 특성에 따라 햇빛, 물, 온도, 환기 등을 잘 조절해주어야 합니다. \n" +
                                        "주의사항: 자신한테 맞는 식물을 골라야 하며, 빛, 온도, 물 주기, 환기가 매우 중요합니다. "));
        hobbyItems.add(new HobbyItem(R.drawable.hobby02, "캠핑", "함께 힐링하는 캠핑",
                        "준비물: 텐트, 테이블, 의자, 차량용냉장고, 조리기구, 화로대, 랜턴, 침낭, 버너\n" +
                                        "장점\n" +
                                        "- 사람들과 함께 할 수 있습니다.\n" +
                                        "- 자연을 체험할 수 있습니다.\n" +
                                        "- 힐링을 할수 있습니다.\n" +
                                        "방법: 계획을 세우며 캠핑장을 선택하고 예약하며 필요물품을 준비합니다\n" +
                                        "주의사항: 벌레 조심, 화재사고 조심, 체온 조절 필수"));
        hobbyItems.add(new HobbyItem(R.drawable.hobby03, "걷기 운동", "가장 쉽고 건강한 운동",
                        "준비물: 운동화, 모자, 손수건, 선크림 등\n" +
                                        "장점\n" +
                                        "- 신체 회복시간을 줄일 수 있습니다.\n" +
                                        "- 심장병 예방 효과가 있습니다.\n" +
                                        "- 노화로 인한 기억장애 개선에 도움을 줍니다.\n" +
                                        "- 스트레스 해소 또는 숙면에 도움을 줍니다.\n" +
                                        "방법: 평소 속도보다 빠르게 걸으면서 적당한 속도를 유지합니다. 팔을 흔들면 상체 운동에도 도움이 됩니다. \n" +
                                        "주의사항: 준비운동은 필수이며 편한 신발과 바른 자세가 중요합니다."));
        hobbyItems.add(new HobbyItem(R.drawable.hobby04, "등산", "자연을 즐길 수 있는 등산",
                        "준비물: 등산화, 등산복, 모자, 배낭, 스틱, 물, 간식, 장갑, 발목양말, 개인물품\n" +
                                        "장점\n" +
                                        "- 심폐 기능 향상 및 골밀도, 체력, 근지구력 향상에 도움을 줍니다. \n" +
                                        "- 작은 성취감으로 큰 만족감을 느낄 수 있습니다.\n" +
                                        "- 다양한 사람들을 만나볼 수 있습니다.\n" +
                                        "- 각종 질병이 예방됩니다.\n" +
                                        "- 정신건강에 좋습니다.\n" +
                                        "방법: 집 근처 가까운 산을 시작으로 규칙적을 산행하며 첨차 거리와 시간과 속도를 올려갑니다.\n" +
                                        "주의사항: 당뇨질환자나고혈압이나, 호흡기 질환자 등은 상행을 해서는 안됩니다. 또한 산속에서 위험상황시 대처 방법을 알고 있어야 합니다. "));
        hobbyItems.add(new HobbyItem(R.drawable.hobby05, "십자수", "집중력 향상에 좋은 십자수",
                        "준비물: 실, 스티커, 십자수 바늘, 도안, 천, 가위, 자수틀 등\n" +
                                        "장점: \n" +
                                        "- 배우기 쉽습니다.\n" +
                                        "- 보관 및 관리가 편리합니다.\n" +
                                        "- 성취감을 느낄수 있습니다.\n" +
                                        "- 집중력을 높일 수 있습니다.\n" +
                                        "- 장소제약이 없습니다.\n" +
                                        "방법: 사용설명서가 있으며 왼쪽에서 오른쪽으로 수를 놓으며 다양한 방법으로 수를 놓을 수 있습니다. \n" +
                                        "주의사항: 실의 길이를 잘 조절해야 하며 수를 한 방향으로 놓아야 합니다. "));
        hobbyItems.add(new HobbyItem(R.drawable.hobby06, "레진 공예", "인지 발달을 유발하는 레진 공예",
                        "준비물(기본): 레진, 몰드, 핀셋, 이쑤시개, 악세사리부자재, 투명필름\n" +
                                        "장점\n" +
                                        "- 초보자도 쉽게 만들 수 있습니다.\n" +
                                        "- 스트레스와 관련된 질환의 영향을 중화시킵니다.\n" +
                                        "- 뇌의 신경망에 자극을 주어 깊게 집중할수 있도록 도웁니다. \n" +
                                        "- 우울증 중상을 줄입니다.\n" +
                                        "- 창의력을 키웁니다.\n" +
                                        "- 긴장을 완화시키는데 도움을 줍니다.\n" +
                                        "- 인지 발달을 유발합니다.\n" +
                                        "- 만든 공예품을 판매할 수도 있습니다.\n" +
                                        "방법: 만드는 모양에 따라 방법이 달라지며 많은 재료를 사용해서 나만의 레진공예를 할수 있습니다.\n" +
                                        "주의사항: 레진의 양에 따라 경화시간을 잘 맞춰야 합니다. "));
        hobbyItems.add(new HobbyItem(R.drawable.hobby07, "축구", "건강하고 재미있게 운동하기",
                        "준비물: 축구화, 축구공, 보호대, 유니폼\n" +
                                        "장점\n" +
                                        "- 하체 근력을 강화해줍니다.\n" +
                                        "- 심폐지구력 능력 향상에 도움을 줍니다.\n" +
                                        "- 신경발달에 도움을 줍니다.\n" +
                                        "- 사교성과 협동심을 기를 수 있습니다.\n" +
                                        "- 다이어트에 도움이 됩니다.\n" +
                                        "- 체력을 길를 수 있습니다.\n" +
                                        "방법: 축구 규칙에 대해 알아야 됩니다. 팀과 함께 수비 또는 공격을 하면서 전반전, 후반전으로 각 45분, 총 90분의 경기를 하며 점수를 더 많이 얻은 팀이 승리하게 됩니다.  \n" +
                                        "주의사항: 부상이 발생할 수 있으므로 충분한 준비운동은 필수입니다. "));
        hobbyItems.add(new HobbyItem(R.drawable.hobby08, "퍼즐", "집중력과 두뇌발달에 좋은 퍼즐",
                        "준비물: 퍼즐\n" +
                                        "장점\n" +
                                        "- 집중력을 기를 수 있습니다.\n" +
                                        "- 성취감을 느낄 수 있습니다.\n" +
                                        "- 손 조작 능력 및 뇌 기능을 높일 수 있습니다.\n" +
                                        "- 창의력을 기르는데 도움을 줍니다. \n" +
                                        "- 두뇌 발달에 도움이 됩니다. \n" +
                                        "방법: 그림이 그려져 있는 여러 개의 퍼즐 조각을 맞물리는 홈대로 끼워서 맞춥니다. \n" +
                                        "주의사항: 조각 수가 많을수록 난이도가 높고 시간도 오래 걸리기 때문에 자신에게 맞는 난이도를 선택해야 합니다. "));
        hobbyItems.add(new HobbyItem(R.drawable.hobby09, "요가", "몸을 유연하게 해주는 요가",
                        "준비물: 요가 매트, 요가복\n" +
                                        "장점\n" +
                                        "- 소화력을 증진시킵니다.\n" +
                                        "- 균형감각을 기르는데 도움을 줍니다.\n" +
                                        "- 집중력을 향상시킵니다.\n" +
                                        "- 몸을 유연하게 합니다.\n" +
                                        "- 스트레스를 줄이는데 도움이 됩니다.\n" +
                                        "- 혈액순환이 증진됩니다.\n" +
                                        "- 인내심 향상에 도우을 줍니다.\n" +
                                        "방법: 동작에 따라 하는 법이 다릅니다. \n" +
                                        "주의사항: 어려운 동작은 반복하여 쉽게 자세를 유지할수 있도록 해야 하며 갈증을 느낄수 있으니 충분히 물을 마셔야 합니다. "));
        hobbyItems.add(new HobbyItem(R.drawable.hobby10, "여행", "새로운 문화를 접해보는 여행",
                        "준비물: 여권, 현지 화페, 비상약, 그 밖에 개인용품\n" +
                                        "장점\n" +
                                        "- 새로운 문화를 접할 수 있습니다\n" +
                                        "- 자신감을 얻을 수 있습니다.\n" +
                                        "- 다양한 국적의 사람들을 만나볼 수 잇습니다.\n" +
                                        "- 지식을 쌓을 수 있습니다.\n" +
                                        "- 모험심을 기를 수 있습니다.\n" +
                                        "- 문제해결 능력을 기를 수 있습니다.\n" +
                                        "- 시야를 넗히는데 도움을 줍니다.\n" +
                                        "방법: 자신만의 테마를 찾고 기록을 남기면 즐거운 여행을 할수 있습니다. \n" +
                                        "주의사항: 위험한 장소나 비상상황에 대처법을 숙지해야되며 소지품 관리에 주의해야 합니다.  "));

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
                Log.i("TAG", "onItemClick: icon"+hobbyItems.get(position).icon);
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