package com.liuguangqiang.idaily.ui.act;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.liuguangqiang.idaily.R;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        initViews();
    }

    private void initViews() {
        final ImageView ivShare = (ImageView) findViewById(R.id.iv_share);
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TextActivity.this, OtherActivity.class);
                if (Build.VERSION.SDK_INT >= 210) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(TextActivity.this, ivShare, "share1");
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
    }

}
