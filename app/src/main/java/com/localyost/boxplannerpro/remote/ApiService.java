package com.localyost.boxplannerpro.remote;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {

    @GET("breeze/Auth/IsLoggedIn")
    Call<ResponseBody> isUserLoggedIn(@Header("Cookie") String authToken);
}
