package fiek.unipr.online_rent_a_car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class register_activity extends AppCompatActivity {

    Button button_login,button_register;
    EditText username,lastname,email,num_tel,password;
    SharedPreferences sharedPreferences;
    //Krijimi i nje objekti DBHelper
    DBHelper db;

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
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        username = findViewById(R.id.sgn_name);
        lastname = findViewById(R.id.sgn_lastname);
        email = findViewById(R.id.sgn_email);
        num_tel = findViewById(R.id.sgn_number);
        password = findViewById(R.id.sgn_psw);

        //Duke vendos MODE_PRIVATE file behet i qasshem vetem duke perdor aplikacionin e thirrjes
        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        //Kur te hapet aktiviteti i ri ReigsterInfo se pari te kontrollojme nese te dhenat shared preferences jane ne rregull ose jo
        String name =sharedPreferences.getString(KEY_NAME,null);

        if(name != null){
            //Nese emri eshte ne rregull atehere thirre aktivitetin e ri RegisterInfo
            Intent intent = new Intent(register_activity.this,RegisterInfo.class);
            startActivity(intent);
        }

        button_register = findViewById(R.id.btn_register);
        db = new DBHelper(this);

        button_login = findViewById(R.id.login);
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


                if(!validateName() | !validateLastName() | !validateEmail() | !validatePhone() | !validatePassword()){

                    return;
                }
                else {

                    Boolean check_user_result = db.check_user(emri);
                    if(check_user_result == false){
                          Boolean rez = db.insertData(emri,mbiemri,mail,numri,psw);
                          if(rez == true){
                             //SharedPreferences for register!
                              SharedPreferences.Editor editor = sharedPreferences.edit();
                              editor.putString(KEY_NAME,emri);
                              editor.putString(KEY_LASTNAME,mbiemri);
                              editor.putString(KEY_EMAIL,mail);
                              editor.putString(KEY_NUMBER,numri);
                              editor.putString(KEY_PASSWORD,psw);
                              editor.apply();

                              Intent intent = new Intent(register_activity.this,RegisterInfo.class);
                              startActivity(intent);

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

    //Validimi apo trajtimi i gabimeve te Emrit
    private Boolean validateName(){
         String val_name = username.getText().toString();
         String noWhiteSpace = "(?=\\S+$)";

         if(val_name.isEmpty()){
             username.setError("Fusha Emri duhet plotësuar!");
             return false;  //Nese fusha emrit eshte empty
         }else if(val_name.length()>15){
             username.setError("Emri juaj është shumë i gjatë!");
             return false;
        }else if(val_name.matches(noWhiteSpace)){
             username.setError("Hapesirat nuk lejohen!");
             return false;
         }else{
             username.setError(null);
             return true;
         }
    }

    private Boolean validateLastName(){
        String val_name = lastname.getText().toString();
        String noWhiteSpace = "(?=\\S+$)";

        if(val_name.isEmpty()){
            lastname.setError("Fusha Mbiemri duhet plotësuar!");
            return false;  //Nese fusha emrit eshte empty
        }else if(val_name.length()>15){
            lastname.setError("Mbiemri juaj është shumë i gjatë!");
            return false;
        }else if(val_name.matches(noWhiteSpace)){
            lastname.setError("Hapesirat nuk lejohen!");
            return false;
        }else{
            lastname.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val_email = email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val_email.isEmpty()){
            email.setError("Fusha Email duhet plotësuar!");
            return false;
        }else if(!val_email.matches(emailPattern)){
            email.setError("Email Adresa juaj nuk është valide!");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }

    }

    private Boolean validatePhone(){
        String val_phone = num_tel.getText().toString();
        String number_match = "^[+][0-9]{10,13}$";

        if(val_phone.isEmpty()){
            num_tel.setError("Fusha telefonit duhet plotësuar!");
            return false;  //Nese fusha emrit eshte empty
        }else if(!val_phone.matches(number_match)){
            num_tel.setError("Kontrolloni numrin tuaj!");
            return false;
        }else{
            num_tel.setError(null);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val_psw = password.getText().toString();
        String password_val = "^"+
                "(?=.*[a-zA-Z])"+    //Shkronja te medha te vogla
                "(?=.*[@#$%^&+=])"+  //lejon vetem nje karakter
                "(?=\\S+$)"+   //no white spaces
                ".{4,}"+
                "$";

        if(val_psw.isEmpty()){
            password.setError("Fusha fjalëkalimi duhet plotësuar!");
            return false;  //Nese fusha emrit eshte empty
        }else if(val_psw.length()<=5){
             password.setError("Fjalëkalimi juaj është shumë i vogël!");
             return false;
        }else if(!val_psw.matches(password_val)){
            password.setError("Fjalëkalimi juaj është i dobet!");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }
}