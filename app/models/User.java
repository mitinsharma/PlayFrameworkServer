package models;
import com.fdflib.model.state.CommonState;

/**
 * Created by Mitin on 10/26/2016.
 */
public class User extends CommonState {

    public String firstName, lastName, studentId, userName, password;

    public User()
    {
        super();
    }

    public User(String fname,String lname, String sid, String uname, String pass)
    {
        super();
        firstName = fname;
        lastName = lname;
        studentId = sid;
        userName = uname;
        password = pass;

    }

    private void getPasswordHash(String password)
    {

    }

    public void getClasses(int userId)
    {

    }

}
