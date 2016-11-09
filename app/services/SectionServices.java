package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.Section;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class SectionServices extends FdfCommonServices {


    public Section saveSection(Section newSection){

        if(getEntitiesByValueForPassedField(Section.class, "name", newSection.name).size()<1
                && getEntitiesByValueForPassedField(Section.class, "sectionNumber", Integer.toString(newSection.sectionNumber)).size()<1){
            save(Section.class,newSection);

        }
        else {
            FdfEntity<Section> oldSection =getSectionByNameAndCode(newSection.name,newSection.sectionNumber);
            newSection.id=oldSection.entityId;
            newSection.cf=true;
            save(Section.class, newSection);
        }
        return newSection;
    }


    //load all current for Course
    public List<Section> getAllSections(){
        return this.getAllCurrent(Section.class);

    }

    //get all Sections with history
    public List<FdfEntity<Section>> getAllSectionWithHistory() {
        return this.getAll(Section.class);
    }

    //get Section by code
    public Section getSectionByCode(String sectionNumber){
        List <FdfEntity<Section>> tarSection= getEntitiesByValueForPassedField(Section.class, "sectionNumber", sectionNumber);
        return tarSection.get(0).current;

    }
    //get Sections by Course name
    public List<FdfEntity<Section>> getSectionByCourseName(String name){
        List <FdfEntity<Section>> tarSection= getEntitiesByValueForPassedField(Section.class, "name", name);
        return tarSection;

    }

    //get Section by id
    public Section getSectionById(Long id){
        return getEntityCurrentById(Section.class,id);
    }



    //get Section by name and code
    public FdfEntity<Section> getSectionByNameAndCode(String name, int sectionNumber) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("name", name);
        fieldsAndValues.put("sectionNumber", Integer.toString(sectionNumber));


        List<FdfEntity<Section>> tarSection =
                getEntitiesByValuesForPassedFields(Section.class, fieldsAndValues);

        return tarSection.get(0);
    }

    //delete Section
    public void deleteCourse(Long id){
        setDeleteFlag(Section.class,id,-1,-1);

    }
    //undelete section
    public void undeleteCourse(Long id){
        removeDeleteFlag(Section.class,id,-1,-1);
    }




}
