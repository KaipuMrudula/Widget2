package com.example.acer.widget2;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String NAME="com.example.acer.widget2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.list);
        String[] s=getResources().getStringArray(R.array.names);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,s);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemname=parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, ""+itemname, Toast.LENGTH_SHORT).show();
                sp=getSharedPreferences(NAME,MODE_PRIVATE);
                editor=sp.edit();
                StringBuffer buffer=new StringBuffer();
                buffer.append(itemname);
                editor.putString("chinnu",buffer.toString());
                editor.apply();
                Intent i=new Intent(MainActivity.this,SampleWidget.class);
                i.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

                int[] ids=AppWidgetManager.getInstance(MainActivity.this).getAppWidgetIds(new ComponentName(getApplicationContext(),SampleWidget.class));
                i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
                sendBroadcast(i);
            }
        });
    }
}
