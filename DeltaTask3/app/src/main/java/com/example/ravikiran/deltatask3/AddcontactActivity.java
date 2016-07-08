package com.example.ravikiran.deltatask3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class AddcontactActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE=1;
    ImageView targetImage;
    EditText name;
    EditText number;
    Bitmap newprofile;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);
        context=this;
        targetImage=(ImageView)findViewById(R.id.imageView2);
        name=(EditText)findViewById(R.id.editText);
        number=(EditText)findViewById(R.id.editText2);
        newprofile=BitmapFactory.decodeResource(getResources(),R.drawable.default_profile);

    }

    public void getImage(View view){
        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                newprofile = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                targetImage.setImageBitmap(newprofile);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

    public void addAndGoBack(View view){

        ContactDetails newDetails=new ContactDetails();
        newDetails.setName(name.getText().toString());
        newDetails.setPh_no(number.getText().toString());
        Bitmap profileToBeAdded=newDetails.scaleDownBitmap(newprofile);
        newDetails.setImage(profileToBeAdded);
        ContactsStorage newEntries=new ContactsStorage(context, null,null,1);
        newEntries.addRow(newDetails);
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();

    }
}
