package com.example.finalexam_project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class Service extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendNotification(remoteMessage.getFrom(), remoteMessage.getNotification().getBody());
        sendNotification(remoteMessage.getNotification().getBody());
        NotificationModel notification = new NotificationModel(remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody());

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.insertNotification(notification);
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        CollectionReference notificationsRef = db.collection("Notifications");
//
//// Build the notification data
//        Map<String, Object> notificationData = new HashMap<>();
//        notificationData.put("title", remoteMessage.getNotification().getTitle());
//        notificationData.put("body", remoteMessage.getNotification().getBody());
//        notificationData.put("timestamp", new Date());
//
//        db.collection("Notifications")
//                .add(notificationData)
//                .addOnSuccessListener(documentReference -> {
//                    System.out.println("Notification history added with ID: " + documentReference.getId());
//                })
//                .addOnFailureListener(e -> {
//                    System.out.println("Error adding notification history");
//                });
    }
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_IMMUTABLE);
        String channelId = "fcm_default_channel";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_stat_name).setContentTitle("PT ABC")
                        .setContentText(messageBody).setAutoCancel(true)
                        .setSound(defaultSoundUri).setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }
    }

    private void sendNotification(String from, String body) {
        new Handler(Looper.getMainLooper()).post(new Runnable(){
            @Override
            public void run() {
                Toast.makeText(Service.this.getApplicationContext(), from + " -> " + body, Toast.LENGTH_SHORT);
            }
        });
    }
}
