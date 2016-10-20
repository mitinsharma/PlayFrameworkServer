package models;

import com.fdflib.model.state.CommonState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsmanner on 10/19/2016.
 */
public class LmsAccessElement extends CommonState {
     public LmsAccessElement() {
        userAccesses = new ArrayList<>();
    }

    private Boolean isAuthorized(long userId, AccessLevel accessLevel) {
        for(UserAccess userAccess : userAccesses) {
            if(userAccess.userId == userId && userAccess.accessLevel == accessLevel) {
                return true;
            }
        }
        return false;
    }

    private List<UserAccess> userAccesses;
}
