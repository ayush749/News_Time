package ayush.abes.newsretrofit.Interface;

import ayush.abes.newsretrofit.Response.ArticleResponse;
import ayush.abes.newsretrofit.Response.NewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ayush on 5/8/2017.
 */

public interface ApiInterface {
    @GET("sources")
    public Call<NewsResponse> getNewsChannel(@Query("category") String category, @Query("language") String language, @Query("country") String country);

    @GET("articles")
    Call<ArticleResponse> getNewsarticles(@Query("source") String source, @Query("apiKey") String apiKey, @Query("sortBy") String sortBy);
}
