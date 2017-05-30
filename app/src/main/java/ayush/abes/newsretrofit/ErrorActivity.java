package ayush.abes.newsretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //    finish();
        Intent i =new Intent(ErrorActivity.this,MainActivity.class);
        startActivity(i);


    }
}

