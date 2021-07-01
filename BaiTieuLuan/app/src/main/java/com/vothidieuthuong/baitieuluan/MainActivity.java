package com.vothidieuthuong.baitieuluan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 ListView listcasi;
 ArrayList<list_casi> list_casi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listcasi=(ListView) findViewById(R.id.list_casi);
        list_casi= new ArrayList<list_casi>();

        list_casi.add(new list_casi("Khởi My", R.drawable.my));
        list_casi.add(new list_casi("Thủy Tiên", R.drawable.tien));
        list_casi.add(new list_casi("Noo Phước Thịnh", R.drawable.thinh));
        list_casi.add(new list_casi("Hòa Minzy", R.drawable.hoa));
        list_casi.add(new list_casi("Sơn Tùng", R.drawable.tung));
        list_casi.add(new list_casi("Jack", R.drawable.meo));
        Adapter adaptercs= new Adapter(MainActivity.this,R.layout.list_ca_si,list_casi);
        listcasi.setAdapter(adaptercs);
        listcasi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this, ActivityBaihat.class);
                intent.putExtra("position", position);
                intent.putExtra("tencasi", list_casi.get(position).tencasi);
                startActivity(intent);

            }
        });
    }
}