package three.googlers.refernceportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import three.googlers.refernceportal.data.PortalDbHelper;

public class SearchFragment extends Activity {
    public static final String NAME="name";
	 public void onCreate(Bundle savedInstanceState)
     {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.search);
            final ListView listView = (ListView) findViewById(R.id.listView1);
             
             String[] values = new String[] { "Servant",
                                              "Plumber",
                                              "Electrician",
                                              "Gardner",
                                              "Watchman",

                                             };
     
             ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
               android.R.layout.simple_list_item_1, android.R.id.text1, values);
     
     
             // Assign adapter to ListView
             listView.setAdapter(adapter); 
             
             // ListView Item Click Listener
             listView.setOnItemClickListener(new OnItemClickListener() {
  
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view,
                      int position, long id) {
                     
                    // ListView Clicked item index
                    int itemPosition     = position;
                    
                   switch (itemPosition) {
				case 0:
					Intent i = new Intent(SearchFragment.this,PersonsActivity.class);
                    i.putExtra(NAME, PortalDbHelper.TABLE_MAID);
					startActivity(i);
					break;
				case 1:
					Intent j = new Intent(SearchFragment.this,PersonsActivity.class);
                    j.putExtra(NAME, PortalDbHelper.TABLE_PLUMBER);
					startActivity(j);
					break;
				case 2:
					Intent k = new Intent(SearchFragment.this,PersonsActivity.class);
                    k.putExtra(NAME, PortalDbHelper.TABLE_ELECTRICIAN);
					startActivity(k);
					break;
				case 3:
					Intent l = new Intent(SearchFragment.this,PersonsActivity.class);
                    l.putExtra(NAME, PortalDbHelper.TABLE_GARDENER);
					startActivity(l);
					break;
				case 4:
					Intent m = new Intent(SearchFragment.this,PersonsActivity.class);
                    m.putExtra(NAME, PortalDbHelper.TABLE_WATCHMAN);
					startActivity(m);
					break;

				}
                   }
     
              }); 
         }
     
     
     }


