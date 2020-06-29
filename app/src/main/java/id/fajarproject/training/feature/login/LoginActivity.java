package id.fajarproject.training.feature.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.fajarproject.training.R;
import id.fajarproject.training.model.User;
import id.fajarproject.training.feature.movie.MovieActivity;
import id.fajarproject.training.util.AppPreference;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.loading)
    ConstraintLayout loading;

    LoginPresenter presenter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this,this);
        user = new User();
        setAction();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!AppPreference.getStringPreferenceByName(this,"user").isEmpty()){
            showSuccessLogin();
        }
    }

    @Override
    public void showSuccessLogin() {
        showLoading(false);
        Intent intent = new Intent(this, MovieActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorLogin() {
        showLoading(false);
        showDialog("Username dan Password salah");
    }

    @Override
    public void showErrorUserName() {
        etUsername.setError("Username harus diisi");
    }

    @Override
    public void showErrorPassword() {
        etPassword.setError("Password harus diisi");
    }

    @Override
    public void setAction() {

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user.setUsername(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user.setPassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.validateLogin(user)){
                    presenter.login(user);
                }else {
                    showDialog("Please check data");
                }
            }
        });
    }

    @Override
    public void showLoading(boolean isShow) {
        if (isShow){
            loading.setVisibility(View.VISIBLE);
            setEnableUI(false);
        }else {
            loading.setVisibility(View.GONE);
            setEnableUI(true);
        }
    }

    @Override
    public void setEnableUI(boolean isEnable) {
        if (isEnable){
            btnLogin.setEnabled(true);
            etUsername.setEnabled(true);
            etPassword.setEnabled(true);
        }else {
            btnLogin.setEnabled(false);
            etUsername.setEnabled(false);
            etPassword.setEnabled(false);
        }
    }

    @Override
    public void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();

        alert.show();
    }

}