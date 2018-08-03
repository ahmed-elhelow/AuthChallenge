package com.example.android.authchallenge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class SignInActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    /*
     * It has been chosen to collect the code required for signing in/out
     * in a separate activity to avoid code repetition.
     * Firebase Auth. state listener is used
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(SignInActivity.this, "You're signed in.", Toast.LENGTH_SHORT).show();
                    SignInStateClass signInState = SignInStateClass.getInstance();
                    signInState.setSignedInState(true);
                    finish();
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    /*
     * If signing in succeeded, it returns to the former activity that initialized
     * the signing in process.
     * It also changes the signing in state to true inside the Singleton class.
     * If signing in cancelled, it returns to the Main Activity which doesn't require
     * signing in.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign-in succeeded, set up the UI
                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
                SignInStateClass signInState = SignInStateClass.getInstance();
                signInState.setSignedInState(true);
            } else if (resultCode == RESULT_CANCELED) {
                // Sign in was canceled by the user, finish the activity
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                Intent mainActivityIntent = new Intent(this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    /*
     * After signing out, it returns to the Main Activity which doesn't require
     * signing out
     * It also changes the signing in state to false inside the Singleton class.
     */
    public static void sign_out(Context context) {
        AuthUI.getInstance().signOut(context);
        Toast.makeText(context, "Signed out!", Toast.LENGTH_SHORT).show();
        SignInStateClass signInState = SignInStateClass.getInstance();
        signInState.setSignedInState(false);
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        context.startActivity(mainActivityIntent);
    }
}
