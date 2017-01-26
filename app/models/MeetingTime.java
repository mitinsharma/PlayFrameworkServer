package models;

import com.fdflib.model.state.CommonState;
import org.joda.time.DateTime;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class MeetingTime extends CommonState {
    public DateTime startTime;
    public DateTime endTime;
    public int repeatFrequency;
}
