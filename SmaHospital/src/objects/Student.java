/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

public class Student {
    
    private String studentId;
    private String name;   
    private String gender;    
    private String residence;
    private String regNo;
    private String course;
    private String tel;
    private String email;
    private String studentNo;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public Student(String studentId, String name, String gender, String residence, String regNo, String course, String tel, String email, String studentNo) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.residence = residence;
        this.regNo = regNo;
        this.course = course;
        this.tel = tel;
        this.email = email;
        this.studentNo = studentNo;
    }
    public Student(){    
    }
    
    //add here code 

   

    
    
   
}
