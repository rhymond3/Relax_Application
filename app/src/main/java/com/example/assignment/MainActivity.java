package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText username, password;
    Button login;
    TextView textViewUsername, textViewPassword;
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private String sharedPrefile = "login";
    private SharedPreferences prefs;

    static final Integer LOCATION = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        username = (EditText)findViewById(R.id.Username);
        password = (EditText)findViewById(R.id.Password);

        textViewUsername = (TextView)findViewById(R.id.textView1);
        textViewPassword = (TextView)findViewById(R.id.textView2);

        login = (Button)findViewById(R.id.login);

        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION);

        prefs = getSharedPreferences(sharedPrefile,MODE_PRIVATE);
        if(prefs.getString(USERNAME,null)!=null && prefs.getString(PASSWORD,null)!=null){
            Intent i = new Intent(MainActivity.this, HomeActivity.class );
            startActivity(i);
        }
    }

    public void RegisterPage(View view){
        Intent i = new Intent(MainActivity.this, Register.class );
        startActivity(i);
    }

    public void LoginAccount(View view){
        String UserName = username.getText().toString();
        String Password = password.getText().toString();
        SharedPreferences.Editor preferenceEditor = prefs.edit();
        if(validate()){
            User currentUser = myDb.Authenticate(new User(null, UserName, null, Password));
            if (currentUser != null) {
                preferenceEditor.putString(USERNAME,UserName);
                preferenceEditor.putString(PASSWORD,Password);
                preferenceEditor.apply();
                Toast.makeText(getApplicationContext(),"Succesfuul Login",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
            else
                Toast.makeText(getApplicationContext(),"Fail to Login, Please try again",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validate() {
        boolean valid = true;

        String UserName = username.getText().toString();
        String Password = password.getText().toString();

        if (UserName.isEmpty()) {
            textViewUsername.setText("*Please enter valid username!");
            valid = false;
        }
        else{
            textViewUsername.setText("");
        }

        if (Password.isEmpty()) {
            textViewPassword.setText("*Please enter password!");
            valid = false;
        }
        else{
            textViewPassword.setText("");
        }
        return valid;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
            }
        }
    }
}