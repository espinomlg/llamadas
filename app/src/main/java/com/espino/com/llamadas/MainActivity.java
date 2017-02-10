package com.espino.com.llamadas;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private TextView empty;
    private LlamadasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(android.R.id.list);
        empty = (TextView) findViewById(android.R.id.empty);
    }

    public void setAdapter() {
        Cursor cur = null;
        String[] projection = new String[]{
                CallLog.Calls.TYPE,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION
        };
        Uri llamadas = CallLog.Calls.CONTENT_URI;
        ContentResolver cr = getContentResolver();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED)
            cur = cr.query(llamadas, projection, null, null, null);

        adapter = new LlamadasAdapter(MainActivity.this, cur);
        lista.setAdapter(adapter);

    }
}
