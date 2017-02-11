package com.example.userside.Backend;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

import java.text.ParseException;

import static android.R.id.message;
import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.v7.appcompat.R.attr.title;

//this our broadcast receiver

public class reciever extends BroadcastReceiver {
    private Backend db;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.d("second app: ", "broadcast received");
        /*
        Intent intnt = new Intent(context,secondAppActivity.class);
        intnt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        */
        db = BackendFactory.getFactoryDatabase();
        try {
            db.setUpDatabase();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        addNotification(context);
    }

    private void addNotification(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent intnt = new Intent(context,secondAppActivity.class);
        //intnt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intnt.addFlags(Notification.FLAG_AUTO_CANCEL);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intnt, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setSmallIcon(R.mipmap.ic_launch);
        builder.setContentTitle("New Agency or Trip Added!");
        builder.setContentText("Come Check Out New Adventures!");
        builder.setContentIntent(contentIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(1,builder.build());
    }
}