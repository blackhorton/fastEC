package com.ming.latte.net;

import com.ming.latte.app.ConfigKeys;
import com.ming.latte.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author Hortons
 * on 2019/7/24
 */

/**
 * 负责创建App的全局变量
 */

public class RestCreator {

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getSRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * 创建一个全局的WeakHashMap参数，避免多次创建
     * 通过静态内部类的方式可以实现变量的惰性加载
     */
//    public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    /**
     * Retrofit全局实例只需要有一个即可
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = Latte.getConfiguration(ConfigKeys.API_HOST);
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

        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        //获取拦截器的数组
        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        //TODO 此处应该可以优化
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

        //通过循环将拦截器传入到okHttp中,初始化拦截器
        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
    }

    private static final class RestServiceHolder {
        //TODO 查看文档学习
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }
}
