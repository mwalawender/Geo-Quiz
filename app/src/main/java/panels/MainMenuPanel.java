package panels;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import geoquiz.walawender.michal.pl.geoquiz.R;
import geoquiz.walawender.michal.pl.geoquiz.UserSessionManager;
import screens.ImageDownloadScreen;
import screens.LogoutScreen;
import screens.MainScreen;

public class MainMenuPanel extends FragmentActivity {

    public static final String TOP_SCREEN_TAG = "top";

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(MainMenuPanel.this)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

        checkInternetConnection();
        initializeViews();

        sessionManager = UserSessionManager.getInstance();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return MainScreen.newInstance("MainScreen, Instance 1");
                case 1: return ImageDownloadScreen.newInstance("ImageDownloadScreen, Instance 1");
                case 2: return LogoutScreen.newInstance("LogoutScreen, Instance 1");
                default: return MainScreen.newInstance("MainScreen, Default");
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initializeViews() {

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        TopPanel topPanel = new TopPanel();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerOne, topPanel, TOP_SCREEN_TAG);
        fragmentTransaction.commit();
    }

    private void checkInternetConnection() {
        if (!isInternetConnectionAvailable()) {
            showDialogWithInternetAlert();
        }
    }

    private void showDialogWithInternetAlert() {

        new AlertDialog.Builder(MainMenuPanel.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setTitle("No Internet Connection!")
                .setMessage("To properly use our app, please turn on internet connection!")
                .setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        turnOnWifi();
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })

                .show();
    }

    private void turnOnWifi() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
    }

    public Boolean isInternetConnectionAvailable() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1    www.onet.pl");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            if (reachable) {
                System.out.println("Internet access");
                return reachable;
            } else {
                System.out.println("No Internet access");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onBackPressed() {

    }

}
