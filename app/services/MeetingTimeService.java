package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.MeetingTime;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Dominic Rossillo on 10/31/2016.
 */
public class MeetingTimeService extends FdfCommonServices{
    //save meeting times that are new
    public void saveMeetingTime(MeetingTime newMeetingTime){
        // if the meet time doesnt exist then add it else do nothing
        if((getMeetingTimeByStartAndEndTime(newMeetingTime.startTime,newMeetingTime.endTime)).size()<1){
            save(MeetingTime.class,newMeetingTime);


        }


    }
    //get all current meeting times
    public List<MeetingTime> getAllSections(){
        return this.getAllCurrent(MeetingTime.class);

    }
    //get all meeting times with history
    public List<FdfEntity<MeetingTime>> getAllMeeetingTimeWithHistory() {
        return this.getAll(MeetingTime.class);
    }



    //find a meeting time based on start and end time
    public List<FdfEntity<MeetingTime>> getMeetingTimeByStartAndEndTime(DateTime start, DateTime end) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("startTime", String.valueOf(start));
        fieldsAndValues.put("endTime", String.valueOf(end));


        List<FdfEntity<MeetingTime>> tarMeetingTime =
                getEntitiesByValuesForPassedFields(MeetingTime.class, fieldsAndValues);

        return tarMeetingTime;
    }


    //delete Meeting Time
    public void deleteMeetingTime(Long id){
        setDeleteFlag(MeetingTime.class,id,-1,-1);

    }
    //undelete Meeting Time
    public void undeleteMeetingTime(Long id){
        removeDeleteFlag(MeetingTime.class,id,-1,-1);
    }




}
