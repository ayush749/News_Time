package ayush.abes.newsretrofit;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import ayush.abes.newsretrofit.Adapter.NewsAdapter;
import ayush.abes.newsretrofit.Client.ApiClient;
import ayush.abes.newsretrofit.Client.ArticleClient;
import ayush.abes.newsretrofit.Model.NewsModel;
import ayush.abes.newsretrofit.Response.ArticleResponse;
import ayush.abes.newsretrofit.Response.NewsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<NewsResponse> {
    private final static String API_KEY = "ffc5b1e2a84b445aa7d31d4ad0975a85";
    private final static String Category = "";
    private final static String language = "";
    private final static String country = "";
    private ProgressDialog dialog;
    NewsResponse newsResponse;
    RecyclerView recyclerView;
    NewsAdapter newsadapter;
    String status;
    private static MainActivity mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isInternetOn();


        isNetworkAvailable();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...Please Wait");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        newsadapter = new NewsAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_View);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(newsadapter);

        dialog.show();
        Call<NewsResponse> call = ArticleClient.getClent().getNewsChannel("","","");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
        if (response.isSuccessful()) {
            newsResponse = response.body();
            newsadapter.getNewsList((ArrayList<NewsModel>) newsResponse.source);
            status = newsResponse.getStatus();
            dialog.dismiss();
            Log.d("ayush", status);
            Log.d("ayush", newsResponse.source.get(0).getDescription());
        } else {
            Toast.makeText(this, "failed to load", Toast.LENGTH_SHORT).show();
            Log.d("ayush", "failure");

        }
    }

    @Override
    public void onFailure(Call<NewsResponse> call, Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        Log.d("ayush", "failur");

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            final ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            final android.net.NetworkInfo wifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            final android.net.NetworkInfo mobile = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi.isAvailable() || mobile.isAvailable()) {
                // Do something

                Log.d("Network Available ", "Flag No 1");
            }
        }

    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

             Call<NewsResponse> call = ApiClient.getClent().getNewsChannel("", "", "");
            call.enqueue(this);


            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Intent errorintent = new Intent(MainActivity.this,ErrorActivity.class);
            startActivity(errorintent);
            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("no", null).show();
    }
}
