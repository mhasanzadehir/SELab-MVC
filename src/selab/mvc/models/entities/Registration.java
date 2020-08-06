package selab.mvc.models.entities;

import selab.mvc.models.Model;

public class Registration implements Model {
    private String studentNo;
    private String courseNo;
    private double point;

    @Override
    public String getPrimaryKey() {
        return this.studentNo + "-" + this.courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setPoint(double point) {
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
