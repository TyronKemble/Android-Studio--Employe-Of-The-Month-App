/**
 * Leerlijn   : Praktijk Opdracht 1
 * Created on : Mei 14, 2019, 10:30:53 PM
 * Author     : Tyron.Kemble
 */
package com.example.novimedewerkervandemaand;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.xiaopo.flying.sticker.StickerView;

import java.io.File;
import java.io.IOException;

public class CameraKlasse {

    private FotoKlasse fotoKlasse;
    private GalerijKlasse galerijKlasse;
    private GadgetKlasse gadgetKlasse;
    private File photoFile = null;

    CameraKlasse() {
        this.fotoKlasse = new FotoKlasse();
        this.galerijKlasse = new GalerijKlasse();
        this.gadgetKlasse = new GadgetKlasse();
    }

    protected File dispatchTakePictureIntent(Activity activity, StickerView stickerView, int REQUEST_TAKE_PHOTO) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {


                try {
                    photoFile = fotoKlasse.createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (photoFile != null) {

                Uri photoURI = FileProvider.getUriForFile(activity,
                        "com.example.android.fileprovider2",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

                try {
                    galerijKlasse.GalerijUpdaten(activity,photoFile);
                    gadgetKlasse.VerwijderAlleStickers(stickerView);

                }catch (Exception x){
                    x.printStackTrace();
                }

            }
        }
        return photoFile;
    }

    protected void verwijderTempFoto(Activity activity){
        File f = new File(String.valueOf(this.photoFile));
        try {
            if (f.exists()){
                f.delete();
            }
        } catch (Exception x){
            x.printStackTrace();
        }

        galerijKlasse.GalerijUpdaten(activity,f);
    }
}

