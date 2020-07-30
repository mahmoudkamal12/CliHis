package com.example.clihis;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRActivity extends AppCompatActivity {
   ImageView qr_image;


   FirebaseUser firebaseUser;
   DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r);

        qr_image=findViewById(R.id.qr_image);

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String userId=firebaseUser.getUid();

        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        try {
            BitMatrix bitMatrix=qrCodeWriter.encode(userId, BarcodeFormat.QR_CODE,200,200);
            Bitmap bitmap=Bitmap.createBitmap(200,200,Bitmap.Config.RGB_565);

            for (int x = 0;x<200;x++){
                for (int y=0;y<200;y++){
                    bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK : Color.WHITE);

                }
            }

            qr_image.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }


    }
}