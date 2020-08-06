package selab.mvc.models.entities;

import selab.mvc.models.DataContext;
import selab.mvc.models.DataSet;
import selab.mvc.models.Model;
import selab.mvc.models.ValidationUtils;

import java.util.OptionalDouble;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Student implements Model {
    private String name;
    private String studentNo;

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!ValidationUtils.validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public double getAverage() {
        DataSet<Registration> registrations = DataContext.getInstance().getRegistrations();
        OptionalDouble result = registrations.getAll()
                .stream()
                .filter(registration -> registration.getStudentNo().equals(this.studentNo))
                .mapToDouble(Registration::getPoint)
                .average();
        return result.isPresent() ? result.getAsDouble() : 0;
    }

    public String getCourses() {
        DataSet<Registration> registrations = DataContext.getInstance().getRegistrations();
        return registrations.getAll()
                .stream()
                .filter(registration -> registration.getStudentNo().equals(this.studentNo))
                .map(Registration::getCourseNo)
                .collect(Collectors.joining(","));
    }

}
