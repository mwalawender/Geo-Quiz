package screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import geoquiz.walawender.michal.pl.geoquiz.LogoutDialog;
import geoquiz.walawender.michal.pl.geoquiz.R;
import geoquiz.walawender.michal.pl.geoquiz.UserSessionManager;
import mehdi.sakout.fancybuttons.FancyButton;

public class LogoutScreen extends Fragment implements View.OnClickListener {

    FancyButton btnShare, btnClose, btnLogout;
    UserSessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logout_screen, container, false);

        btnClose = (FancyButton) view.findViewById(R.id.btnMoreApps);
        btnLogout = (FancyButton) view.findViewById(R.id.btnLogout);
        btnShare = (FancyButton) view.findViewById(R.id.btnShareApp);

        btnShare.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShareApp:
                break;
            case R.id.btnMoreApps:
                break;
            case R.id.btnLogout:
                LogoutDialog logoutDialog = new LogoutDialog();
                logoutDialog.show(getFragmentManager(), "Logout Dialog");
                break;
        }
    }

    public static LogoutScreen newInstance(String text) {

        LogoutScreen f = new LogoutScreen();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);

        return f;
    }
}
