package fiek.unipr.online_rent_a_car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Variabla e cila percakton se pas disa sekondash te animacionit
    //kalon tek faqja tjeter. 4000 nenkupton 4 sekonda.

    private static int SPLASH_SCREEN = 4000;

    //Deklarimi variablave per animacione
    Animation top_anim, bottom_anim;
    //Vendosja e animacionit ne foton dhe tekstin ne faqe te pare
    ImageView foto;
    TextView logo, teksti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //Largimi i ActionBar
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
       // Perdorimi Animacioneve
        top_anim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Marrja id te elementeve tek activity_main.xml
        foto = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView3);
        teksti = findViewById(R.id.textView2);

        //Vendosja animacioneve te krijuara
        foto.setAnimation(top_anim);
        logo.setAnimation(bottom_anim);
        teksti.setAnimation(bottom_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}