package models;
import com.fdflib.model.state.CommonState;

/**
 * Created by Mitin on 10/26/2016.
 */
public class User extends CommonState {

    public String firstName, lastName, userName, password;

    public User()
    {
        super();
    }

    public User(String fname,String lname, String uname, String pass)
    {
        super();
        firstName = fname;
        lastName = lname;
        userName = uname;
        password = pass;

    }



}
