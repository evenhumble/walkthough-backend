package io.hedwig.tcexecutor.cicd.gitlab;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;

public class GitLabClient {

    private Retrofit retrofitClient;

    public static GitLabClient getClient(GitLabConfig config){

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor(chain -> {
            Request originReq = chain.request();
            Request request = originReq.newBuilder()
                    .header("Private-Token",config.getAccessToken())
                    .method(originReq.method(),originReq.body())
                    .build();
            return chain.proceed(request);
        });

        GitLabClient gitLabClient = new GitLabClient();
        gitLabClient.retrofitClient=new Retrofit.Builder()
                .baseUrl(config.getUrl())
                .client(httpClientBuilder.build())
                .build();
        return gitLabClient;

    }
}
