package com.outsource.subwaypeopledensity;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.outsource.subwaypeopledensity.model.Subway;
import com.outsource.subwaypeopledensity.model.SubwayStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> stationLists = new ArrayList<>(); //지하철역 리스트
    int p[]={-1,-1,-1,-1,-1};
    List<Subway> subways = new ArrayList<>();



    public ListViewAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < SubwayStation.daejeonStations.length; i++) {
            stationLists.add(SubwayStation.daejeonStations[i]);
        }

        setRandomNumber();
        setSubwayData();

    }

    public void setGotoNextStation(){
        for(int i=0; i<5; i++){
            p[i]++;
        }
    }

    public void modifySubwayDataForNextStation(){
        Random rnd = new Random();
        for(int i=0; i<5; i++){
            Subway subway = new Subway(rnd.nextInt(100),rnd.nextInt(100),rnd.nextInt(100),rnd.nextInt(100)
                    ,rnd.nextInt(100),rnd.nextInt(100),rnd.nextInt(100),rnd.nextInt(100));
            subways.set(i, subway);
        }

        if(MainActivity.position != -1)
            MainActivity.viewSubwayViewer(subways.get(MainActivity.position), MainActivity.position);
    }


    public void setSubwayData() {
        Random rnd = new Random();
        for(int i=0; i<5; i++){
            Subway subway = new Subway(rnd.nextInt(100),rnd.nextInt(100),rnd.nextInt(100),rnd.nextInt(100)
                    ,rnd.nextInt(100),rnd.nextInt(100),rnd.nextInt(100),rnd.nextInt(100));
            subways.add(subway);
        }
    }

    public void setRandomNumber() {
        Random rnd = new Random();

        for(int i=0; i<5; i++){
            p[i] = rnd.nextInt(22);
            for(int k=0; k<i; k++){
                if(p[k] == p[i]){
                    i--;
                    break;
                }
            }
        }
    }


    @Override
    public int getCount() {
        return stationLists.size();
    }

    @Override
    public Object getItem(int position) {
        return stationLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        /* 'listview_custom' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView stationName = convertView.findViewById(R.id.subwayStationName);
        ImageView subwayImage = convertView.findViewById(R.id.subwayButtonImage);

        stationName.setText(stationLists.get(position));


        int num = -1;

        if (position == p[0]) {
            num = 0;
            subwayImage.setVisibility(View.VISIBLE);
        }
        else if (position == p[1]) {
            num = 1;
            subwayImage.setVisibility(View.VISIBLE);
        }
        else if (position == p[2]) {
            num = 2;
            subwayImage.setVisibility(View.VISIBLE);
        }
        else if (position == p[3]) {
            num = 3;
            subwayImage.setVisibility(View.VISIBLE);
        }
        else if (position == p[4]) {
            num = 4;
            subwayImage.setVisibility(View.VISIBLE);
        }
        else
            subwayImage.setVisibility(View.INVISIBLE);


        final int finalNum = num;
        subwayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.position = finalNum;
                if(finalNum != -1)
                    MainActivity.viewSubwayViewer(subways.get(finalNum), finalNum);
            }
        });

        return convertView;
    }

}
