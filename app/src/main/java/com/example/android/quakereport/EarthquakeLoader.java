package com.example.android.quakereport;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader <List<EarthQuake>> {


    /** Query URL */
    private String urls;

    private static final String LOG_TAG = EarthquakeLoader.class.getName();



    public EarthquakeLoader(Context context, String url) {
        super(context);
        urls=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.i(LOG_TAG,"From OnStartLoading() Method");
    }


    @Nullable
    @Override
    public List<EarthQuake> loadInBackground() {

        Log.i(LOG_TAG,"From LoadInbackground() Method");
        if (urls == null) {
            return null;
        }

        List<EarthQuake> result = QueryUtils.fetchEarthquakeData(urls);
        return result;
    }
}
