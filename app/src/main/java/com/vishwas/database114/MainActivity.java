package com.vishwas.database114;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    EditText first_Name,last_Name;
    Button addButton;
    Button viewAllButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);

        first_Name = (EditText)findViewById(R.id.editTextFirstName);
        last_Name = (EditText)findViewById(R.id.editTextForLastName);

        //editTextId = (EditText)findViewById(R.id.edittextid);
        addButton = (Button)findViewById(R.id.buttonForAddData);
        viewAllButton = (Button)findViewById(R.id.buttonForViewAll);

        AddData();
        viewAllData();


    }


    //creating a method AddData to add first name and last name..
    public  void AddData() {
        //setting onClickListener on addButton..
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = dbHelper.insertData(first_Name.getText().toString(),
                                last_Name.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    /**
     * creating a viewAllData method to view all the data we have insert in it.
     * and in the viewAllButton setting onClickListener..
     */
    public void viewAllData() {
        viewAllButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = dbHelper.getAllData();
                        if(res.getCount() == 0) {
                            // show this message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuilder buffer = new StringBuilder();
                        while (res.moveToNext()) {
                            buffer.append("Id :").append(res.getString(0)).append("\n");
                            buffer.append("First Name :").append(res.getString(1)).append("\n");
                            buffer.append("Last Name :").append(res.getString(2)).append("\n");

                        }

                        // Show all the save data
                        showMessage("Show Insert Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
            return true;
        }


    }

