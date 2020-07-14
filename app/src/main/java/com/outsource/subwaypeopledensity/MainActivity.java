package com.outsource.subwaypeopledensity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.outsource.subwaypeopledensity.model.Subway;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final int NEXT_STATION_TIME = 60; // 지하철 칸 몇초마다 이동?

    private static ConstraintLayout subwayViewer; // 레이아웃 상단 지하철 칸 표기하는 레이아웃
    private ListViewAdapter mAdapter; //리스트 어댑터
    private ListView listView; //아래 역 표시하는 리스트뷰
    private TextView nowTime; // 화면에서 @@초 남은 표시하는 텍스트뷰

    /**
     * 아래는 지하철 칸과 각 칸의 현재인원을 나타내는 텍스트뷰
     * */
    private static TextView box_1_people, box_2_people, box_3_people, box_4_people, box_5_people, box_6_people, box_7_people, box_8_people;
    private static LinearLayout box_1, box_2, box_3, box_4, box_5, box_6, box_7, box_8;


    private static Handler mHandler; //쓰레드와 메인 UI 연동을 위한 핸들러

    int stopWatch = NEXT_STATION_TIME; // 현재남은시간. 60초부터 시작.

    static int position =-1; // 지하철 현재위치. 처음엔 -1을 두어 아무거나 뜨는거 방지.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                stopWatch--;

                if(stopWatch <= 0){
                    stopWatch = NEXT_STATION_TIME;
                    mAdapter.setGotoNextStation();
                    mAdapter.modifySubwayDataForNextStation();
                    mAdapter.notifyDataSetChanged();
                }

                nowTime.setText(String.valueOf(stopWatch));
            }
        };

        Thread4Subway runnable = new Thread4Subway();
        Thread thread = new Thread(runnable);
        thread.start();

    }

    class Thread4Subway implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    mHandler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void viewSubwayViewer(Subway subway, int position) {

        if(MainActivity.position == -1)
            return;

        MainActivity.position = position;

        subwayViewer.setVisibility(View.VISIBLE);

        box_1_people.setText(String.valueOf(subway.getBox_1_people()));
        box_2_people.setText(String.valueOf(subway.getBox_2_people()));
        box_3_people.setText(String.valueOf(subway.getBox_3_people()));
        box_4_people.setText(String.valueOf(subway.getBox_4_people()));
        box_5_people.setText(String.valueOf(subway.getBox_5_people()));
        box_6_people.setText(String.valueOf(subway.getBox_6_people()));
        box_7_people.setText(String.valueOf(subway.getBox_7_people()));
        box_8_people.setText(String.valueOf(subway.getBox_8_people()));

        if (subway.getBox_1_people() <= 30)
            box_1.setBackgroundResource(R.drawable.roundbox_first_green);
        else if (subway.getBox_1_people() <= 70)
            box_1.setBackgroundResource(R.drawable.roundbox_first_yellow);
        else
            box_1.setBackgroundResource(R.drawable.roundbox_first_red);

        if (subway.getBox_8_people() <= 30)
            box_8.setBackgroundResource(R.drawable.roundbox_last_green);
        else if (subway.getBox_8_people() <= 70)
            box_8.setBackgroundResource(R.drawable.roundbox_last_yellow);
        else
            box_8.setBackgroundResource(R.drawable.roundbox_last_red);

        setBackground(subway.getBox_2_people(), box_2);
        setBackground(subway.getBox_3_people(), box_3);
        setBackground(subway.getBox_4_people(), box_4);
        setBackground(subway.getBox_5_people(), box_5);
        setBackground(subway.getBox_6_people(), box_6);
        setBackground(subway.getBox_7_people(), box_7);

    }

    private static void setBackground(int people, LinearLayout linearLayout) {
        if (people <= 30)
            linearLayout.setBackgroundResource(R.drawable.roundbox_center_green);
        else if (people <= 70)
            linearLayout.setBackgroundResource(R.drawable.roundbox_center_yellow);
        else
            linearLayout.setBackgroundResource(R.drawable.roundbox_center_red);
    }

    private void initView() {
        subwayViewer = (ConstraintLayout) findViewById(R.id.subwayViewer);

        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ListViewAdapter(this);
        listView.setAdapter(mAdapter);

        nowTime = (TextView)findViewById(R.id.stopWatch);

        box_1_people = (TextView) findViewById(R.id.box_1_people);
        box_2_people = (TextView) findViewById(R.id.box_2_people);
        box_3_people = (TextView) findViewById(R.id.box_3_people);
        box_4_people = (TextView) findViewById(R.id.box_4_people);
        box_5_people = (TextView) findViewById(R.id.box_5_people);
        box_6_people = (TextView) findViewById(R.id.box_6_people);
        box_7_people = (TextView) findViewById(R.id.box_7_people);
        box_8_people = (TextView) findViewById(R.id.box_8_people);

        box_1 = (LinearLayout) findViewById(R.id.box_1);
        box_2 = (LinearLayout) findViewById(R.id.box_2);
        box_3 = (LinearLayout) findViewById(R.id.box_3);
        box_4 = (LinearLayout) findViewById(R.id.box_4);
        box_5 = (LinearLayout) findViewById(R.id.box_5);
        box_6 = (LinearLayout) findViewById(R.id.box_6);
        box_7 = (LinearLayout) findViewById(R.id.box_7);
        box_8 = (LinearLayout) findViewById(R.id.box_8);

    }
}
