package three.googlers.refernceportal;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import three.googlers.refernceportal.data.PortalDbHelper;

public class Login extends Activity
        //implements OnClickListener, OnItemClickListener
{
    PortalDbHelper dbHelper;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ListView lv1=(ListView)findViewById(R.id.listView10);
        ArrayList<Level> lv=new ArrayList<Level>();
        Intent in = getIntent();
        String ngo = in.getStringExtra(LoginFragment.TAG_NAME);
        TextView tv = (TextView) findViewById(R.id.tvLogin);
        tv.setText(ngo);
        dbHelper = new PortalDbHelper(this);
        Cursor c = dbHelper.getRecordsByNgo(ngo);
        if(c.moveToFirst()){
            int i=0;
            do{
                lv.add(i,new Level(c.getString(c.getColumnIndex(PortalDbHelper.COLUMN_NAME)),
                        c.getString(c.getColumnIndex(PortalDbHelper.COLUMN_ADDRESS)),
                        c.getString(c.getColumnIndex(PortalDbHelper.COLUMN_NGO)),
                        c.getString(c.getColumnIndex(PortalDbHelper.COLUMN_PHONE))));
                i++;
            }while(c.moveToNext());
        }
        LevelAdapter adp=new LevelAdapter(this, R.layout.singleview1, lv);
        lv1.setAdapter(adp);
    }
}
