package compumovil.udea.edu.co.anywall_gr02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.parse.ParseUser;

public class DispatchActivity extends Activity {

    public DispatchActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser=ParseUser.getCurrentUser();
        //System.out.println(ParseUser.getCurrentUser().toString());
        if(currentUser.getUsername()!=null){

            startActivity(new Intent(this,Welcome.class));
        }else{

            startActivity(new Intent(this,LoginSignupActivity.class));
        }
    }
}
