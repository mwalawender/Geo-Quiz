package geoquiz.walawender.michal.pl.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

    public static final int SPLASH_TIME = 2000;
    private Handler launchLoginHandler;
    private UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        session = UserSessionManager.getInstance();
        session.init(getApplicationContext());
        startSplash();
    }

    private void startSplash() {
        launchLoginHandler = new Handler();
        launchLoginHandler.postDelayed(new Runnable() {
            public void run() {
                session.checkLogin();
                finish();
            }
        }, SPLASH_TIME);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        launchLoginHandler.removeMessages(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
