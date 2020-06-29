package id.fajarproject.training.feature.login;


import android.app.Activity;
import android.os.Handler;

import id.fajarproject.training.model.User;
import id.fajarproject.training.util.AppPreference;

/**
 * Create by Fajar Adi Prasetyo on 24/06/2020.
 */
public class LoginPresenter {

    LoginView view;
    User user;
    Activity activity;

    public LoginPresenter(Activity activity,LoginView view){
        this.view = view;
        this.activity   = activity;
        this.user = new User();
    }

    public boolean validateLogin(User user){
        boolean isLogin = true;
        if (user.getUsername() == null){
            view.showErrorUserName();
            isLogin = false;
        }
        if (user.getPassword() == null){
            view.showErrorPassword();
            isLogin = false;
        }
        return isLogin;
    }

    public void login(final User params){
        view.showLoading(true);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,60*10000);
                if (user.validUser(params.getUsername(),params.getPassword())){
                    AppPreference.writeToPreference(activity,"user",params.getUsername());
                    view.showSuccessLogin();
                }else {
                    view.showErrorLogin();
                }
                handler.removeCallbacks(this);
            }
        });
    }
}
