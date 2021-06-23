package fiek.unipr.online_rent_a_car;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    String select_options = "Light";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Online Rent A Car");

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
           Toast.makeText(HomeActivity.this, "Butoni LogOut u klikua", Toast.LENGTH_LONG).show();
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
                   Toast.makeText(HomeActivity.this, "Ju zgjodhet: "+select_options, Toast.LENGTH_SHORT).show();
               }
           });
           builder.setPositiveButton("Choose", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.dismiss();
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