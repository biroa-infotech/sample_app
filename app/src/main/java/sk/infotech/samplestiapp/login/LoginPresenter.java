package sk.infotech.samplestiapp.login;

import android.os.Handler;

import sk.infotech.samplestiapp.ui.activities.LoginActivity;

/**
 * Created by MacMini on 25/01/16.
 */
public class LoginPresenter {


    private ILoginView mView;

    public LoginPresenter(ILoginView view) {
        this.mView = view;
    }

    public void attemptLogin(String username, String password) {

        if (username.isEmpty()) {
            mView.wrongUsername(LoginActivity.EMPTY_LOGIN);
        }
        else if (password.isEmpty()) {
            mView.wrongPassword(LoginActivity.EMPTY_PASSWORD);
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mView.goToMainScreen();
                }
            }, 2000);
        }
        // TODOregex email validation
    }
}
