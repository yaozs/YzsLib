package com.yzs.wanandroidapp.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description:
 * Date: 2018/4/24
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
//        Request request = chain.request();
//
//        long t1 = System.nanoTime();
//        logger.info(String.format("Sending request %s on %s%n%s",
//                request.url(), chain.connection(), request.headers()));
//
//        Response response = chain.proceed(request);
//
//        long t2 = System.nanoTime();
//        logger.info(String.format("Received response for %s in %.1fms%n%s",
//                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return null;
    }
}
