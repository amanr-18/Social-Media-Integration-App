package com.example.socialmediaintegrationapp;









    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
    import android.view.View;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.facebook.CallbackManager;
        import com.facebook.FacebookCallback;
        import com.facebook.FacebookException;
        import com.facebook.FacebookSdk;
    import com.facebook.login.LoginManager;
    import com.facebook.login.LoginResult;
        import com.facebook.login.widget.LoginButton;
    import com.google.android.gms.auth.api.Auth;
    import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
    import com.google.android.gms.auth.api.signin.GoogleSignInResult;
    import com.google.android.gms.common.ConnectionResult;
    import com.google.android.gms.common.SignInButton;
    import com.google.android.gms.common.api.GoogleApiActivity;
    import com.google.android.gms.common.api.GoogleApiClient;

    import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    LoginManager loginManager;

    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    TextView textView;
    //we need an integer constant as my request code
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);
        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        setContentView(R.layout.activity_main);
        signInButton=(SignInButton)findViewById(R.id.sign_in_button);

        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton=(SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }

        });








        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText("User ID: " + loginResult.getAccessToken().getUserId() + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }


        });

    }

    ////////////// for google sign in
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
//for google
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);


            if(result.isSuccess()){
                //gotoProfile();
                Intent intent=new Intent(MainActivity.this,profile.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"Sign in cancel",Toast.LENGTH_LONG).show();
            }
        }


    }

//    private void gotoProfile() {
//
//    }
///for facebook
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode , resultCode , data);
//
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }
}









//    private void handleSignInResult(GoogleSignInResult result){
//
//
//
//
//
//
//
//
//
//
//
//
//
//}















//        loginButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v)
//                    {
//
//                                loginManager.logOut();
//
//        loginManager.logInWithReadPermissions(MainActivity.this, Arrays.asList(
//                "email",
//                "public_profile",
//                "user_birthday"))
//       \













