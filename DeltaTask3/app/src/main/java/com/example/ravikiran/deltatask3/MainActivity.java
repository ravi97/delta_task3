package com.example.ravikiran.deltatask3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   // ArrayList<String> name=new ArrayList<String>();
   // ArrayList<Integer> number=new ArrayList<Integer>();
   // ArrayList<Bitmap> profile=new ArrayList<Bitmap>();
    ListView lv;
    Context context;
    String nameOrNumber;
    EditText toBeSearched;
    ArrayList<ContactDetails> allDetails;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listView);
        toBeSearched=(EditText)findViewById(R.id.editText3);
        context=this;
        showList();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ContactDetails clickedDetails=(ContactDetails)parent.getItemAtPosition(position);
                        Intent intent=new Intent(context,EditcontactActivity.class);
                        intent.putExtra("name",clickedDetails.getName());
                        intent.putExtra("number",clickedDetails.getPh_no());
                        intent.putExtra("profileInBytes",clickedDetails.bitmapToByte(clickedDetails.getImage()));
                        startActivity(intent);
                        finish();



                    }
                }
        );

    }

    public void addContact(View v){
        Intent i=new Intent(this,AddcontactActivity.class);
        startActivity(i);
        finish();

    }


    public void showList(){
        allDetails=new ArrayList<ContactDetails>();
        allDetails.clear();
        ContactsStorage displayAll = new ContactsStorage(MainActivity.this, null, null, 1);
        Cursor c = displayAll.returnFullTableCursor();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                            ContactDetails contactDetails = new ContactDetails();
                            contactDetails.setName(c.getString(c.getColumnIndex(displayAll.COLUMN_NAME)));
                            contactDetails.setPh_no(c.getString(c.getColumnIndex(displayAll.COLUMN_NUMBER)));
                            contactDetails.setImage(contactDetails.byteToBitmap(c.getBlob(c.getColumnIndex(displayAll.COLUMN_PROFILE))));
                            allDetails.add(contactDetails);


                } while (c.moveToNext());
            }
        }
        c.close();
        if(allDetails.size()!=0){

        CustomAdapter ca=new CustomAdapter(allDetails,this);
        lv.setAdapter(ca);
        }
    }
    
    public void search(View view){
        nameOrNumber=toBeSearched.getText().toString();
        int i=0;
        boolean found=false;
        while(!found&&i<allDetails.size()){
            if(allDetails.get(i).getName().equals(nameOrNumber)||allDetails.get(i).getPh_no().equals(nameOrNumber)){
                found=true;
            }
            else {
                i++;
            }
        }
        if(found){
            Toast.makeText(MainActivity.this, "This corresponds to\nName : "+allDetails.get(i).getName()+"\nNumber : "+allDetails.get(i).getPh_no(), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Contact does not exist", Toast.LENGTH_SHORT).show();
        }
        
        
    }

}
