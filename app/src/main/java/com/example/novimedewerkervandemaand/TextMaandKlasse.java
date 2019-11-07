/**
 * Leerlijn   : Praktijk Opdracht 1
 * Created on : Mei 15, 2019, 09:55:20 PM
 * Author     : Tyron.Kemble
 */
package com.example.novimedewerkervandemaand;

import android.app.Activity;
import android.graphics.Color;
import android.text.Layout;
import android.view.View;

import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.TextSticker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextMaandKlasse {
    private DateFormat dateFormat = new SimpleDateFormat("MM");
    private Date date = new Date();
    private TextSticker sticker;

    protected void onClickDezeMaand(View v,StickerView stickerView, Activity activity) {
        sticker = new TextSticker(activity);
        sticker.setTextColor(Color.BLUE);
        sticker.setText("Novi Medewerker van de maand" + " " + maandOphalen());
        sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        sticker.resizeText();
        stickerView.addSticker(sticker);
    }

    private String maandOphalen() {
        switch (dateFormat.format(date)) {
            case "01":
                return "Januari";
            case "02":
                return "Februari";
            case "03":
                return "Maart";
            case "04":
                return "April";
            case "05":
                return "Mei";
            case "06":
                return "Juni";
            case "07":
                return "Juli";
            case "08":
                return "Augustus";
            case "09":
                return "September";
            case "10":
                return "Oktober";
            case "11":
                return "November";
            case "12":
                return "December";
            default:
                return "No Month";
        }

    }
}
