package org.example.model;

/**
 * @author chenj
 */
public class FemaleHealthForm  extends  AbstractHealthForm{

    private  String uterus;

    public String getUterus() {
        return uterus;
    }

    public void setUterus(String uterus) {
        this.uterus = uterus;
    }


//    @Override
//    public String toString() {
//        return "FemaleHealthForm{" +
//                "id=" + getId() +
//                ", empId=" + getEmpId() +
//                ", heart='" + getHeart() + '\'' +
//                ", liver='" + getLiver() + '\'' +
//                ", spleen='" + getSpleen() + '\'' +
//                ", lung='" + getLung() + '\'' +
//                ", kidney='" +getKidney() + '\'' +
//                ", note='" + getNote() + '\'' +
//                ", uterus='"+uterus + '\'' +
//                '}';
//    }
}
