package ayush.abes.newsretrofit.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ayush on 5/8/2017.
 */

public class NewsModel {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("category")
    private String category;
    @SerializedName("language")
    private String language;



    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public String getUrl() {
        return url;
    }


    public String getCategory() {
        return category;
    }


    public String getLanguage() {
        return language;
    }
}
