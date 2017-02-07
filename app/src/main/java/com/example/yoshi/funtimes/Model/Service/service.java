package com.example.yoshi.funtimes.Model.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.yoshi.funtimes.Model.Backend.IDataSource;
import com.example.yoshi.funtimes.Model.Backend.ManagerFactory;

//this is our Service which checks every 4 sec if there are any changes in our DB

public class service extends Service {
    private final IDataSource db;
    private final int timeToSleep = 4000;
    private final Thread background;

    public service() {
        db = ManagerFactory.getDB();

        background = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    checkForChange();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    stopCheck();
                }
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        background.start();
        return START_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();
        stopCheck();
    }

    private boolean running = true;

    /*

     */
    private void checkForChange() throws InterruptedException {
        while (running) {
            if (db.newAction() || db.newBusiness()) {
                broadcastIntent();
            }
            Log.d("service: ", "running");
            Thread.sleep(timeToSleep);
        }
    }

    private void broadcastIntent() {
        Intent intent = new Intent();
        intent.setAction("com.project.CHECK_DATABASE");
        sendBroadcast(intent);
        Log.d("service: ", "broadcast sent");
    }

    private void stopCheck() {
        running = false;
    }
}

