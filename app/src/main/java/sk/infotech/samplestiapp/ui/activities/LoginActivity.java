package sk.infotech.samplestiapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sk.infotech.samplestiapp.BuildConfig;
import sk.infotech.samplestiapp.R;
import sk.infotech.samplestiapp.login.ILoginView;
import sk.infotech.samplestiapp.login.LoginPresenter;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements ILoginView{

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private LoginPresenter mLoginPresenter;

    // Constants
    public final static int EMPTY_LOGIN = 0;
    public final static int EMPTY_PASSWORD = 1;
    public final static int INVALID_USERNAME = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginPresenter = new LoginPresenter(this);

        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.sign_in_btn);
       /* mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //...
            }
        });*/

        // TODO HomeWork make edittext with next and done buttons
        // add checkbox with Remember login field and get value
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.progress);

        if (BuildConfig.DEBUG) {
            mEmailView.setText("a");
            mPasswordView.setText("a");
        }

    }

    public void tappedLogin(View view) {
        mProgressView.setVisibility(View.VISIBLE);
        mLoginFormView.setVisibility(View.GONE);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        mLoginPresenter.attemptLogin(email, password);
    }


    @Override
    public void wrongUsername(int errorType) {
        mProgressView.setVisibility(View.GONE);
        mLoginFormView.setVisibility(View.VISIBLE);
        mEmailView.requestFocus();

        if (errorType == EMPTY_LOGIN) {
            mEmailView.setError(getString(R.string.error_field_required));
        }
        else if (errorType == INVALID_USERNAME) {
            mEmailView.setError(getString(R.string.error_invalid_email));
        }
    }

    @Override
    public void wrongPassword(int errorType) {
        mProgressView.setVisibility(View.GONE);
        mLoginFormView.setVisibility(View.VISIBLE);
        mPasswordView.requestFocus();

        if (errorType == EMPTY_PASSWORD) {
            mPasswordView.setError(getString(R.string.error_field_required));
        }
    }

    /*
        Intent - oficialna dokumentacia: http://developer.android.com/reference/android/content/Intent.html

        V skratke, je to sprava operacnemu systemu, ze sa ma nieco vykonat, v tomto pripade sa ma vykonat spustenie novej
        aktivity
     */
    @Override
    public void goToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }
}

