package com.vothidieuthuong.baitieuluan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ActivityBaihat extends AppCompatActivity {
 TextView txttencs, txtstart, txtend,txtdagphat;
 ImageButton imgplay, imgnext, imgback;
 ListView listViewbh;
 SeekBar seekBar;
 ArrayList<list_baihat> bh;
 ArrayList<list_casi> cs;
 MediaPlayer Player= new MediaPlayer();
 int vitriphat;

 @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baihat);
        loadView();
        Intent intent= getIntent();
        int vt=intent.getIntExtra("position",0);
         String ten=intent.getStringExtra("tencasi");
         txttencs.setText("Ca sĩ : "+ ten);

         bh=new ArrayList<list_baihat>();
         switch (vt){
             case 0:
                 bh.add(new list_baihat("Ai Ai Ai", time(R.raw.my1),R.raw.my1));
                 bh.add(new list_baihat("Buông tay",time(R.raw.my2),R.raw.my2));
                 break;
             case 1:
                 bh.add(new list_baihat("Mãi thuộc về anh",time(R.raw.tien1),R.raw.tien1));
                 bh.add(new list_baihat("Sao anh không ăn",time(R.raw.tien2),R.raw.tien2));
                 break;
             case 2:
                 bh.add(new list_baihat("Những kẻ mộng mơ",time(R.raw.thinh1),R.raw.thinh1));
                 bh.add(new list_baihat("Em đã thương người ta hơn anh",time(R.raw.thinh2),R.raw.thinh2));
                 bh.add(new list_baihat("Yêu một người sao buồn đến thế",time(R.raw.thinh3),R.raw.thinh3));
                 break;
             case 3:
                 bh.add(new list_baihat("Thư chưa gởi anh",time(R.raw.hoa1),R.raw.hoa1));
                 bh.add(new list_baihat("Điều buồn nhất khi yêu",time(R.raw.hoa2),R.raw.hoa2));
                 break;
             case 4:
                 bh.add(new list_baihat("Muộn rồi mà sao còn",time(R.raw.tung1),R.raw.tung1));
                 bh.add(new list_baihat("Gửi người yêu cũ",time(R.raw.tung2),R.raw.tung2));
                 break;
             case 5:
                 bh.add(new list_baihat("Laylalay",time(R.raw.meo1),R.raw.meo1));
                 break;
         }
         Adapter_baihat adapter_baihat= new Adapter_baihat(ActivityBaihat.this, R.layout.list_bai_hat,bh);
        listViewbh.setAdapter(adapter_baihat);

        tg_ht();

     listViewbh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          if(Player.isPlaying()){
              Player.stop();
          }else{
              vitriphat=position;
             khoitao();
         }
          imgplay.setBackgroundResource(R.drawable.dung1);
         }

     });
     imgplay.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(Player.isPlaying()){
                 Player.pause();
                 imgplay.setBackgroundResource(R.drawable.dung1);
             }else{
                 Player.start();
                 imgplay.setBackgroundResource(R.drawable.dung);

             }
         }
     });
     imgnext.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             vitriphat++;
             if(vitriphat>(bh.size()-1)){
                 vitriphat=0;
             }

           Player.stop();
           khoitao();
         }
     });
     imgback.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             vitriphat--;
             if (vitriphat<0){
                 vitriphat=bh.size()-1;
             }

             Player.stop();
             khoitao();
         }
     });
     seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {
             Player.seekTo(seekBar.getProgress());
         }
     });
         }
         void tg_ht(){
             Handler handler= new Handler();
             Boolean b=handler.postDelayed(new Runnable() {
                 @Override
                 public void run() {
                      SimpleDateFormat simpleDateFormat= new SimpleDateFormat("mm:ss");
                      txtstart.setText(simpleDateFormat.format(Player.getCurrentPosition()));
                      seekBar.setProgress(Player.getCurrentPosition());
                      Player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                          @Override
                          public void onCompletion(MediaPlayer mp) {
                              vitriphat++;
                              if(vitriphat>bh.size()-1){
                                  Player.stop();
                              } else {
                                  Player.stop();
                                  khoitao();
                                  Player.start();
                              }
                          }
                      });
                      handler.postDelayed(this,500);
                 }
             }, 100);

         }

    private void khoitao() {
        Player= MediaPlayer.create(ActivityBaihat.this,bh.get(vitriphat).baihat);
        txtdagphat.setText("Đang phát : "+bh.get(vitriphat).tenbaihat);
        txtend.setText(time(bh.get(vitriphat).baihat));
       seekBar.setMax(Player.getDuration());
        Player.start();
    }


    private String time(int baihat){
        String t;
        MediaPlayer Player=new MediaPlayer();
        Player=MediaPlayer.create(ActivityBaihat.this,baihat);
       SimpleDateFormat tg = new SimpleDateFormat("mm:ss");
        t=tg.format(Player.getDuration());
        return t;
    }

    private void loadView() {
        txttencs=findViewById(R.id.tencasi);
        txtstart=findViewById(R.id.timeplay);
        txtdagphat=findViewById(R.id.dagphat);
        txtend=findViewById(R.id.timeout);
        imgplay=findViewById(R.id.stop);
        imgback=findViewById(R.id.lui);
        imgnext=findViewById(R.id.next);
        listViewbh=findViewById(R.id.nhac);
        seekBar=findViewById(R.id.seebar);


    }

    @Override
    protected void onStop() {
     Player.stop();
        super.onStop();
    }
}