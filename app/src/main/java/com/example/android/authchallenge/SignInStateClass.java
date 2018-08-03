package com.example.android.authchallenge;

/*
 * The Singleton class SignInStateClass is used to keep track of the user state
 * The state is initialized as false
 * The state is only changed at signing in/out
 */
public class SignInStateClass {
    private static SignInStateClass ourInstance = new SignInStateClass();
    private static boolean signedInState = false;

    public static SignInStateClass getInstance() {
        return ourInstance;
    }

    private SignInStateClass() {}

    protected void setSignedInState(boolean newSignedInState){
        signedInState = newSignedInState;
    }

    protected boolean getSignedInState(){
        return signedInState;
    }
}
