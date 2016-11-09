package models;

import org.joda.time.DateTime;
import play.data.format.Formats;

import java.util.Date;
import java.util.List;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class Section extends LmsAccessElement {
    public Section() { super(); }
    public Section(String inName, int inCode) {
        super();
        name = inName;
        code = inCode;
        published = false;
    }

    public String name;
    public int code;
    public boolean published;
    protected Date publishDate;
    public List<MeetingTime> meetingTimes;
}
