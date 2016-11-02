package models;

import com.fdflib.model.state.CommonState;

/**
 * Created by tsmanner on 11/1/2016.
 */
public class SectionToMeetingTime extends CommonState {
    public SectionToMeetingTime() { super(); }
    public SectionToMeetingTime(long inSectionId, long inMeetingTime) {
        super();
        sectionId = inSectionId;
        meetingTimeId = inMeetingTime;
    }
    public long sectionId;
    public long meetingTimeId;
}
