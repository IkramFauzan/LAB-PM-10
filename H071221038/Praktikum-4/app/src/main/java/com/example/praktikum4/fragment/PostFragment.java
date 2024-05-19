package com.example.praktikum4.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktikum4.Account;
import com.example.praktikum4.DataSource;
import com.example.praktikum4.R;

import java.util.ArrayList;


public class PostFragment extends Fragment {

    private TextView et_content;
    private ImageView iv_image;
    private Boolean cekImage = false;
    private Uri uriImage;
    private Button btn_post;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        et_content = view.findViewById(R.id.et_content);
        iv_image = view.findViewById(R.id.iv_image);
        btn_post = view.findViewById(R.id.btn_post);

        ActivityResultLauncher<Intent> intentLaunch = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                        uriImage = result.getData().getData();
                        iv_image.setImageURI(uriImage);
                        cekImage = true;

                    }
                });

        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toGalery = new Intent(Intent.ACTION_PICK);
                toGalery.setType("image/*");
                intentLaunch.launch(toGalery);
            }
        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_content.getText().toString().trim();

                if (content.isEmpty()) {
                    Toast.makeText(getActivity(), "Masukkan content terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else if (cekImage == false){
                    Toast.makeText(getActivity(), "Pilih Gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Berhasil memposting", Toast.LENGTH_SHORT).show();


                    ArrayList<Account> datas = new ArrayList<>();
                    datas.add(new Account("433", "433",R.drawable.pat33,0,content,uriImage));

                    HomeFragment homeFragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("accounts", datas);
                    homeFragment.setArguments(bundle);

                    FragmentManager fragmentManager = getParentFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, homeFragment, HomeFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        return view;
    }

}