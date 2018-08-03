package com.example.android.authchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    /*
     * Main Activity doesn't required signing in
     * It contains two buttons to switch to Second and Third Activity
     * The Second and Third Activities require signing in
     * There is a menu item for signing out
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButtonToSecond, mButtonToThird;

        mButtonToSecond = (Button) findViewById(R.id.activity2_Button);
        mButtonToThird = (Button) findViewById(R.id.activity3_Button);

        mButtonToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondActivityIntent = new Intent(view.getContext(), SecondActivity.class);
                startActivity(secondActivityIntent);
            }
        });

        mButtonToThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent thirdActivityIntent = new Intent(view.getContext(), ThirdActivity.class);
                startActivity(thirdActivityIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                SignInActivity.sign_out(this);
                Toast.makeText(this, "Signed out!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
