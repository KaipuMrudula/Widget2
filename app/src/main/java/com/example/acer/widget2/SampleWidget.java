package com.example.acer.widget2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

public class SampleWidget extends AppWidgetProvider {
    SharedPreferences preferences;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int myWidgetId:appWidgetIds){
            preferences=context.getSharedPreferences(MainActivity.NAME,Context.MODE_PRIVATE);
            String s=preferences.getString("chinnu","no data found");

            Intent intent=new Intent(context,MainActivity.class);
            PendingIntent pi=PendingIntent.getActivity(context,1,intent,0);
            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.design);
            views.setTextViewText(R.id.widgettext,s);
            views.setOnClickPendingIntent(R.id.widgettext,pi);
            appWidgetManager.updateAppWidget(myWidgetId,views);
        }
    }
}
