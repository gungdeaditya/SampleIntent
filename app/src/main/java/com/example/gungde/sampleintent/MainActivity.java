package com.example.gungde.sampleintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnExplicit1, btnExplicit2, btnImplicit1, btnImplicit2, btnImplicit3, btnImplicit4;
    EditText edtInput1, edtInput2;
    Bitmap bitmap;
    ImageView img;
    Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExplicit1 = (Button)findViewById(R.id.btn_explicit1);
        btnExplicit2 = (Button)findViewById(R.id.btn_explicit2);
        btnImplicit1 = (Button)findViewById(R.id.btn_implicit1);
        btnImplicit2 = (Button)findViewById(R.id.btn_implicit2);
        btnImplicit3 = (Button)findViewById(R.id.btn_implicit3);
        btnImplicit4 = (Button)findViewById(R.id.btn_implicit4);
        edtInput1 = (EditText) findViewById(R.id.edt_input1);
        edtInput2 = (EditText)findViewById(R.id.edt_input2);
        img = (ImageView) findViewById(R.id.image);

        btnExplicit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

        btnExplicit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                String data = edtInput1.getText().toString();
                i.putExtra("key", data);
                startActivity(i);
            }
        });

        btnImplicit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.codemargonda.com"));
                startActivity(i);
            }
        });

        btnImplicit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:085772136367"));
                startActivity(i);
            }
        });

        btnImplicit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                String data = edtInput2.getText().toString();
                i.putExtra(Intent.EXTRA_TEXT,data);
                i.setType("text/plain");
                startActivity(i);
            }
        });

        btnImplicit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}