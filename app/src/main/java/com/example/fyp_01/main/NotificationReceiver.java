package com.example.fyp_01.main;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import com.example.fyp_01.R;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String NOTIFICATION_CHANNEL_ID = "FYP";

    @Override
    public void onReceive(Context context, Intent intent) {
        intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent resultPendingIntent = PendingIntent.getBroadcast(context, 7, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("FYP")
                .setContentText("Don't forget to get active!")
                .setAutoCancel(true)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        builder.setContentIntent(resultPendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "FYPChannel";
            String description = "Channel for FYP";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance);
            channel.enableVibration(true);
            channel.setDescription(description);
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(7, builder.build());
    }
}
