package com.lb.launcher.test;

import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.lb.launcher.http.OkHttpClientManager;
import com.lb.launcher.http.OkHttpClientManager.Param;
import com.squareup.okhttp.Request;

import java.io.File;
import java.util.List;

/**
 * http 网络请求测试
 * Created by ferris on 2015/9/25.
 */
public class OkHttpTest {

    //异步网络post请求
    public void post() {
        Param[] param = new Param[]{new Param("username", "zhy"),
                new Param("password", "123")};
        OkHttpClientManager.postAsyn("http://192.168.56.1:8080/okHttpServer/user!getSimpleString", param, new OkHttpClientManager.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Object response) {

            }
        });
    }

    //异步网络post请求
    public void get() {
        OkHttpClientManager.getAsyn("http://192.168.56.1:8080/okHttpServer/user!getSimpleString", new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String u) {

            }
        });

        OkHttpClientManager.getAsyn("http://192.168.56.1:8080/okHttpServer/user!getUsers",
                new OkHttpClientManager.ResultCallback<List<User>>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(List<User> us) {

                    }
                });
    }

    //同步网络post请求
    public void postSync() {
//        OkHttpClientManager.post(String url, Object tag, Param... params);
//        OkHttpClientManager.postAsString(String url, Object tag, Param... params)
    }

    //同步网络get请求
    public void getSync() {
//        OkHttpClientManager.getAsString(String url, Object tag);
    }

    //显示图片
    public void showImage(ImageView mImageView){
        OkHttpClientManager.getDisplayImageDelegate()
                .displayImage(mImageView,
                        "http://images.csdn.net/20150817/1.jpg");
    }

    //基于HTTP POST的大文件上传（可包一般键值对）
    public void postFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "test1.txt");
        if (!file.exists()) {
            return;
        }

        Param[] param = new Param[]{new Param("username", "zhy"),
                new Param("password", "123")};


        OkHttpClientManager.getUploadDelegate().postAsyn("http://192.168.1.103:8080/okHttpServer/fileUpload", "mFile", file, param, new OkHttpClientManager.ResultCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Object response) {

            }
        }, "Tag");

    }

    public void BigFileDownload(){
        OkHttpClientManager.getDownloadDelegate().downloadAsyn(
                "url",
                Environment.getExternalStorageDirectory().getAbsolutePath(),
                new OkHttpClientManager.ResultCallback<String>()
                {
                    @Override
                    public void onError(Request request, Exception e)
                    {
                    }

                    @Override
                    public void onResponse(String response)
                    {
//                        Toast.makeText(MainActivity.this, response + "下载成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

//kHttpClientManager.cancelTag(tag)取消TAG
}
