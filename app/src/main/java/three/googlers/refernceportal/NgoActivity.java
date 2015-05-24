package three.googlers.refernceportal;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;


public class NgoActivity extends ActionBarActivity {
    NgoFragment nf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo);
        ActionBar ab = getSupportActionBar();
        nf = new NgoFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ngo_container, nf)
                    .commit();

        }
    }
}
