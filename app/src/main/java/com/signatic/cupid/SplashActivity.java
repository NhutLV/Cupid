package com.signatic.cupid;

import android.content.Intent;
import android.os.Bundle;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.signatic.model.User;
import com.signatic.service.services.UserServiceImpl;
import com.signatic.utils.ToolsActivity;
import com.signatic.utils.UserLogin;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by DefaultAccount on 9/6/2016.
 */
public class SplashActivity extends AppCompatActivity {
    private Button mCreateAccount, mLogin;
    private LoginButton mLoginButton;
    private CallbackManager mCallbackManager = null;
    private SessionManager mSessionManager;
    private static final String TAG = SplashActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.splash_layout);
        //region Properties Button
        mCreateAccount = (Button) findViewById(R.id.btn_create);
        mLogin = (Button) findViewById(R.id.btn_login);
        mLoginButton = (LoginButton) findViewById(R.id.btn_login_facebook);
        mLoginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
        mSessionManager=new SessionManager(getApplicationContext());
        loginWithToken();

        //endregion

        getevent();

//        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
//            private ProfileTracker mProfileTracker;
//            UserServiceImpl userService = new UserServiceImpl(getApplicationContext());
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                final Intent i = new Intent(SplashActivity.this, IntroductionActivity.class);
//                if (Profile.getCurrentProfile() == null) {
//                    mProfileTracker = new ProfileTracker() {
//                        @Override
//                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
//                            // profile2 is the new profile
//                            mProfileTracker.stopTracking();
//                            mLoginButton.setReadPermissions(Arrays.asList("email"));
//                            User user = new User(profile2.getName(),profile2.getFirstName(), profile2.getLastName(), "", profile2.getId(),profile2.getProfilePictureUri(150,150).toString());
//                            userService.registerFb(user);
//                            startActivity(i);
//                            overridePendingTransition(R.anim.fadein,R.anim.fadeout);
//                        }
//                    };
//                    // no need to call startTracking() on mProfileTracker
//                    // because it is called by its constructor, internally.
//                } else {
//                    Profile profile = Profile.getCurrentProfile();
//                    User user = new User(profile.getName(),profile.getFirstName(), profile.getLastName(), "", profile.getId(),profile.getProfilePictureUri(150,150).toString());
//                    userService.registerFb(user);
//                    startActivity(i);
//                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);
//                }
//
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                if(!ToolsActivity.isNetworkOnline(getApplicationContext())){
//                    Toast.makeText(getApplicationContext(),"Please connect network",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ToolsActivity.isNetworkOnline(getApplicationContext())){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.check_connect),Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                        Intent i = new Intent(SplashActivity.this, IntroductionActivity.class);
                        UserServiceImpl userService = new UserServiceImpl(getApplicationContext());

                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            // App code
                            GraphRequest request = GraphRequest.newMeRequest(
                                    loginResult.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(JSONObject object, GraphResponse response) {
                                            Log.v("LoginActivity", response.toString());

                                            // Application code
                                            String email = object.optString("email");
//                                			String birthday = object.optString("birthday"); // 01/31/1980 format
                                            String name = object.optString("name");
                                            String first_name = object.optString("first_name");
                                            String last_name = object.optString("last_name");
                                            String id = object.optString("id");

                                            User user = new User(name,first_name, last_name, email, id,"");
                                            userService.registerFb(user);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                                        }
                                    });
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "id,name,email,gender,birthday,first_name,last_name");
                            request.setParameters(parameters);
                            request.executeAsync();

                        }

                        @Override
                        public void onCancel() {
                            // App code
                            Log.v(TAG, "cancel");
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                            Log.v(TAG, exception.getCause().toString());
                        }
                    });
                }
            }
        });






        /*receive message and send message*/

        /*end receive message and send message*/

    }

    public void getevent() {
        //region event for button create account
        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ToolsActivity.isNetworkOnline(getApplicationContext())){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.check_connect),Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(SplashActivity.this, SignUpActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                // finish();
            }
        });

        //endregion event for button create account

        //region event for button login
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ToolsActivity.isNetworkOnline(getApplicationContext())){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.check_connect),Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(SplashActivity.this, LoginEmailActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);

            }
        });

        //endregion
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loginWithToken(){
        if(!ToolsActivity.isNetworkOnline(this)){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.check_connect),Toast.LENGTH_SHORT).show();
            return;
        }
        if (AccessToken.getCurrentAccessToken() != null) {
            Profile profile = Profile.getCurrentProfile();
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            User user = new User(profile.getName(),profile.getFirstName(), profile.getLastName(), "", profile.getId(),profile.getProfilePictureUri(150,150).toString());
            UserLogin.setUserLogin(user);
            System.out.println("toi muon biet apikey:"+user.getApiKey());
            startActivity(i);
            finish();
        }
       if(mSessionManager.isLoggedIn()){
           HashMap<String, String> user = mSessionManager.getUserDetails();
           Gson gson = new Gson();
           String json = user.get(SessionManager.UserLogin);
           User userLogin = gson.fromJson(json, User.class);
           System.out.println("toi muon biet apikey:"+userLogin.getApiKey());
           Intent i = new Intent(SplashActivity.this, MainActivity.class);
           UserLogin.setUserLogin(userLogin);
           startActivity(i);
           finish();
       }
    }
}
