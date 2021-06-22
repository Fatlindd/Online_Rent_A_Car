package fiek.unipr.online_rent_a_car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterInfo extends AppCompatActivity {

    Button button_login;
    TextView textView_username,textView_lastname,textView_email,textView_num_tel,textView_password;

    SharedPreferences sharedPreferences;

    //Brenda klases ne kemi krijuar objektet e SharedPreferences dhe i thrrasim tek metoda getSharedPreferences()
    private static final String SHARED_PREF_NAME ="mypref";
    private static final String KEY_NAME = "username";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_info);
        getSupportActionBar().hide();

        textView_username = findViewById(R.id.name);
        textView_lastname = findViewById(R.id.last_name);
        textView_email = findViewById(R.id.email);
        textView_num_tel = findViewById(R.id.number);
        textView_password = findViewById(R.id.fjalkalimi);
        button_login = findViewById(R.id.login_register);

        //Po ashtu ketu duhet te thirret ne kete aktivitet getSharedPreference() metoden per ti bere fetch te dhenat..
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String username = sharedPreferences.getString(KEY_NAME,null);
        String lastname = sharedPreferences.getString(KEY_LASTNAME,null);
        String email = sharedPreferences.getString(KEY_EMAIL,null);
        String number = sharedPreferences.getString(KEY_NUMBER,null);
        String password = sharedPreferences.getString(KEY_PASSWORD,null);

        if(username != null || lastname != null || email != null || number != null || password != null){

            //Vendosen te dhenat ne TextView tek activity_register_info
            textView_username.setText("Emri: "+username);
            textView_lastname.setText("Mbiemri: "+lastname);
            textView_email.setText("Email: "+email);
            textView_num_tel.setText("Nr.Telefonit"+number);
            textView_password.setText("Fjalëkalimi: "+password);
        }
        //Butoni qe na dergon tek Login ne aplikacion
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(RegisterInfo.this,Login.class);
                startActivity(intent);
                Toast.makeText(RegisterInfo.this, "Tani mund të qaseni në App!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
