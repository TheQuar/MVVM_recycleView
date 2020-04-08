package com.quar.mvvmrecycleview.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {
@FormUrlEncoded
    @POST("myadv")
    Call<ResponseBody> GetPassangerBlank(
            @Field("userid") String userid
    );

    @POST("adv-passanger")
    Call<ResponseBody> GetDriveBlank(
            @Field("test") String test
    );
}
