package com.example.chris.notificationtest2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // This method is called when we click the send notification button
    // This method will take the title and body text from the text boxes and generate and send a notification with that displays them
    public void onClickSendNotification(View view) {

        // Get the title and content
        String title = ((TextView) findViewById(R.id.tvSetTitle)).getText().toString();
        String body = ((TextView) findViewById(R.id.tvSetBodyText)).getText().toString();

        // Build the notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.notification_icon)
                        .setContentTitle(title)
                        .setContentText(body);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Send the notification.
        mNotificationManager.notify(9999, mBuilder.build());
    }

    // This method is called when we click the send with picture button
    // This method works similar to the other button method, but it will attach a picture for display in the motification
    public void onClickSendNotificationWithPicture(View view) {
        // Get the title and content
        String title = ((TextView) findViewById(R.id.tvSetTitle)).getText().toString();
        String body = ((TextView) findViewById(R.id.tvSetBodyText)).getText().toString();

        // Get the image we want to display
        Bitmap bitmapGalaxy = BitmapFactory.decodeResource(this.getResources(),R.mipmap.galaxy_image);

        // Build the notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.notification_icon)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setLargeIcon(bitmapGalaxy)
                        .setStyle(new android.support.v7.app.NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmapGalaxy));

        /*
        // Get the image we want to display
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.galaxy_image);
        android.support.v7.app.NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle().bigPicture(bitmap);
        s.setSummaryText("Summary Text for Galaxy Image");
        mBuilder.setStyle(s);
        */

        // Create our intent and stack builder and send the notification
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        //mBuilder.setAutoCancel(true);
        NotificationManager mNotiManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotiManager.notify(9998,mBuilder.build());
    }
}
