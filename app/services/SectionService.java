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
                && getEntitiesByValueForPassedField(Section.class, "code", Integer.toString(newSection.code)).size()<1) {
            save(Section.class, newSection);
        }
        else {
            FdfEntity<Section> oldSection = getSectionByNameAndCode(newSection.name,newSection.code);
            newSection.id=oldSection.entityId;
            newSection.cf=true;
            save(Section.class, newSection);
        }
        return newSection;
    }

    public List<Section> getAllSections() { return this.getAllCurrent(Section.class); }

    public List<FdfEntity<Section>> getAllSectionsWithHistory() { return this.getAll(Section.class); }

    public Section getSectionByCode(String code){
        List <FdfEntity<Section>> tarSection= getEntitiesByValueForPassedField(Section.class, "code", code);
        return tarSection.get(0).current;
    }

    public List<FdfEntity<Section>> getSectionByCourseName(String name){
        List <FdfEntity<Section>> tarSection= getEntitiesByValueForPassedField(Section.class, "name", name);
        return tarSection;
    }

    public Section getSectionById(Long id) { return getEntityCurrentById(Section.class,id); }

    public FdfEntity<Section> getSectionByNameAndCode(String name, int code) {
        HashMap<String, String> fieldsAndValues = new HashMap<>();
        fieldsAndValues.put("name", name);
        fieldsAndValues.put("code", Integer.toString(code));
        List<FdfEntity<Section>> tarSection =
                getEntitiesByValuesForPassedFields(Section.class, fieldsAndValues);
        return tarSection.get(0);
    }

    public void deleteSection(Long id) { setDeleteFlag(Section.class,id,-1,-1); }
    public void undeleteSection(Long id) { removeDeleteFlag(Section.class,id,-1,-1); }
}
