/**
 * Leerlijn   : Praktijk Opdracht 1
 * Created on : Mei 22, 2019, 07:45:13 PM
 * Author     : Tyron.Kemble
 */
package com.example.novimedewerkervandemaand;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FotoVerzendenKlasse {

    private FotoEditKlasse fotoEditKlasse = new FotoEditKlasse();

    protected void startShare(ImageView ivImage, Activity activity){

        Bitmap bitmap = viewToBitmap(ivImage,ivImage.getWidth(), ivImage.getHeight());
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpeg");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        String imageFileName = "JPEG_" + fotoEditKlasse.getTimeStamp() + "_";
        File file = new File(Environment.getExternalStorageDirectory() + File.separator +"/DCIM/Camera/"+imageFileName+".jpg");
        try {
            if (file.createNewFile()) {

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write((byteArrayOutputStream.toByteArray()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file ));
        activity.startActivity(Intent.createChooser(shareIntent, "Share Image"));
    }
    private Bitmap viewToBitmap (View view, int width, int height){
        Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

}
