package com.example.datamahasiswa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMhs extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DBHelper mydb;

    TextView nomhs;
    TextView phone;
    TextView nama;

    int id_To_Update = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_mhs);
        nomhs = (TextView) findViewById(R.id.editTextNim);
        nama = (TextView) findViewById(R.id.editTextName);
        phone = (TextView) findViewById(R.id.editTextPhone);

        Button b = (Button) findViewById(R.id.button1);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");

            if (Value > 0) {
                //means this is the view part not the add contact part.

                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                final String no = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_NIM));
                final String nam = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_NAMA));
                final String phon = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_PHONE));
                if (!rs.isClosed()) {
                    rs.close();
                }

                b.setVisibility(View.INVISIBLE);

                nomhs.setText((CharSequence) no);
                nomhs.setFocusable(false);
                nomhs.setClickable(false);

                nama.setText((CharSequence) nam);
                nama.setFocusable(false);
                nama.setClickable(false);

                phone.setText((CharSequence) phon);
                phone.setFocusable(false);
                phone.setClickable(false);

            }
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.insertContact(
                        nomhs.getText().toString(),
                        nama.getText().toString(),
                        phone.getText().toString()
                );
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.menu_display, menu);
            } else {
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        }
        return true;
    }

//    public void run(View view, String nim, String nama, String noHp) {
//
//        mydb.insertContact(noHp, nama, noHp);
//
//    }
}