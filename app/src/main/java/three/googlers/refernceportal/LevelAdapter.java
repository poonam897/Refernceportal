package three.googlers.refernceportal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LevelAdapter  extends ArrayAdapter <Level>{

Typeface tface;
	
    static Context context;
       static int layoutResourceId;   
        ArrayList<Level> data;

    public LevelAdapter(Context context, int layoutResourceId, ArrayList<Level> data) {
           super(context, layoutResourceId, data);
           this.layoutResourceId = layoutResourceId;
           this.context = context;
           this.data = data;
       }


       public long getItemId(int position) {
           return position;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           View row = convertView;
           WeatherHolder holder = null;
           if(row == null)
           {
               LayoutInflater inflater = ((Activity)context).getLayoutInflater();
               row = inflater.inflate(layoutResourceId, parent, false);
              //row.setMinimumHeight(200);
               holder = new WeatherHolder();
             // holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
               holder.txtTitle = (TextView)row.findViewById(R.id.textView1);
               holder.txtTitle2 = (TextView)row.findViewById(R.id.textView2);
               holder.txtTitle3 = (TextView)row.findViewById(R.id.textView3);
               holder.txtTitle4 = (TextView)row.findViewById(R.id.textView4);
               row.setTag(holder);
           }
           else
           {
               holder = (WeatherHolder)row.getTag();
           }

           Level weather = data.get(position);
           holder.txtTitle.setText(weather.title);
           holder.txtTitle2.setText(weather.title2);
           holder.txtTitle3.setText(weather.title3);
           holder.txtTitle4.setText(weather.title4);
           return row;
       }

       static class WeatherHolder
       {
        //   ImageView imgIcon;
           TextView txtTitle;
           TextView txtTitle2;
           TextView txtTitle3;
           TextView txtTitle4;
       //    ImageView imgIcon2;
       }

	

}
