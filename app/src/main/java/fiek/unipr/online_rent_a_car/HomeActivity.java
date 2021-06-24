package fiek.unipr.online_rent_a_car;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    //Per tek AlertDialog i mode NIGHT
    String select_options = "DEFAULT MODE";
    //Referenca tek activity_home per snackbars
    RelativeLayout home_activity;
    TextView textView;
    DBHelper db;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Online Rent A Car");

        //Qasja tek layout per Snackbar view
        home_activity = findViewById(R.id.home_activity);
        textView = findViewById(R.id.tv1);
        password = findViewById(R.id.password);
        db = new DBHelper(this);

        //DARK MODE , LIGHT MODE AND DEFAULT MODE
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark);
        }else{
            setTheme(R.style.Theme_Light);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.logut) {

            AlertDialog.Builder logout=new AlertDialog.Builder(this);
            logout.setTitle("Shëno passwordin llogarise tuaj: ");

            final EditText password = new EditText(this);
            password.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            logout.setView(password);

            logout.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String user = password.getText().toString().trim();

                    if(user.equals("")){
                        Toast.makeText(HomeActivity.this, "Jepni passwordin e lloagrise tuaj!", Toast.LENGTH_SHORT).show();
                    }else{
                        Boolean result = db.check_password(user);

                        if(result == true){
                            Intent intent =new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(HomeActivity.this, "Passwordi juaj është gabim!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
              logout.show();
       }else if(item.getItemId() == R.id.info){

         final AlertDialog.Builder info_builder = new AlertDialog.Builder(this);
           info_builder.setTitle("Informacion:")
                 .setMessage("Aplikacioni Rent A Car Online është një aplikacion që na ofron mundësi që në cdo kohë të" +
                         " informohemi ne lidhje me vetura me qera në tërë territorin e Kosoves!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Faleminderit!", Toast.LENGTH_LONG).show();
                    }
                })
                 .setNegativeButton(" ", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         //nothing
                     }
                 })
                 .setCancelable(false);

               AlertDialog dialog = info_builder.create();
               dialog.show();
       }else if(item.getItemId() == R.id.mode){

           String[] mode_choose = {"LIGHT","DARK","DEFAULT MODE"};
           AlertDialog.Builder builder = new AlertDialog.Builder(this);
           builder.setTitle("Zgjedhni modin e aplikacionit:");
           builder.setSingleChoiceItems(mode_choose, 0, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   select_options = mode_choose[which];
               }
           });
           builder.setPositiveButton("Choose", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {

                   //SnackBar
                   Snackbar.make(home_activity,"Ju zgjodhet: "+select_options,Snackbar.LENGTH_LONG)
                           .setAction("Close", new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {

                               }
                           }).setDuration(8000)
                           .show();

                   if(select_options == "DARK"){
                       AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                   }else{
                       AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                   }

               }
           });
           builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
               }
           });
           builder.show();

       }
           return super.onOptionsItemSelected(item);
    }
}