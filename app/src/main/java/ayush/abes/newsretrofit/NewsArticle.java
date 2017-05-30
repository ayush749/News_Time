package ayush.abes.newsretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ayush.abes.newsretrofit.Adapter.ArticleAdapter;
import ayush.abes.newsretrofit.Adapter.NewsAdapter;
import ayush.abes.newsretrofit.Client.ApiClient;
import ayush.abes.newsretrofit.Client.ArticleClient;
import ayush.abes.newsretrofit.Model.ArticleModel;
import ayush.abes.newsretrofit.Model.NewsModel;
import ayush.abes.newsretrofit.Response.ArticleResponse;
import ayush.abes.newsretrofit.Response.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsArticle extends AppCompatActivity implements Callback<ArticleResponse> {
    private final static String API_KEY="ffc5b1e2a84b445aa7d31d4ad0975a85";
    ArticleResponse articleResponse;
    RecyclerView recyclerView;
    ArticleAdapter articleAdapter;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_article);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        articleAdapter=new ArticleAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(articleAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String id = bundle.getString("id");
            String name = bundle.getString("name");
            String description = bundle.getString("description");
            String url = bundle.getString("url");
            String category = bundle.getString("category");
            String language = bundle.getString("language");
//            id_text.setText(name);

            Call<ArticleResponse> call = ArticleClient.getClent().getNewsarticles(id, API_KEY, "");
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
        if(response.isSuccessful()) {
            articleResponse = response.body();
            articleAdapter.getArticleList((ArrayList<ArticleModel>) articleResponse.articles);
            status=articleResponse.getStatus();
            Log.d("ayush",status);
            Log.d("response",articleResponse.articles.get(0).getDescription());
        }
    }

    @Override
    public void onFailure(Call<ArticleResponse> call, Throwable t) {

    }
}

