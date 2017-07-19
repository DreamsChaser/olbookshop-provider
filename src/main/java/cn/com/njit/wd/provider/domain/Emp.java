package cn.com.njit.wd.provider.domain;

/**
 * Created by wangdi on 2017/3/27.
 */
public class Emp {
    private String empId;
    private String empName;
    private String empAge;
    private String emgGender;

    public String getEmgGender() {
        return emgGender;
    }

    public void setEmgGender(String emgGender) {
        this.emgGender = emgGender;
    }

    public String getEmpAge() {
        return empAge;
    }

    public void setEmpAge(String empAge) {
        this.empAge = empAge;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "emgGender='" + emgGender + '\'' +
                ", empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", empAge='" + empAge + '\'' +
                '}';
    }
}
