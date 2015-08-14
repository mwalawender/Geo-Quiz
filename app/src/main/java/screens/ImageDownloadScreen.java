package screens;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import geoquiz.walawender.michal.pl.geoquiz.ImageStorage;
import geoquiz.walawender.michal.pl.geoquiz.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class ImageDownloadScreen extends Fragment {

    public static final String IMAGE_URL = "http://carryingthegun.files.wordpress.com/2012/09/personal_trollface_hd.png";
    public static final String FILENAME = "testimage1.jpg";
    private Bitmap cachedImage = null;
    private String result;
    FancyButton btnDownloadImage;
    ImageView downloadedImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.image_screen, container, false);
        btnDownloadImage = (FancyButton)view.findViewById(R.id.btnDownloadImage);
        downloadedImage = (ImageView)view.findViewById(R.id.imageViewDownloadedImage);
        downloadedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cachedImage!= null){
                    Toast.makeText(getActivity().getApplicationContext(), "Image saved in: " + result, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageLoader imageLoader = ImageLoader.getInstance();
                imageLoader.displayImage(IMAGE_URL, downloadedImage, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        cachedImage = loadedImage;
                        downloadedImage.setImageBitmap(cachedImage);
                        ImageStorage imgStorage = ImageStorage.getInstance();
                        result = imgStorage.saveToInternalStorage(cachedImage, getActivity().getApplicationContext(), FILENAME);

                    }
                });
            }
        });
        return view;
    }

    public static ImageDownloadScreen newInstance(String text) {

        ImageDownloadScreen f = new ImageDownloadScreen();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);

        return f;
    }
}
