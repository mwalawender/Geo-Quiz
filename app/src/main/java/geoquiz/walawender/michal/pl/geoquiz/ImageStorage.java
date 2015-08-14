package geoquiz.walawender.michal.pl.geoquiz;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ImageStorage {

    private static ImageStorage instance;

    private ImageStorage(){

    }

    public static ImageStorage getInstance()
    {
        if (instance == null)
        {
            instance = new ImageStorage();
        }
        return instance;
    }

    public String saveToInternalStorage(Bitmap bitmapImage, Context c, String fileName){
        ContextWrapper contextWrapper = new ContextWrapper(c);
        File directory = contextWrapper.getDir("imageDir", Context.MODE_PRIVATE);
        File filePath=new File(directory,fileName);
        if(!filePath.isFile()){

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(filePath);
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
        return directory.getAbsolutePath();
    }

    public Bitmap loadImageFromStorage(String path, String fileName)
    {
        Bitmap b = null;
        try {
            File f=new File(path, fileName);
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return b;
    }
}
