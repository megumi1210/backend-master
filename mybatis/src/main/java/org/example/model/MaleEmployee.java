package org.example.model;

/**
 * @author chenj
 */
public class MaleEmployee extends Employee {
    /**
     *  与健康表一对一级联分男性和女性
     */
    private MaleHealthForm  healthForm= null;


    public MaleHealthForm getHealthForm() {
        return healthForm;
    }

    public void setHealthForm(MaleHealthForm healthForm) {
        this.healthForm = healthForm;
    }

//    @Override
//    public String toString() {
//        return "MaleEmployee{" +
//                "id=" + getId() +
//                ", realName='" + getRealName() + '\'' +
//                ", sex=" + getSex() +
//                ", birthday=" + getBirthday() +
//                ", mobile='" + getMobile() + '\'' +
//                ", email='" + getEmail() + '\'' +
//                ", position='" + getPosition() + '\'' +
//                ", note='" + getNote() + '\'' +
//                ", workCard=" + getWorkCard() +
//                ", employeeTasks=" + getEmployeeTasks() +
//                ", maleHealthForm=" + healthForm +
//                '}';
//    }
}
