package com.example.userside.Backend;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.userside.Model.MainActivity;
import com.example.userside.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by yoshi on 1/4/17.
 */


public class reciever extends BroadcastReceiver {
    public reciever() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"Intent Received",Toast.LENGTH_SHORT).show();
        Log.d("second app: ", "broadcast received");
        Intent intnt = new Intent(context,MainActivity.class);
        intnt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(intnt);
        addNotification(context);
    }

    private void addNotification(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launch);
        builder.setContentTitle("New Attraction/Business Added !");
        builder.setContentText("The Database have changed , need to sync");
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(1,builder.build());

    }
}