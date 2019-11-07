/**
 * Leerlijn   : Praktijk Opdracht 1
 * Created on : Mei 14, 2019, 10:42:53 PM
 * Author     : Tyron.Kemble
 */
package com.example.novimedewerkervandemaand;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FotoKlasse {

    private File publicDir;
    private String timeStamp;
    private String imageFileName;

    public FotoKlasse() {
        this.timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        this.imageFileName = "JPEG_" + timeStamp + "_";
    }


    protected File createImageFile() throws IOException {

        publicDir = Environment.getExternalStoragePublicDirectory("/DCIM/Camera");
        if (publicDir.isDirectory()){
            publicDir.mkdir();
        }

        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                publicDir
        );

        return image;
    }

}

