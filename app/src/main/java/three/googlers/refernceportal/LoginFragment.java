package three.googlers.refernceportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import three.googlers.refernceportal.data.PortalDbHelper;

public class LoginFragment extends Activity  {
    TextView tv1,tv2;
   static int id_ngo;
    static String name_ngo;
    public static String TAG_NAME = "name";
    private PortalDbHelper dbHelper;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.loginpage);
        dbHelper = new PortalDbHelper(this);
       tv1 = (TextView)findViewById(R.id.pword);
       tv2 = (TextView)findViewById(R.id.email);
       
       
    }

	public void onLogin(View v) {
        if(chkvalidation()){
            name_ngo = dbHelper.getNGOById(id_ngo);
            Intent i = new Intent(this, Login.class);
            i.putExtra(TAG_NAME,name_ngo);
            startActivity(i);
        }
        else {
            Toast.makeText(this,"Password or Username is Incorrect",Toast.LENGTH_LONG).show();
        }
	}

	private boolean chkvalidation() {
        String pass =  tv1.getText().toString();
        String user = tv2.getText().toString();
        id_ngo = dbHelper.getNGOId(user,pass);
        if(id_ngo!=-1)
            return true;
		return false;
	}
}
