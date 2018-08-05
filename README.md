# AuthChallenge
The application consists of 4 activities and one singleton class

## Main Activity
Main Activity doesn't required signing in.
It contains two buttons to switch to Second and Third Activity.  
The Second and Third Activities require signing in.
There is a menu item for signing out.

## Second Activity
Second Activity requires signing in.
It contains two buttons to switch to Main and Third Activity.   
There is a menu item for signing out.
Checking for signing in occurs in onResume.
If user is not signed in, onResume directs the user to the signInActivity
The Singleton class SignInStateClass is used to keep track of the user state

## Third Activity
Third Activity requires signing in  
It contains two buttons to switch to Main and Second Activity   
There is a menu item for signing out 
Checking for signing in occurs in onResume
If user is not signed in, onResume directs the user to the signInActivity
The Singleton class SignInStateClass is used to keep track of the user state

## SignIn Activity
It has been chosen to collect the code required for signing in/out in a separate activity to avoid code repetition.  
Firebase Auth. state listener is used
If signing in succeeded, it returns to the former activity that initialized the signing in process.
It also changes the signing in state to true inside the Singleton class.
If signing in cancelled, it returns to the Main Activity which doesn't require signing in.
In case of signing out, it returns to the Main Activity which doesn't require signing in
It also changes the signing in state to false inside the Singleton class.

## SignIn State Class
The Singleton class SignInStateClass is used to keep track of the user state 
The state is initialized as false 
The state is only changed at signing in/out
