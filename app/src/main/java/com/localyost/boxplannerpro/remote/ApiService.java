package com.localyost.boxplannerpro.remote;

import com.localyost.boxplannerpro.remote.data.CrossfitClass;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("breeze/Auth/IsLoggedIn")
    Call<ResponseBody> isUserLoggedIn(@Header("Cookie") String authToken);

    @POST("Account/Login")
    Call<ResponseBody> login(@Body LoginBody body);

    @GET("breeze/SpecificScheduleBreeze/GetSchedulesForMonth")
    Call<List<CrossfitClass>> getClasses(@Header("Cookie") String authToken, @QueryMap Map<String, String> queryParams);
}
