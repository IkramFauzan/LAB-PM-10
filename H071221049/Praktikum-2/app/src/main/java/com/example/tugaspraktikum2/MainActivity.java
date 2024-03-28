package com.example.tugaspraktikum2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private EditText etName;
    private EditText etUname;
    private Button btnSubmit;

    private String textFromEtName;
    private String textFromEtUname;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.Nama);
        etUname = findViewById(R.id.Uname);
        image = findViewById(R.id.selectImage);
        btnSubmit = findViewById(R.id.btnSubmit);

        // memilih gambar dari aplikasi galeri
        image.setOnClickListener(view -> {
            Intent open = new Intent(Intent.ACTION_PICK);
            open.setType("image/*");
            launcherIntentGallery.launch(open); // menampilkan gambar yang sudah dipilih
        });

        btnSubmit.setOnClickListener(view -> {
            textFromEtName = etName.getText().toString().trim();
            textFromEtUname = etUname.getText().toString().trim();

            if (textFromEtName.isEmpty() || textFromEtUname.isEmpty()) {
                Toast.makeText(this, "Isi nama dan username terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else if (imageUri == null) {
                Toast.makeText(this, "Pilih gambar", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra("textFromEtName", textFromEtName);   // menambahkan data tambahan ke intent
                intent.putExtra("textFromEtUname", textFromEtUname); // dikirimkan ke MainActivity2
                intent.putExtra("imageUri", imageUri.toString());    // dikonversi sebelum dimasukkan ke dalam intent
                startActivity(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> launcherIntentGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) { // Perikasa apakah sudah memilih gambar
                    Intent data = result.getData();   //  Mendapatkan data
                    if (data != null) {               // Memastikan data yang diterima tidak null
                        imageUri = data.getData();    // Mendapatkan
                        image.setImageURI(imageUri);  // gambar yang dipilih, ditampilkan di imageview
                    }
                }
            });
}