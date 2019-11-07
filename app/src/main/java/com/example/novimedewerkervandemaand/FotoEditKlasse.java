/**
 * Leerlijn   : Praktijk Opdracht 1
 * Created on : Mei 14, 2019, 10:56:55 PM
 * Author     : Tyron.Kemble
 */
package com.example.novimedewerkervandemaand;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiaopo.flying.sticker.StickerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FotoEditKlasse {

    private String timeStamp;
    private GadgetKlasse gadgetKlasse;
    private TextMaandKlasse textMaandKlasse;

    FotoEditKlasse() {
        this.timeStamp =  new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        this.gadgetKlasse = new GadgetKlasse();
        this.textMaandKlasse = new TextMaandKlasse();
    }


    protected GadgetKlasse getGadgetKlasse() {
        return gadgetKlasse;
    }

    protected TextMaandKlasse getTextMaandKlasse() {
        return textMaandKlasse;
    }

    protected String getTimeStamp() {
        return timeStamp;
    }

    protected void EditFotoOpslaan(ImageView ivImage, StickerView stickerView, Activity activity) {

        ivImage.invalidate();
        BitmapDrawable drawable = (BitmapDrawable) ivImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String imageFileName = "JPEG_" + this.timeStamp + "_";
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "/DCIM/Camera/" + imageFileName + ".jpg");

        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write((byteArrayOutputStream.toByteArray()));
            stickerView.save(file);
            FileCheckerToastBericht(file, activity);
        } catch (Exception x){
            x.printStackTrace();
        }
    }

    private void FileCheckerToastBericht(File file , Activity activity){
        if (file != null) {
            Toast savedToast = Toast.makeText(activity.getApplicationContext(),
                    "Foto Opgeslagen", Toast.LENGTH_LONG);
            savedToast.show();
        } else {
            Toast unSaved = Toast.makeText(activity.getApplicationContext(),
                    "Opps, Error, Image not Saved", Toast.LENGTH_LONG);
            unSaved.show();
        }
    }

}
