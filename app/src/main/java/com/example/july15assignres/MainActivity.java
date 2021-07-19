package com.example.july15assignres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText mEtUserId;
    private TextView mTvName;
    private TextView mTvyear;
    private TextView mTvcolor;
    private TextView mTvpantone;
    private Button mBtnCallApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mEtUserId=findViewById(R.id.etUserId);
        mTvName=findViewById(R.id.tvName);
        mTvpantone=findViewById(R.id.tvpantone);
        mTvyear=findViewById(R.id.tvyear);
        mTvcolor=findViewById(R.id.tvcolor);
        mBtnCallApi=findViewById(R.id.btnCallApi);

        mBtnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService apiService=Network.getInstance().create(com.example.july15assignres.apiService.class);
                int UserId=Integer.parseInt(mEtUserId.getText().toString());
                apiService.getUser(UserId).enqueue(new Callback<ResponseDTO>() {
                    @Override
                    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                        ResponseDTO responseDTO=response.body();
                        String name=responseDTO.getData().getName();
                        String color=responseDTO.getData().getColor();
                        int year=responseDTO.getData().getYear();
                        String pandome=responseDTO.getData().getPantoneValue();
                        mTvName.setText(name);
                        mTvcolor.setText(color);
                        mTvyear.setText(year + "");
                        mTvpantone.setText(pandome);
                    }

                    @Override
                    public void onFailure(Call<ResponseDTO> call, Throwable t) {

                    }
                });
            }
        });

    }


}