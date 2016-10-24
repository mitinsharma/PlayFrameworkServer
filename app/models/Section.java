package models;

import com.fdflib.model.state.CommonState;
import play.data.format.Formats;

import java.util.Date;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class Section extends CommonState {

    public String name;
    public int code;
    public boolean published;
    protected Date puslishDate;
    public  Formats.DateTime[] meetingTimes;
    public long professor;
}
