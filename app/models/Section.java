package models;

import play.data.format.Formats;

import java.util.Date;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class Section {
//    + id:int [1] // 4dflib
//            + name:string [1]
//            + code:int [1]
//            + published:bool [1] = false
//            + publishDate:date [1]
//            + meetingTimes:MeetingTime [1..*]
//            + professor:User [1]
    public String name;
    public int code;
    public boolean published;
    protected Date puslishDate;
    public  Formats.DateTime[] meetingTimes;
    public long professor;
}
