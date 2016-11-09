package models;

import play.data.format.Formats;

import java.util.Date;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class Section extends LmsAccessElement {

    public String name;
    public int sectionNumber;
    public boolean published;
    protected Date puslishDate;
    public  Formats.DateTime[] meetingTimes;
    public long professor;
}
