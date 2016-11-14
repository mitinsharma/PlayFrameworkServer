package services;

import com.fdflib.model.entity.FdfEntity;
import com.fdflib.service.impl.FdfCommonServices;
import models.Section;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class SectionService extends FdfCommonServices {
    public Section saveSection(Section newSection) {
        if(getEntitiesByValueForPassedField(Section.class, "name", newSection.name).size()<1
                && getEntitiesByValueForPassedField(Section.class, "sectionNumber", Integer.toString(newSection.sectionNumber)).size()<1){
            save(Section.class,newSection);
        }
        else {
            FdfEntity<Section> oldSection = getSectionByNameAndNumber(newSection.name,newSection.sectionNumber);
            newSection.id=oldSection.entityId;
            newSection.cf=true;
            save(Section.class, newSection);
        }
        return newSection;
    }

    public List<Section> getAllSections() { return getAllCurrent(Section.class); }

    public List<FdfEntity<Section>> getAllSectionsWithHistory() { return getAll(Section.class); }

    public Section getSectionByNumber(String sectionNumber){
        List <FdfEntity<Section>> tarSection = getEntitiesByValueForPassedField(Section.class, "sectionNumber", sectionNumber);
        return tarSection.get(0).current;
    }

    public List<FdfEntity<Section>> getSectionByCourseName(String name){
        return getEntitiesByValueForPassedField(Section.class, "name", name);
    }

    public Section getSectionById(Long id) { return getEntityCurrentById(Section.class,id); }

    public FdfEntity<Section> getSectionByNameAndNumber(String name, int sectionNumber) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("name", name);
        fieldsAndValues.put("sectionNumber", Integer.toString(sectionNumber));
        List<FdfEntity<Section>> tarSection =
                getEntitiesByValuesForPassedFields(Section.class, fieldsAndValues);
        if(tarSection.isEmpty()) return null;
        return tarSection.get(0);
    }

    public void deleteSection(Long id) { setDeleteFlag(Section.class,id,-1,-1); }
    public void undeleteSection(Long id) { removeDeleteFlag(Section.class,id,-1,-1); }
}
