package ayush.abes.newsretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDescription extends AppCompatActivity {
  TextView author,description,url,publishedAt;
    ImageView news_image;
    String urlString;
    String titleString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_description);
        news_image=(ImageView)findViewById(R.id.news_image);
        author=(TextView)findViewById(R.id.author);
        description=(TextView)findViewById(R.id.desc);
        url=(TextView)findViewById(R.id.url);
        publishedAt=(TextView)findViewById(R.id.date);
        Bundle getNewsBundle=getIntent().getExtras();
        if(getNewsBundle!=null){
            titleString=getNewsBundle.getString("title_news");
            String authorString = getNewsBundle.getString("news_author");
            String descriptionString = getNewsBundle.getString("description_news");
            urlString = getNewsBundle.getString("news_url");
            String publishedAtString = getNewsBundle.getString("news_publishedAt");
            String imageUrlString = getNewsBundle.getString("news_urlToImage");
            if(getSupportActionBar()!=null){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }

            author.setText(authorString);
            description.setText(descriptionString);
            publishedAt.setText(publishedAtString);
            getSupportActionBar().setTitle(titleString);
            Picasso.with(NewsDescription.this)
                    .load(imageUrlString)
                    .centerCrop()
                    .resize(150,150)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(news_image);

                    }
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(NewsDescription.this,WebActivity.class);
                webIntent.putExtra("url",urlString);
                webIntent.putExtra("title",titleString);
                startActivity(webIntent);
            }
        });
    }

}
