
package objects;


public class Doctor {
    private int Id;
    private String service;
    private String userName;
    private String password;
    
    public Doctor() {
        
    }

    public Doctor(String userName, String password,String service) {
        this.service = service;
        this.userName = userName;
        this.password = password;
    }
    
    public Doctor(int Id,String userName, String password, String service) {
        this.Id = Id;
        this.service = service;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

   
    
}
