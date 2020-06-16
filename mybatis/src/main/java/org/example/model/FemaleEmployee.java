package org.example.model;

/**
 * @author chenj
 */
public class FemaleEmployee extends Employee {

    /**
     *  与健康表一对一级联分男性和女性
     */
    private FemaleHealthForm  healthForm= null;


    public FemaleHealthForm getHealthForm() {
        return healthForm;
    }

    public void setHealthForm(FemaleHealthForm healthForm) {
        this.healthForm = healthForm;
    }

//    @Override
//    public String toString() {
//        return "FemaleEmployee{" +
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
//                ", femaleHealthForm=" + healthForm +
//                '}';
//    }
}
