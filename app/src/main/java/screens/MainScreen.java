package screens;

import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import geoquiz.walawender.michal.pl.geoquiz.R;

public class MainScreen extends Fragment {

    private TextView firstTextView, secondTextView, thirdTextView, fourthTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.middle_screen, container, false);
        firstTextView = (TextView) view.findViewById(R.id.firstTextView);
        secondTextView = (TextView) view.findViewById(R.id.secondTextView);
        thirdTextView = (TextView) view.findViewById(R.id.thirdTextView);
        fourthTextView = (TextView) view.findViewById(R.id.fourthTextView);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");

        firstTextView.setTypeface(font);
        secondTextView.setTypeface(font);
        thirdTextView.setTypeface(font);
        fourthTextView.setTypeface(font);

        return view;
    }

    public static MainScreen newInstance(String text) {

        MainScreen f = new MainScreen();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);

        return f;
    }

}
