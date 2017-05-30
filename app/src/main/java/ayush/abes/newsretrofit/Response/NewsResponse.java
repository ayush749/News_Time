package ayush.abes.newsretrofit.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import ayush.abes.newsretrofit.Model.NewsModel;

/**
 * Created by Ayush on 5/8/2017.
 */

public class NewsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("sources")
    public ArrayList<NewsModel> source;

    public String getStatus(){
        return status;
    }

}
