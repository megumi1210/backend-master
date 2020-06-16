package org.example.model;


import java.sql.Date;
import java.util.List;

/**
 * 级联的测试案例
 * @author chenj
 */
public class Employee {

    private Long id;
    private String realName;
    private SexEnum sex;
    private Date birthday;
    private String mobile;
    private String email;
    private String position;
    private String note;

    /**
     *  与工牌表一对一级联
     */
    private WorkCard workCard;
    /**
     *  与健康表一对一级联分男性和女性
     */
    private AbstractHealthForm healthForm;
    /**
     *  与员工任务表一对多级联
     */
    private List<EmployeeTask> employeeTasks;

    public WorkCard getWorkCard() {
        return workCard;
    }

    public void setWorkCard(WorkCard workCard) {
        this.workCard = workCard;
    }

    public AbstractHealthForm getHealthForm() {
        return healthForm;
    }

    public void setHealthForm(AbstractHealthForm healthForm) {
        this.healthForm = healthForm;
    }

    public List<EmployeeTask> getEmployeeTasks() {
        return employeeTasks;
    }

    public void setEmployeeTasks(List<EmployeeTask> employeeTasks) {
        this.employeeTasks = employeeTasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", note='" + note + '\'' +
                ", workCard=" + workCard +
                ", healthForm=" + healthForm +
                ", employeeTasks=" + employeeTasks +
                '}';
    }
}
