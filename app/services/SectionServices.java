package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.Section;

import java.util.List;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class SectionServices extends FdfCommonServices {







    public Section saveSection(Section newSection){

        save(Section.class, newSection);
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
    public Section getSectionByCode(String code){
        List <FdfEntity<Section>> tarSection= getEntitiesByValueForPassedField(Section.class, "code", code);
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


    //delete Section
    public void deleteCourse(Long id){
        setDeleteFlag(Section.class,id,-1,-1);

    }
    //undelete section
    public void undeleteCourse(Long id){
        removeDeleteFlag(Section.class,id,-1,-1);
    }




}
