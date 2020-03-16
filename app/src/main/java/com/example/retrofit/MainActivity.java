package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPost;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPost=findViewById(R.id.btnPost);
        tv=findViewById(R.id.tv);

        btnPost.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPost:
                Postsend();
                break;
        }
    }


    void Postsend(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置网络请求baseUrl
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Api request = retrofit.create(Api.class);
        Call<Data> call = request.postDataCall("i love you","json");
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                Log.e("Test", "数据：" + new Gson().toJson(response.body().getTranslateResult().get(0).get(0).getTgt()));
                Toast.makeText(MainActivity.this, "post回调成功:异步执行", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("Test", "post回调失败：" + t.getMessage() + "," + t.toString());
                Toast.makeText(MainActivity.this, "post回调失败", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
