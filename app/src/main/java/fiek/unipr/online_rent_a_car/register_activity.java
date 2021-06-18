package fiek.unipr.online_rent_a_car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register_activity extends AppCompatActivity {

    Button button_login,button_register;
    EditText username,lastname,email,num_tel,password;
    //Krijimi i nje objekti DBHelper
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText)findViewById(R.id.sgn_name);
        lastname = (EditText)findViewById(R.id.sgn_lastname);
        email = (EditText) findViewById(R.id.sgn_email);
        num_tel = (EditText) findViewById(R.id.sgn_number);
        password = (EditText) findViewById(R.id.sgn_psw);
        button_register = (Button) findViewById(R.id.btn_register);
        db = new DBHelper(this);

        button_login = (Button)findViewById(R.id.login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(register_activity.this,Login.class);
                startActivity(loginIntent);
            }
        });

        button_register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Vendosja e te gjitha te dhenave ne EditText tek register
                String emri = username.getText().toString();
                String mbiemri = lastname.getText().toString();
                String mail = email.getText().toString();
                String numri = num_tel.getText().toString();
                String psw = password.getText().toString();

                if(emri.equals("") || mbiemri.equals("") || mail.equals("") || numri.equals("") || psw.equals("")){
                    Toast.makeText(register_activity.this,"Duhet të plotësoni të gjitha fushat!",Toast.LENGTH_SHORT).show();
                } else {
                    Boolean check_user_result = db.check_user(emri);
                    if(check_user_result == false){
                          Boolean rez = db.insertData(emri,mbiemri,mail,numri,psw);
                          if(rez == true){
                              Toast.makeText(register_activity.this, "Regjistrimi përfundoj me sukses!", Toast.LENGTH_SHORT).show();
                          }else{
                              Toast.makeText(register_activity.this, "Regjistrimi dështoj!", Toast.LENGTH_SHORT).show();
                          }
                    }else{
                        Toast.makeText(register_activity.this, "Përdoruesi tashmë ekziston!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}