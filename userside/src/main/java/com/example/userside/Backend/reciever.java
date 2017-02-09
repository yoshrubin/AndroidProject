package com.example.userside.Backend;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.userside.Backend.Factory.Backend;
import com.example.userside.Backend.Factory.BackendFactory;
import com.example.userside.Model.secondAppActivity;
import com.example.userside.R;

import static android.content.Context.NOTIFICATION_SERVICE;

//this our broadcast receiver

public class reciever extends BroadcastReceiver {
    private Backend db;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.d("second app: ", "broadcast received");
        db = BackendFactory.getFactoryDatabase();
        db.setUpDatabase();
        addNotification(context);
    }

    private void addNotification(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launch);
        builder.setContentTitle("New Agency or Trip Added!");
        builder.setContentText("Come Check Out New Adventures!");
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(1,builder.build());

    }
}