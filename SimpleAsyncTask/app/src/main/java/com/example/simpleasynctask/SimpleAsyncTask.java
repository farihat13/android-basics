package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar progressBar) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;
        int chunks = 10;
        try {
            chunks = (int) (s / (float) chunks);
            for (int i = 0; i < 10; i++) {
                Thread.sleep(chunks);
                publishProgress((int) ((i + 1) * chunks * 100.0 / s));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }


    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setProgress(values[0]);
    }
}
