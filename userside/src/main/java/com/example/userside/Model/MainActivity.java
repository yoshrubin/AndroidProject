package com.example.userside.Model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.userside.R;
//DON'T PAY ATTENTION TO THIS ACTIVITY, IT IS NOT AN ACTUAL PART OF THE PROJECT.
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button broadcast = (Button) findViewById(R.id.button);
        broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcastIntent(broadcast);
            }
        });

    }

    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("com.project.CHECK_DATABASE");
        sendBroadcast(intent);
    }
}