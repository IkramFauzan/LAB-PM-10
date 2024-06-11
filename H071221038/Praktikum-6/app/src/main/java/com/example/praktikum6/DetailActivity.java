package com.example.praktikum6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

//    public static final String EXTRA_ALL_USERS = "allUsers";
//    static final String EXTRA_USER_ID = "id";

    private ApiService apiService;
    private ImageView avatarImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    private LinearLayout linearDetail;
    private ProgressBar detail_progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        apiService = RetrofitClient.getClient();
        avatarImageView = findViewById(R.id.avatarImageView);
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        detail_progressBar = findViewById(R.id.detail_progressBar);
        linearDetail = findViewById(R.id.linearDetail);

        detail_progressBar.setVisibility(View.VISIBLE);
        linearDetail.setVisibility(View.GONE);

        Intent intent = getIntent();
        int userId = intent.getIntExtra("user", -1);
        Call<SingleUserResponse> call = apiService.getUsersById(userId);
        call.enqueue(new Callback<SingleUserResponse>() {
            @Override
            public void onResponse(Call<SingleUserResponse> call, Response<SingleUserResponse> response) {
                if (response.isSuccessful()) {
                    detail_progressBar.setVisibility(View.GONE);
                    linearDetail.setVisibility(View.VISIBLE);

                    User user = response.body().getDataUser();
                    nameTextView.setText(user.getFirst_name() + " " + user.getLast_name());
                    emailTextView.setText(user.getEmail());
                    Picasso.get().load(user.getAvatar()).into(avatarImageView);

                }
            }
            @Override
            public void onFailure(Call<SingleUserResponse> call, Throwable t) {
            }
        });
    }
}
