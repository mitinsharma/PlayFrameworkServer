package services;

import com.fdflib.service.impl.FdfCommonServices;
import models.Section;

/**
 * Created by Dominic Rossillo on 10/24/2016.
 */
public class CourceServices extends FdfCommonServices{
    public Section addSection(Section newSection){

        save(Section.class, newSection);
        return newSection;
    }


}
