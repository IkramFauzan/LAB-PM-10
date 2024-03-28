package com.example.tugaspraktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    private TextView textViewNama;
    private TextView textViewUname;
    private TextView textViewTitle;
    private TextView textViewContent;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textViewNama = findViewById(R.id.tvNama);
        textViewUname = findViewById(R.id.tvUname);
        textViewTitle = findViewById(R.id.tvTitle);
        textViewContent = findViewById(R.id.tvContent);
        image = findViewById(R.id.img);

        String text1 = getIntent().getStringExtra("textFromEtName");
        String text2 = getIntent().getStringExtra("textFromEtUname");
        String text3 = getIntent().getStringExtra("textFromEtNote1");
        String text4 = getIntent().getStringExtra("textFromEtNote2");
        String imageUriString = getIntent().getStringExtra("imageUri");

        textViewNama.setText(text1);
        textViewUname.setText(text2);
        textViewTitle.setText(text3);
        textViewContent.setText(text4);
        Uri imageUri = Uri.parse(imageUriString);
        image.setImageURI(imageUri);
    }
}