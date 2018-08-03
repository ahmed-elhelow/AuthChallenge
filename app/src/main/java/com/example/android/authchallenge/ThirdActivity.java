package com.example.android.authchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    /*
     * Third Activity requires signing in
     * It contains two buttons to switch to Main and Second Activity
     * There is a menu item for signing out
     * Checking for signing in occurs in onResume
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button mButtonToMain, mButtonToSecond;

        mButtonToMain = (Button) findViewById(R.id.main_activity_Button);
        mButtonToSecond = (Button) findViewById(R.id.activity2_Button);


        mButtonToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(mainActivityIntent);
            }
        });

        mButtonToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secondActivityIntent = new Intent(view.getContext(), SecondActivity.class);
                startActivity(secondActivityIntent);
            }
        });
    }

    /*
     * If user is not signed in, onResume directs the user to the signInActivity
     * The Singleton class SignInStateClass is used to keep track of the user state
     */
    @Override
    protected void onResume() {
        super.onResume();
        SignInStateClass signInState = SignInStateClass.getInstance();
        if(!signInState.getSignedInState()) {
            Intent signInActivityIntent = new Intent(this, SignInActivity.class);
            startActivity(signInActivityIntent);
        }
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
