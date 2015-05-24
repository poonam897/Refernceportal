package three.googlers.refernceportal;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class FirstPage extends TabActivity
{
            @Override
            public void onCreate(Bundle savedInstanceState)
            {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.firstpage);

                    // create the TabHost that will contain the Tabs
                    TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

                    TabSpec tab1 = tabHost.newTabSpec("loginpage");
                    TabSpec tab2 = tabHost.newTabSpec("search");
                    TabSpec tab3 = tabHost.newTabSpec("ngodetails");	
                    //TabSpec tab4 = tabHost.newTabSpec("ngodetails");
                    tabHost.setCurrentTab(2);
                    tab1.setIndicator("loginpage");
                    tab1.setContent(new Intent(this,LoginFragment.class));
                  
                    tab2.setIndicator("search");
                    tab2.setContent(new Intent(this,SearchFragment.class));

                    tab3.setIndicator("ngodetails");
                    tab3.setContent(new Intent(this,NgoActivity.class));
                  //  tab4.setIndicator("aboutus");
                    //tab4.setContent(new Intent(this,LoginFragment.class));
                    
                   
                    /** Add the tabs  to the TabHost to display. */
                    tabHost.addTab(tab1);
                    tabHost.addTab(tab2);
                    tabHost.addTab(tab3);
                    //tabHost.addTab(tab4); 
            }
}
