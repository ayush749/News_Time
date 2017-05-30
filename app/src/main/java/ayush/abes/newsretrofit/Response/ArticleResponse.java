package ayush.abes.newsretrofit.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ayush.abes.newsretrofit.Model.ArticleModel;
import ayush.abes.newsretrofit.Model.NewsModel;

/**
 * Created by Ayush on 5/9/2017.
 */

public class ArticleResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("source")
    private String source;
    @SerializedName("sortBy")
    private String sortBy;
    @SerializedName("articles")
    public ArrayList<ArticleModel> articles;

    public String getStatus(){
        return status;
    }

}
