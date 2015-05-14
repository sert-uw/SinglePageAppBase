package com.example.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.*;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends ActionBarActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                String type = message.substring(0, message.indexOf("/"));
                String body = message.substring(message.indexOf("/") + 1);

                if (type.equals("Toast")) {
                    try {
                        Toast.makeText(MainActivity.this, body, Toast.LENGTH_SHORT).show();
                        return true;
                    } finally {
                        result.confirm();
                    }
                }

                else if(type.equals("Dialog")){
                    try{
                          String[] params = parseForDialog(body);

                          if(params == null)
                              return true;

                          MyDialogFragment dialog = MyDialogFragment.newInstance(params);
                          dialog.setDialogListener(dialogListener);
                          dialog.show(MainActivity.this.getFragmentManager(), "dialog");

                          return true;
                    } finally {
                        result.confirm();
                    }
                }

                else
                    return false;
            }
        });

        webView.loadUrl("file:///android_asset/index.html");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static final int PARAM_NUM = 8;
    private String[] parseForDialog(String body) {
        String[] params = new String[PARAM_NUM];

        for(int i=0; i<PARAM_NUM - 1; i++) {
            int index = body.indexOf("/");
            if(index == -1)
                return null;

            params[i] = body.substring(0, index);
            body = body.substring(index + 1);
        }

        params[PARAM_NUM - 1] = body.substring(body.indexOf("/") + 1);

        return params;
    }

    private DialogListener dialogListener = new DialogListener() {
        @Override
        public void doClick(String js){
            if(js.equals("") || webView == null)
                return;

            webView.loadUrl("javascript:" + js);
        }
    };
}
