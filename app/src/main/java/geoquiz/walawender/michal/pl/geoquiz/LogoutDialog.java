package geoquiz.walawender.michal.pl.geoquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class LogoutDialog extends DialogFragment {

    UserSessionManager sessionManager;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setTitle("Exit App.").setMessage("Thanks for using our app,please rate us for support, logout or exit.")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                }).setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionManager = UserSessionManager.getInstance();
                        sessionManager.logoutUser();
                    }
                }).setNeutralButton("Rate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity().getApplicationContext(), "Rate App", Toast.LENGTH_LONG).show();
                    }
                })
                .create();
    }
}
