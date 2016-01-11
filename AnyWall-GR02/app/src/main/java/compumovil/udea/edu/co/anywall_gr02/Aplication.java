package compumovil.udea.edu.co.anywall_gr02;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by dante on 27/11/15.
 */
public class Aplication extends android.app.Application{
    public static final boolean APPDEBUG=false;
    public static final String APPTAG="AnyWall";
    public Aplication(){}

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(AnywallPost.class);
        Parse.initialize(this, "3DJGpU6CJ6Vj00KGUK94eJia5bI4VaMXbu85jAGQ"
                , "FIZVBjGKlclbtDZE8iK1fV7yLULWdfkRuaL2kNgt");
        ParseUser.enableAutomaticUser();
        ParseACL defauAcl=new ParseACL();
        defauAcl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defauAcl,true);
    }
}
