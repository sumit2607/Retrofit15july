package com.example.july15assignres;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface apiService {
    @GET("/api/unknown/{userId}")
    Call<ResponseDTO> getUser(@Path("userId") int userId);
}
