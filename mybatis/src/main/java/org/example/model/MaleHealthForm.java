package org.example.model;

/**
 * @author chenj
 */
public class MaleHealthForm extends  AbstractHealthForm {

    private  String prostate ;

    public String getProstate() {
        return prostate;
    }

    public void setProstate(String prostate) {
        this.prostate = prostate;
    }


    @Override
    public String toString() {
        return "MaleHealthForm{" +
                "id=" + getId() +
                ", empId=" + getEmpId() +
                ", heart='" + getHeart() + '\'' +
                ", liver='" + getLiver() + '\'' +
                ", spleen='" + getSpleen() + '\'' +
                ", lung='" + getLung() + '\'' +
                ", kidney='" +getKidney() + '\'' +
                ", note='" + getNote() + '\'' +
                ", prostate='"+prostate + '\'' +
                '}';
    }


}
