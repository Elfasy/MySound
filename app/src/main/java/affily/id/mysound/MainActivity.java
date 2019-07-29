package affily.id.mysound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnPlay;
    SoundPool soundPool;
    int soundId;
    boolean soundPoolLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundPoolLoaded){
                    soundPool.play(soundId,1,1,0,0,1);
                }
            }
        });
        soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .build();

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                if (i1 == 0){
                    soundPoolLoaded = true;
                }else{
                    Toast.makeText(MainActivity.this,"Gagal load",Toast.LENGTH_SHORT).show();
                }
            }
        });

        soundId = soundPool.load(this, R.raw.soundpool, 1);
    }
}
