package com.chootdev.folderstructure.services.api;

import android.content.Context;

import com.chootdev.folderstructure.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lakmal Weerasekara on 08/09/16.
 */
public class ServiceGenerator {

    private Context mContext;
    private static String mUserToken;
    private static String mAppToken;
    private static String mUrl;
    private static String APP_TOKEN = "apptoken";
    private static String USER_TOKEN = "usertoken";

    public ServiceGenerator(Context context) {
        mContext = context;
        mUrl = mContext.getString(R.string.API_URL);
        mAppToken = mContext.getString(R.string.APP_TOKEN);
    }

    public <T> T CreateService(Class<T> serviceClass) {
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC).create();
        OkHttpClient okHttpClient = getOkHttpInterceptor();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mUrl)
                .client(okHttpClient.newBuilder().connectTimeout(1, TimeUnit.MINUTES).readTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES).build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(serviceClass);
    }

    private OkHttpClient getOkHttpInterceptor() {
        return new OkHttpClient.Builder().addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader(APP_TOKEN, mAppToken)
                                .addHeader(USER_TOKEN, mUserToken)
                                .method(original.method(), original.body());

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    public APIInterface getApiInstance() {
        return new ServiceGenerator(mContext).CreateService(APIInterface.class);
    }

}
