package selab.mvc.models.entities;

import selab.mvc.models.Model;
import selab.mvc.models.ValidationUtils;

public class Registration implements Model {
    private String studentNo;
    private String courseNo;
    private double point;

    @Override
    public String getPrimaryKey() {
        return this.studentNo + "-" + this.courseNo;
    }


    public void setCourseNo(String value) {
        if (!ValidationUtils.validateCourseNo(value))
            throw new IllegalArgumentException("Format is not correct");

        this.courseNo = value;
    }

    public void setStudentNo(String value) {
        if (!ValidationUtils.validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }

    public void setPoint(double point) {
        if (!ValidationUtils.validatePoint(point))
            throw new IllegalArgumentException("Format is not correct");

        this.point = point;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public double getPoint() {
        return point;
    }

}
