/**
 * Leerlijn   : Praktijk Opdracht 1
 * Created on : Mei 15, 2019, 09:50:01 PM
 * Author     : Tyron.Kemble
 */
package com.example.novimedewerkervandemaand;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.xiaopo.flying.sticker.BitmapStickerIcon;
import com.xiaopo.flying.sticker.StickerView;

public class GadgetKlasse {


    private int imageRes[];
    private BitmapStickerIcon alleStickers;


    protected StickerView AlleStickers (View v, StickerView stickerView, Activity activity, int drawable){
        alleStickers =
                new BitmapStickerIcon(ContextCompat.getDrawable(activity, drawable),
                        BitmapStickerIcon.LEFT_BOTTOM);

        return stickerView.addSticker(alleStickers);
    }


    protected   Boolean VerwijderGeselecteerdeSticker (View v, StickerView stickerView){
        return stickerView.removeCurrentSticker();
    }

    protected  void VerwijderAlleStickers(StickerView stickerView){
        stickerView.removeAllStickers();
    }


    protected void onClickEditOptiesZichtbaar(FloatingActionButton VerwijderGeselecteerdeStickers, FloatingActionButton SaveEditImage ){
        VerwijderGeselecteerdeStickers.show();
        SaveEditImage.show();
    }

    protected void onClickEditOptiesNietZichtbaar( FloatingActionButton VerwijderGeselecteerdeStickers, FloatingActionButton SaveEditImage){
        VerwijderGeselecteerdeStickers.hide();
        SaveEditImage.hide();
    }

}
