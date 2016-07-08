package com.example.ravikiran.deltatask3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;

/**
 * Created by Ravikiran on 7/5/2016.
 */
public class ContactDetails {
    private String ph_no;
    private String name;
    private Bitmap profile;

    public ContactDetails() {
    }

    public ContactDetails(String ph_no, String name, Bitmap profile) {
        this.ph_no = ph_no;
        this.name = name;
        this.profile = profile;
    }

    public byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);
        return stream.toByteArray();
    }
    public Bitmap byteToBitmap(byte[]image){
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        return BitmapFactory.decodeByteArray(image,0,image.length);
    }

    public Bitmap scaleDownBitmap(Bitmap oldImage){
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();
        Matrix m = new Matrix();
        m.postScale(((float)100)/width,((float)100)/height);
        Bitmap bnew=Bitmap.createBitmap(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(), m, false);
        return bnew;
    }

    public String getPh_no() {
        return ph_no;
    }

    public Bitmap getImage() {
        return profile;
    }

    public void setImage(Bitmap image) {
        this.profile = image;
    }

    public String getName() {
        return name;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }

    public void setName(String name) {
        this.name = name;
    }

}
