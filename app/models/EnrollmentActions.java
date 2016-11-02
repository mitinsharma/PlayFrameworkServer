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
    public Long userId, sectionId;

    public EnrollmentActions()
    {
        super();
    }

    public EnrollmentActions(Long uid, Long sid, Date d, Formats.DateTime t)
    {
        userId  = uid;
        sectionId = sid;
        date=d;
        time=t;
    }
}
