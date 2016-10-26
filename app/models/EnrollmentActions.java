package models;

import com.fdflib.model.state.CommonState;
import org.joda.time.DateTime;
import play.data.format.Formats;

import java.util.Date;

/**
 * Created by Mitin on 10/26/2016.
 */
public class EnrollmentActions extends CommonState {

    public Date date;
    public Formats.DateTime time;

    public EnrollmentActions()
    {
        super();
    }

    public EnrollmentActions(Date d, Formats.DateTime t)
    {
        date=d;
        time=t;
    }
}
