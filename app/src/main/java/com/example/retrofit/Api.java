package com.example.retrofit;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @auther: lijunjie
 * @createDate: 2020/3/12  19:32
 * @purpose：
 */
public interface Api {
    @FormUrlEncoded
    @POST("translate?")
    Call<Data>postDataCall(
      @Field("i") String transword,
      @Field("doctype") String json
    );
}
