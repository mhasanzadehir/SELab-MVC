package selab.mvc.models;

import java.util.regex.Pattern;

public class ValidationUtils {

    /**
     * @param value The value to be validated as course number
     * @return true, if value is in a correct format
     */
    public static boolean validateCourseNo(String value) {
        Pattern pattern = Pattern.compile("^\\d{5}-\\d$");
        return pattern.matcher(value).find();
    }


    /**
     * @param value The time to be checked
     * @return true, if the format of the input is appropriate for a time, like
     */
    public static boolean validateTime(String value) {
        Pattern pattern = Pattern.compile("^((0|1)\\d|2[0-4]):([0-5]\\d)$");
        return pattern.matcher(value).find();
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    public static boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }


    /**
     * @param value The value to be validated as registration point
     * @return true, if value is in a correct format
     */
    public static boolean validatePoint(double value) {
        return value <= 20 && value >=0;
    }
}
