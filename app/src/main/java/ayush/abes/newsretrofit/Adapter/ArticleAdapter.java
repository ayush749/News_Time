package ayush.abes.newsretrofit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ayush.abes.newsretrofit.Model.ArticleModel;
import ayush.abes.newsretrofit.Model.NewsModel;
import ayush.abes.newsretrofit.NewsArticle;
import ayush.abes.newsretrofit.NewsDescription;
import ayush.abes.newsretrofit.R;

import static ayush.abes.newsretrofit.R.id.url;

/**
 * Created by Ayush on 5/9/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    ArrayList<ArticleModel> articleModelArrayList=new ArrayList<ArticleModel>();
    Context context;
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        this.context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.article_layout, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.setData(articleModelArrayList.get(position));
        ArticleModel articleModel = articleModelArrayList.get(position);
        holder.title.setText(articleModel.getTitle());
        Picasso.with(context)
                .load(articleModel.getUrlToImage())
                .resize(150,150)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.title_image);
    }

    @Override
    public int getItemCount() {
        Log.d("ayush", String.valueOf(articleModelArrayList.size()));
        return articleModelArrayList.size();
    }
    public void getArticleList(ArrayList<ArticleModel> articleResponseArrayList) {
        this.articleModelArrayList = articleResponseArrayList;
        notifyDataSetChanged();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        String news_author,news_title,news_description,news_url,news_urlToImage,news_publishedAt;
        TextView title;
        ImageView title_image;
        public ArticleViewHolder(final View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            title_image=(ImageView)itemView.findViewById(R.id.title_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent fullarticleIntent = new Intent(itemView.getContext(), NewsDescription.class);
                    fullarticleIntent.putExtra("news_author",news_author);
                    fullarticleIntent.putExtra("title_news",news_title);
                    fullarticleIntent.putExtra("description_news",news_description);
                    fullarticleIntent.putExtra("news_url",news_url);
                    fullarticleIntent.putExtra("news_urlToImage",news_urlToImage);
                    fullarticleIntent.putExtra("news_publishedAt",news_publishedAt);
                    itemView.getContext().startActivity(fullarticleIntent);
                }
            });
        }
        public void setData(ArticleModel articlemodel) {
            this.news_author = articlemodel.getAuthor();
            this.news_title=articlemodel.getTitle();
            this.news_description=articlemodel.getDescription();
            this.news_url=articlemodel.getUrl();
            this.news_urlToImage=articlemodel.getUrlToImage();
            this.news_publishedAt=articlemodel.getPublisehdAt();

        }
    }
}
