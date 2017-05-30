package ayush.abes.newsretrofit.Client;

import ayush.abes.newsretrofit.Interface.ApiInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ayush on 5/9/2017.
 */

public class ArticleClient {
    public static final String BASE_URL=" https://newsapi.org/v1/";
    private static Retrofit retrofit = null;

    public static ApiInterface getClent(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder().
                    baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
