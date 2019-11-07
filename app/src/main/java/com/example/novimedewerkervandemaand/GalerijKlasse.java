/**
 * Leerlijn   : Praktijk Opdracht 1
 * Created on : Mei 15, 2019, 08:15:55 PM
 * Author     : Tyron.Kemble
 */
package com.example.novimedewerkervandemaand;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.xiaopo.flying.sticker.StickerView;

import java.io.File;

public class GalerijKlasse  {

    private Integer SELECT_FILE =0;
    private Intent intent;
    private Intent intentImage;
    private GadgetKlasse gadgetKlasse;

    GalerijKlasse() {
        this.intentImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).setType("image/");
        this.gadgetKlasse = new GadgetKlasse();
    }

    protected void FotoSelecterenUitGalerij(Activity activity, StickerView stickerView){
        activity.startActivityForResult(intent.createChooser(intentImage, "Select File"), SELECT_FILE);
        gadgetKlasse.VerwijderAlleStickers(stickerView);
    }


    protected void GalerijUpdaten(Activity activity, File photoFile) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photoFile);
        mediaScanIntent.setData(contentUri);
        activity.sendBroadcast(mediaScanIntent);
    }
}
