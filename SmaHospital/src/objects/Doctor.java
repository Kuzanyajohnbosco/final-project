
package objects;


public class Doctor {
    private int Id;
    private String service;
    private String userName;
    private String password;
    private String imgPath;
    private String loginname;
    
    public Doctor() {
        
    }

    public Doctor(String userName, String password,String service,String imgPath,String loginname) {
        this.service = service;
        this.userName = userName;
        this.password = password;
        this.imgPath = imgPath;
        this.loginname = loginname;
    }
    
    public Doctor(int Id,String userName, String password, String service,String imgPath,String loginname) {
        this.Id = Id;
        this.service = service;
        this.userName = userName;
        this.password = password;
        this.imgPath = imgPath;
        this.loginname = loginname;
    }
    
    public Doctor(int Id,String userName, String password, String service, String loginname) {
        this.Id = Id;
        this.service = service;
        this.userName = userName;
        this.password = password;
        this.loginname = loginname;
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    
    public String getloginname() {
        return loginname;
    }

    public void setloginname(String loginname) {
        this.loginname = loginname;
    }    
    

   
    
}
