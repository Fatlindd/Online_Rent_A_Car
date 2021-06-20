package fiek.unipr.online_rent_a_car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Login extends AppCompatActivity {

    TextInputLayout textInputEmri;
    TextInputLayout textInputPass;
    Button button_register,button_login,button_forget;

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        textInputEmri = (TextInputLayout)findViewById(R.id.username);
        textInputPass = (TextInputLayout)findViewById(R.id.password);
        button_register = (Button)findViewById(R.id.register);
        button_login = (Button)findViewById(R.id.btn_login);
        button_forget = (Button)findViewById(R.id.btn_password);

        db =new DBHelper(this);

        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String user = Objects.requireNonNull(textInputEmri.getEditText()).getText().toString().trim();
                String pass = Objects.requireNonNull(textInputPass.getEditText()).getText().toString().trim();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(Login.this, "Plotësoni Username dhe Passwordin tuaj!", Toast.LENGTH_SHORT).show();
                }
                else{

                Boolean result = db.check_username_password(user,pass);

                if(result == true){
                       Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                       startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this, "Të dhenat janë gabim!", Toast.LENGTH_SHORT).show();
                }
                }
            }
        });

    button_register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent registerIntent=new Intent(Login.this,register_activity.class);
            startActivity(registerIntent);
        }
    });


    button_forget.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    });
    }
}