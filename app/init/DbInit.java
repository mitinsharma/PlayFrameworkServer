package init;

import com.fdflib.persistence.database.DatabaseUtil;
import com.fdflib.service.FdfServices;
import com.fdflib.util.FdfSettings;
import models.Department;
import models.Institution;
import models.LMS;
import models.UserAccess;
import play.Environment;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by tsmanner on 10/22/2016.
 */
@Singleton
public class DbInit {
    private static Logger logger = Logger.getLogger("app.init.DbInit");

    @Inject
    DbInit(Environment env) {
        logger.info("Team2 LMS Start");

        // Get the 4DFLib settings singleton
        FdfSettings.PERSISTENCE = DatabaseUtil.DatabaseType.MYSQL;
        FdfSettings.DB_PROTOCOL = DatabaseUtil.DatabaseProtocol.JDBC_MYSQL;
        FdfSettings.DB_ENCODING = DatabaseUtil.DatabaseEncoding.UTF8;
        FdfSettings.DB_HOST = "localhost";
        FdfSettings.DB_USER = "team2";
        FdfSettings.DB_PASSWORD = "isbestteam";
        FdfSettings.DB_NAME = "mscs-lms";

        // Create an array that makes up our 4df data model.
        List<Class> myModel = new ArrayList<>();

        // Add model objects.
        myModel.add(LMS.class);
        myModel.add(Institution.class);
        myModel.add(Department.class);
        myModel.add(UserAccess.class);

        // Call the initialization of the library
        FdfServices.initializeFdfDataModel(myModel);

        serviceTest();
    }

    private void serviceTest() {
    }
}
