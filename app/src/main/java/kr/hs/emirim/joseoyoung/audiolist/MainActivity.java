package kr.hs.emirim.joseoyoung.audiolist;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Button butPlay, butPause, butStop;
    ProgressBar progress;
    TextView textMusic;
    String[] musics={"NEVER","Please Open","Oh Little girl","Boys like girls","I Need you","Draft Punk",};
    int[] musicResIds={R.raw.never, R.raw.please_open,R.raw.oh_little_girl, R.raw.boyslikegirls,R.raw.ineedyou,R.raw.pentatonix};
    int selectedMusiId;
    MediaPlayer mediaPlayer;
    boolean selectedPauseButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView)findViewById(R.id.list_music);
        butPlay=(Button)findViewById(R.id.but_play);
        butPause=(Button)findViewById(R.id.but_pause);
        butStop=(Button)findViewById(R.id.but_stop);
        textMusic=(TextView)findViewById(R.id.text_music);
        progress=(ProgressBar)findViewById(R.id.progress_music);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, musics);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list.setItemChecked(0, true);
        selectedMusiId=musicResIds[0];
        mediaPlayer=MediaPlayer.create(this, selectedMusiId);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPauseButton=false;
                mediaPlayer.stop();
                selectedMusiId=musicResIds[i];
                progress.setVisibility(View.INVISIBLE);
            }
        });

        butPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedPauseButton){
                    selectedPauseButton=false;
                }else
                    mediaPlayer=MediaPlayer.create(MainActivity.this, selectedMusiId);
                mediaPlayer.start();
                progress.setVisibility(View.VISIBLE);
            }
        });

        butPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPauseButton=true;
                mediaPlayer.pause();
                progress.setVisibility(View.INVISIBLE);
            }
        });

        butStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                progress.setVisibility(View.INVISIBLE);
            }
        });
    }
    protected void onStop(){
        super.onStop();
        mediaPlayer.stop();
    }
}
