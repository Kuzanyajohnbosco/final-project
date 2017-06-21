/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

public class Student {
    
    private String studentId;
    private String surName;
    private String otherName;   
    private String gender;    
    private String residence;
    
    public Student(){    
    }

    public Student(String studentId, String surName, String otherName, String gender, String residence) {
        this.studentId = studentId;
        this.surName = surName;
        this.otherName = otherName;
        this.gender = gender;
        this.residence = residence;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    
   
}
