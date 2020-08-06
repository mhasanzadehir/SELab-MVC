package selab.mvc.models.entities;

import selab.mvc.models.DataContext;
import selab.mvc.models.DataSet;
import selab.mvc.models.Model;
import selab.mvc.models.ValidationUtils;
import sun.misc.Regexp;

import java.util.OptionalDouble;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Course implements Model {
    private String title;
    private String courseNo;
    private String startTime = null;
    private String endTime = null;
    private Weekday weekday;


    @Override
    public String getPrimaryKey() {
        return this.courseNo;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setCourseNo(String value) {
        if (!ValidationUtils.validateCourseNo(value))
            throw new IllegalArgumentException("Format is not correct");

        this.courseNo = value;
    }

    public String getCourseNo() {
        return this.courseNo;
    }

    public void setStartTime(String value) {
        if (!ValidationUtils.validateTime(value))
            throw new IllegalArgumentException("Invalid time format.");

        if (this.endTime != null && compareTime(value, this.endTime) != -1)
            throw new IllegalArgumentException("The start time cannot be past the end time.");

        this.startTime = value;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setEndTime(String value) {
        if (!ValidationUtils.validateTime(value))
            throw new IllegalArgumentException("Invalid time format");

        if (this.startTime != null && compareTime(value, this.startTime) != 1)
            throw new IllegalArgumentException("The end time cannot be earlier than the start time.");

        this.endTime = value;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setWeekday(Weekday value) {
        this.weekday = value;
    }

    public String getWeekday() {
        return this.weekday.name();
    }

    public double getAverage() {
        DataSet<Registration> registrations = DataContext.getInstance().getRegistrations();
        OptionalDouble result = registrations.getAll()
                .stream()
                .filter(registration -> registration.getCourseNo().equals(this.courseNo))
                .mapToDouble(Registration::getPoint)
                .average();
        return result.isPresent() ? result.getAsDouble() : 0;
    }

    public String getStudents() {
        DataSet<Registration> registrations = DataContext.getInstance().getRegistrations();
        return registrations.getAll()
                .stream()
                .filter(registration -> registration.getCourseNo().equals(this.courseNo))
                .map(Registration::getStudentNo)
                .collect(Collectors.joining(","));
    }

    /**
     * @param time1 First time
     * @param time2 Second time
     * @return If time1 > time2, returns 1, if time1 < time2 returns -1, otherwise returns 0
     */
    private int compareTime(String time1, String time2) {
        int time1Hour = Integer.parseInt(time1.substring(0, 2));
        int time1Minute = Integer.parseInt(time1.substring(3, 5));
        int time2Hour = Integer.parseInt(time2.substring(0, 2));
        int time2Minute = Integer.parseInt(time2.substring(3, 5));

        if (time1Hour > time2Hour || (time1Hour == time2Hour && time1Minute > time2Minute))
            return 1;
        else if (time1Hour == time2Hour && time1Minute == time2Minute)
            return 0;
        else
            return -1;
    }
}
