package geoquiz.walawender.michal.pl.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import panels.MainMenuPanel;

public class UserSessionManager {

    public static final String PREFER_NAME = "GeoQuizPrefs";
    public static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_NAME = "name";
    public static final int PRIVATE_MODE = 0;

    SharedPreferences sessionPrefs;
    Editor editor;
    Context context;

    private static UserSessionManager instance;

    private UserSessionManager() {

    }

    public static UserSessionManager getInstance() {
        if (instance == null) {
            instance = new UserSessionManager();
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
        sessionPrefs = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = sessionPrefs.edit();
    }

    public void createUserLoginSession(String name, boolean isLogged) {
        editor.putBoolean(IS_USER_LOGIN, isLogged);
        editor.putString(KEY_NAME, name);
        editor.commit();
        Intent i = new Intent(context, MainMenuPanel.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }

    public void checkLogin() {
        if (!this.isUserLoggedIn()) {
            Intent i = new Intent(context, LoginWindow.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            Intent i = new Intent(context, MainMenuPanel.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginWindow.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i);
    }

    public boolean isUserLoggedIn() {
        return sessionPrefs.getBoolean(IS_USER_LOGIN, false);
    }
}
