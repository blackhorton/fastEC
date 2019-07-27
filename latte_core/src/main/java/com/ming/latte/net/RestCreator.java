package com.ming.latte.net;

import com.ming.latte.app.ConfigType;
import com.ming.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author Hortons
 * on 2019/7/24
 */


public class RestCreator {

    /**
     *  创建一个全局的WeakHashMap参数，避免多次创建
     *  通过静态内部类的方式可以实现变量的惰性加载
     */
//    public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private static final class ParamsHolder{
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getSRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * Retrofit全局实例只需要有一个即可
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                //传入host地址
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                //字符串转换器(Retrofit2)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * Okhttp惰性的初始化
     */
    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        //TODO 查看文档学习
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
