package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by Mitin on 10/26/2016.
 */
public class EnrollmentActions extends CommonState {

    public String date, time;

    public EnrollmentActions()
    {
        super();
    }

    public EnrollmentActions(String d, String t)
    {
        date=d;
        time=t;
    }
}
