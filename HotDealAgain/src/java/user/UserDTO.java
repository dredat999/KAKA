package user;

import java.time.LocalDateTime;

/**
 *
 * @author HP
 */
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String telephone;
    private LocalDateTime created_date;
    private LocalDateTime last_loginDate;
    private LocalDateTime last_modified;
    private int role_id;
    private boolean is_actived;

        public UserDTO(int id, String username, String password, String first_name, String last_name, String telephone, LocalDateTime created_date, LocalDateTime last_loginDate, LocalDateTime last_modified, int role_id, boolean is_actived) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.telephone = telephone;
        this.created_date = created_date;
        this.last_loginDate = last_loginDate;
        this.last_modified = last_modified;
        this.role_id = role_id;
        this.is_actived = is_actived;
    }
    
    
    
    public UserDTO(int id, String username, String password, String first_name, String last_name, String telephone, LocalDateTime created_date, LocalDateTime last_loginDate, int role_id, boolean is_actived) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.telephone = telephone;
        this.created_date = created_date;
        this.last_loginDate = last_loginDate;
        this.role_id = role_id;
        this.is_actived = is_actived;
    }

    public UserDTO(int id, String username, String first_name, String last_name, String telephone, LocalDateTime created_date, LocalDateTime last_loginDate, int role_id, boolean is_actived) {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.telephone = telephone;
        this.created_date = created_date;
        this.last_loginDate = last_loginDate;
        this.role_id = role_id;
        this.is_actived = is_actived;
    }   

    UserDTO(int id, String username, String password, String firstname, String lastname, String telephone, int role, boolean active) {
        this.id = id;
        this.username = username;
        this.first_name = firstname;
        this.last_name = lastname;
        this.telephone = telephone;
        this.role_id = role;
        this.is_actived = active;
    }
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the created_date
     */
    public LocalDateTime getCreated_date() {
        return created_date;
    }

    /**
     * @param created_date the created_date to set
     */
    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    /**
     * @return the last_loginDate
     */
    public LocalDateTime getLast_loginDate() {
        return last_loginDate;
    }

    /**
     * @param last_loginDate the last_loginDate to set
     */
    public void setLast_loginDate(LocalDateTime last_loginDate) {
        this.last_loginDate = last_loginDate;
    }

    /**
     * @return the last_modified
     */
    public LocalDateTime getLast_modified() {
        return last_modified;
    }

    /**
     * @param last_modified the last_modified to set
     */
    public void setLast_modified(LocalDateTime last_modified) {
        this.last_modified = last_modified;
    }

    /**
     * @return the role_id
     */
    public int getRole_id() {
        return role_id;
    }

    /**
     * @param role_id the role_id to set
     */
    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    /**
     * @return the is_actived
     */
    public boolean isIs_actived() {
        return is_actived;
    }

    /**
     * @param is_actived the is_actived to set
     */
    public void setIs_actived(boolean is_actived) {
        this.is_actived = is_actived;
    }
    
    
}
