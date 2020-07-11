package com.outsource.subwaypeopledensity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConstraintLayout subwayViewer;
    private RecyclerViewAdapter adapter; // 커스텀 어댑터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }


    private void initView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        subwayViewer = (ConstraintLayout)findViewById(R.id.subwayViewer);
        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}