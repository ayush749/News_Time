package ayush.abes.newsretrofit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ayush.abes.newsretrofit.NewsArticle;
import ayush.abes.newsretrofit.Model.NewsModel;
import ayush.abes.newsretrofit.R;

/**
 * Created by Ayush on 5/9/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    ArrayList<NewsModel> newsModelArrayList = new ArrayList<NewsModel>();

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new NewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.setData(newsModelArrayList.get(position));
        NewsModel newsModel = newsModelArrayList.get(position);
        holder.tv_title.setText(newsModel.getName());
    }

    @Override
    public int getItemCount() {
        Log.d("ayush", String.valueOf(newsModelArrayList.size()));
        return newsModelArrayList.size();
    }


    public void getNewsList(ArrayList<NewsModel> newsResponseArrayList) {
        this.newsModelArrayList = newsResponseArrayList;
        notifyDataSetChanged();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        ImageView im_title;
        String id, name, description, url, category, language;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.title);
            im_title = (ImageView) itemView.findViewById(R.id.title_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent articleIntent = new Intent(itemView.getContext(), NewsArticle.class);
                    articleIntent.putExtra("id",id);
                    articleIntent.putExtra("name",name);
                    articleIntent.putExtra("description",description);
                    articleIntent.putExtra("url",url);
                    articleIntent.putExtra("category",category);
                    articleIntent.putExtra("language",language);
                    itemView.getContext().startActivity(articleIntent);
                }
            });

        }

        public void setData(NewsModel articlemodel) {
            this.id = articlemodel.getId();
            this.name=articlemodel.getName();
            this.description=articlemodel.getDescription();
            this.url=articlemodel.getUrl();
            this.category=articlemodel.getCategory();
            this.language=articlemodel.getLanguage();

        }
    }

}

