package be.ehb.LoginMockup.ui.drinkwater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import be.ehb.Ehealth.R;


public class DrinkWaterReminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_water_reminder);
        createNotificationChannel();

        Button button = findViewById(R.id.button);
        int currentTime = Calendar.getInstance().getTime().getHours();
        System.out.println(currentTime);


        button.setOnClickListener(v -> {

            Intent intent = new Intent(DrinkWaterReminder.this, ReminderBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(DrinkWaterReminder.this, 0, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            long timeAtButtonClick = System.currentTimeMillis();

            long tweeUur =  2; // 2uur


            if(!(currentTime > 22 || currentTime < 6)) {

                Toast.makeText(this, "Reminder Set", Toast.LENGTH_LONG).show();

                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        timeAtButtonClick + tweeUur,
                        pendingIntent);
            }



        });

    }


    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notifyDrinkChannel";
            String description = "Channel for drink water reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyDrink", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
