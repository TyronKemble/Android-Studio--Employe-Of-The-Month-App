/**
 * Leerlijn   : Praktijk Opdracht 1
 * Created on : Mei 13, 2019, 10:30:53 PM
 * Author     : Tyron.Kemble
 */
package com.example.novimedewerkervandemaand;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.xiaopo.flying.sticker.StickerView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView ivImage;
    private StickerView stickerView;
    private View v;
    private File image;

    private Integer SELECT_FILE=0;
    private Integer REQUEST_TAKE_PHOTO=1;

    private FloatingActionButton cam;
    private FloatingActionButton editMenu;
    private FloatingActionButton verzend;
    private FloatingActionButton VerwijderGeselecteerdeStickers;
    private FloatingActionButton SaveEditImage;

    private GalerijKlasse galerijKlasse;
    private CameraKlasse cameraKlasse;
    private FotoVerzendenKlasse fotoVerzendenKlasse;
    private FotoEditKlasse fotoEditKlasse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cam = findViewById(R.id.cam);
        editMenu = findViewById(R.id.edit);
        verzend = findViewById(R.id.verzend);
        VerwijderGeselecteerdeStickers = findViewById(R.id.VerwijderGeselecteerdeStickers);
        SaveEditImage = findViewById(R.id.SaveEditImage);
        ivImage = (ImageView) findViewById(R.id.image_view);
        stickerView = (StickerView) findViewById(R.id.stickerView);


        if (Build.VERSION.SDK_INT >= 24){
            requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }
        else if (Build.VERSION.SDK_INT >= 24){
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET},2);
        }

        init();
    }

    private void init(){
        onClickCameraGallerijMenu();
        onClickEditMenu();
        onClickVerzendFotoMenu();
        onClickVerwijderGeselecteerdeSticker();
        OnClickSaveEditImage();

        fotoEditKlasse = new FotoEditKlasse();
        cameraKlasse = new CameraKlasse();
        fotoVerzendenKlasse = new FotoVerzendenKlasse();
        galerijKlasse = new GalerijKlasse();
        fotoEditKlasse.getGadgetKlasse().onClickEditOptiesNietZichtbaar(VerwijderGeselecteerdeStickers,SaveEditImage);
    }

    private void onClickCameraGallerijMenu(){
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectieMenuCamera();
            }
        });
    }

    private void  onClickEditMenu(){
        editMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectieMenuEditFoto();
            }
        });
    }

    private void  onClickVerzendFotoMenu(){
        verzend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SelectieMenuVerzendFoto();
            }
        });
    }


    private void  onClickVerwijderGeselecteerdeSticker(){
        VerwijderGeselecteerdeStickers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fotoEditKlasse.getGadgetKlasse().VerwijderGeselecteerdeSticker(v,stickerView);
            }
        });
    }


    private  void OnClickSaveEditImage(){
        SaveEditImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fotoEditKlasse.EditFotoOpslaan(ivImage,stickerView,MainActivity.this);
            }
        });
    }


    private void SelectieMenuCamera(){

        final CharSequence[] items={"Camera","Gallery","Cancel"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Camera/Galerij Menu");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    fotoEditKlasse.getGadgetKlasse().onClickEditOptiesNietZichtbaar(VerwijderGeselecteerdeStickers,SaveEditImage);
                    image = cameraKlasse.dispatchTakePictureIntent(MainActivity.this, stickerView, REQUEST_TAKE_PHOTO);
                } else if (items[i].equals("Gallery")) {
                    galerijKlasse.FotoSelecterenUitGalerij(MainActivity.this, stickerView);
                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();

    }



    private void SelectieMenuEditFoto(){

        final CharSequence[] items={"Edit","Cancel"};

        final CharSequence[] edit = {"TextMaand", "Rode Neus", "Rode Oren", "Zonnebril", "Zomerse Hoed", "Strand Bal","Cancel" };
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Edit Foto Menu");


        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, final int i) {
                if (items[i].equals(("Edit"))){

                    fotoEditKlasse.getGadgetKlasse().onClickEditOptiesZichtbaar(VerwijderGeselecteerdeStickers,SaveEditImage);

                    builder.setItems(edit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int t) {
                            if (edit[t].equals("TextMaand")){
                                fotoEditKlasse.getTextMaandKlasse().onClickDezeMaand(v,stickerView, MainActivity.this);
                            } else if (edit[t].equals("Rode Neus")){
                                fotoEditKlasse.getGadgetKlasse().AlleStickers(v,stickerView, MainActivity.this,R.drawable.rode_neus);
                            }else if (edit[t].equals("Rode Oren")){
                                fotoEditKlasse.getGadgetKlasse().AlleStickers(v,stickerView, MainActivity.this,R.drawable.oren);
                            }else if (edit[t].equals("Zonnebril")){
                                fotoEditKlasse.getGadgetKlasse().AlleStickers(v,stickerView, MainActivity.this,R.drawable.bril);
                            }else if (edit[t].equals("Zomerse Hoed")){
                                fotoEditKlasse.getGadgetKlasse().AlleStickers(v,stickerView, MainActivity.this,R.drawable.beachhat);
                            }else if (edit[t].equals("Strand Bal")){
                                fotoEditKlasse.getGadgetKlasse().AlleStickers(v,stickerView, MainActivity.this,R.drawable.beachball);
                            } else if(edit[t].equals("Cancel")){
                                fotoEditKlasse.getGadgetKlasse().onClickEditOptiesNietZichtbaar(VerwijderGeselecteerdeStickers,SaveEditImage);
                                dialogInterface.dismiss();

                            }
                        }
                    });
                    builder.show();
                } else if (items[i].equals("Cancel")) {
                    fotoEditKlasse.getGadgetKlasse().onClickEditOptiesNietZichtbaar(VerwijderGeselecteerdeStickers,SaveEditImage);
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();

    }


    private void SelectieMenuVerzendFoto(){

        final CharSequence[] items={"Send","Cancel"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Verzend Foto");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                 if (items[i].equals("Send")) {
                     fotoVerzendenKlasse.startShare(ivImage,MainActivity.this);
                     fotoEditKlasse.getGadgetKlasse().onClickEditOptiesNietZichtbaar(VerwijderGeselecteerdeStickers,SaveEditImage);
                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if (resultCode == RESULT_OK){
                if (requestCode == REQUEST_TAKE_PHOTO){
                    Bitmap bitmap = BitmapFactory.decodeFile(image.toString());
                    ivImage.setImageBitmap(bitmap);
                    }
                 if(requestCode==SELECT_FILE){

                    Uri selectedImageUri = data.getData();
                    ivImage.setImageURI(selectedImageUri);
                    }

                } else if (resultCode == Activity.RESULT_CANCELED){
                    if (requestCode == REQUEST_TAKE_PHOTO) {
                        cameraKlasse.verwijderTempFoto(MainActivity.this);
                    }
                }
        }
}

