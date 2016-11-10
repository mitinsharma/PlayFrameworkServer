package models;

import com.fdflib.model.state.CommonState;
import org.joda.time.DateTime;


/**
 * Created by Mitin on 10/26/2016.
 */
public class EnrollmentAction extends CommonState {

    public DateTime dateTime;
    public Long userId, sectionId;
    public EnrollmentType type;

    public EnrollmentAction()
    {
        super();
    }
    public EnrollmentAction(Long uid, Long sid, DateTime d, EnrollmentType t) {
        userId = uid;
        sectionId = sid;
        dateTime = d;
        type = t;
    }
}
