package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;


public class DataAdapter extends ArrayAdapter<EarthQuake> {
    private static final String LOCATION_SEPARATOR = " of ";

    public DataAdapter(Activity context, ArrayList<EarthQuake> quake) {
        super(context,  0,quake);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View myView=convertView;
        if(myView == null) {
            myView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_view, parent, false);
        }

        EarthQuake quake=getItem(position);
        String primaryLocation;
        String locationOffset;

        //getting orignal location form json
        String originalLocation=quake.getmLocation();
       // logic to split the location into location and location offset

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }


        TextView magnitude=(TextView)myView.findViewById(R.id.magnitude);
        magnitude.setText(formaterMagnitude(quake.getmMagnitude()));

        TextView locationoffset=(TextView)myView.findViewById(R.id.locationoffset);
        locationoffset.setText(locationOffset);

        TextView location=(TextView)myView.findViewById(R.id.location);
        location.setText(primaryLocation);

        Date dateObject =new Date(quake.getmDate());
        TextView date=(TextView)myView.findViewById(R.id.date);
        String formattedDate =formatDate(dateObject);
        date.setText(formattedDate);

        TextView time=(TextView)myView.findViewById(R.id.time);
        String formattedtime =formatTime(dateObject);
        time.setText(formattedtime);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(quake.getmMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        return myView;
    }


    private int getMagnitudeColor(double magnitude) {

         int magnitudeColorResourceId;
            int magnitudeFloor = (int) Math.floor(magnitude);
            switch (magnitudeFloor) {
                case 0:
                case 1:
                    magnitudeColorResourceId = R.color.magnitude1;
                    break;
                case 2:
                    magnitudeColorResourceId = R.color.magnitude2;
                    break;
                case 3:
                    magnitudeColorResourceId = R.color.magnitude3;
                    break;
                case 4:
                    magnitudeColorResourceId = R.color.magnitude4;
                    break;
                case 5:
                    magnitudeColorResourceId = R.color.magnitude5;
                    break;
                case 6:
                    magnitudeColorResourceId = R.color.magnitude6;
                    break;
                case 7:
                    magnitudeColorResourceId = R.color.magnitude7;
                    break;
                case 8:
                    magnitudeColorResourceId = R.color.magnitude8;
                    break;
                case 9:
                    magnitudeColorResourceId = R.color.magnitude9;
                    break;
                default:
                    magnitudeColorResourceId = R.color.magnitude10plus;
                    break;
            }
            return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
        }



    private String formaterMagnitude(Double magnitude) {
        DecimalFormat magnitudeFormat =new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);

    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat timeFormat=new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
