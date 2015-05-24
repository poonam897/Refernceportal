package three.googlers.refernceportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class PersonsActivity extends ActionBarActivity {
    public static String table;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(SearchFragment.NAME,table);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);
        Intent i = getIntent();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_persons, new PersonsFragment())
                    .commit();
            table = i.getStringExtra(SearchFragment.NAME);
        }
        else{
            table=savedInstanceState.getString(SearchFragment.NAME);
        }

    }


}
