package com.outsource.subwaypeopledensity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConstraintLayout subwayViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        subwayViewer = (ConstraintLayout)findViewById(R.id.subwayViewer);
    }
}