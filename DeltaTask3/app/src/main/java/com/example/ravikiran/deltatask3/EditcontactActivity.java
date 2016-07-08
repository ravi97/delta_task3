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

public class EditcontactActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE=1;
    Context context;
    Bitmap changedProfile;
    ImageView targetImage;
    EditText nameField;
    EditText numberField;
    ContactDetails oldDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcontact);
        context=this;
        targetImage=(ImageView)findViewById(R.id.imageView3);
        nameField=(EditText)findViewById(R.id.editText4);
        numberField=(EditText)findViewById(R.id.editText6);

        oldDetails=new ContactDetails();
        Intent intent=getIntent();
        oldDetails.setName(intent.getStringExtra("name"));
        oldDetails.setPh_no(intent.getStringExtra("number"));
        oldDetails.setImage(oldDetails.byteToBitmap(intent.getByteArrayExtra("profileInBytes")));
        nameField.setText(oldDetails.getName());
        numberField.setText(oldDetails.getPh_no());
        targetImage.setImageBitmap(oldDetails.getImage());
        changedProfile=oldDetails.getImage();


    }

    public void changeImage(View view){
        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            try {
                changedProfile = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                targetImage.setImageBitmap(changedProfile);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

    public void saveAndGoBack(View view){
        ContactsStorage toEdit=new ContactsStorage(context,null,null,1);
        ContactDetails newDetails=new ContactDetails();
        newDetails.setName(nameField.getText().toString());
        newDetails.setPh_no(numberField.getText().toString());
        newDetails.setImage(changedProfile);
        toEdit.deleteRow(oldDetails.getName());
        toEdit.addRow(newDetails);
        Intent i=new Intent(context,MainActivity.class);
        startActivity(i);
        finish();



    }

    public void deleteAndGoBack(View view){
        ContactsStorage toDelete=new ContactsStorage(context,null,null,1);
        toDelete.deleteRow(oldDetails.getName());
        Intent i=new Intent(context,MainActivity.class);
        startActivity(i);
        finish();

    }



}
