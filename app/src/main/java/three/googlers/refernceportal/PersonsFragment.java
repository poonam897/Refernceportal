package three.googlers.refernceportal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import three.googlers.refernceportal.data.PortalDbHelper;


public class PersonsFragment extends Fragment {

    private PortalDbHelper dbHelper;
    public PersonsFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_persons, container, false);
        ArrayList<Level> lv=new ArrayList<Level>();
        dbHelper = new PortalDbHelper(getActivity());
        Cursor c = dbHelper.getAllRecords(PersonsActivity.table);
        if(c.moveToFirst()){
            int x=0;
            do{
                lv.add(x,new Level(c.getString(c.getColumnIndex(PortalDbHelper.COLUMN_NAME)),
                        c.getString(c.getColumnIndex(PortalDbHelper.COLUMN_ADDRESS)),
                        c.getString(c.getColumnIndex(PortalDbHelper.COLUMN_NGO)),
                        c.getString(c.getColumnIndex(PortalDbHelper.COLUMN_PHONE))));
                x++;
            }while(c.moveToNext());
        }
        LevelAdapter adp=new LevelAdapter(getActivity(), R.layout.singleview1, lv);
        ListView lv1=(ListView) rootView.findViewById(R.id.listView_persons);
        lv1.setAdapter(adp);
        return rootView;
    }
}
