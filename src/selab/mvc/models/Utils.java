package selab.mvc.models;

import selab.mvc.models.entities.Registration;

public class Utils {

    public static String generateRegistrationKey(Registration registration){
        return registration.getStudentNo() + "-" + registration.getCourseNo();
    }
}
