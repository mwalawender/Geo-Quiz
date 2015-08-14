package geoquiz.walawender.michal.pl.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;

import mehdi.sakout.fancybuttons.FancyButton;

public class LoginWindow extends Activity implements View.OnClickListener {

    public static final String testLogin = "test";
    public static final String testPassword = "12345";

    private UserSessionManager sessionManager;
    BootstrapEditText inputLogin, inputPassword;
    FancyButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initializeViews();

        sessionManager = UserSessionManager.getInstance();
    }

    private void initializeViews() {

        inputLogin = (BootstrapEditText) findViewById(R.id.txtLogin);
        inputPassword = (BootstrapEditText) findViewById(R.id.txtPassword);
        btnSignIn = (FancyButton) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                validateUser();
                break;
        }
    }

    private void validateUser() {

        User user = new User(inputLogin.getText().toString(), inputPassword.getText().toString());
        Validator validator = new Validator();

        if (!validator.validateData(user.getUserLogin(), testLogin, user.getUserPassword(), testPassword)) {
            Toast.makeText(getApplicationContext(), "Wrong login or password, try again!", Toast.LENGTH_LONG).show();
        } else {

            user.setUserLogin(inputLogin.getText().toString());
            user.setUserPassword(inputPassword.getText().toString());
            user.setIsLogged(true);

            sessionManager.createUserLoginSession(user.getUserLogin(), user.isLogged());
            finish();
        }
    }
}
