/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Sofija
 */
public class Employee extends AbstractDomain {

    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private int userType;
    private boolean deleted;

    public Employee() {
        id = 0;
        userType = Constants.USER;
        deleted=false;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getUserType()
    {
        return userType;
    }

    public void setUserType(int userType)
    {
        this.userType = userType;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    

 

    @Override
    public String toString() {
        if(name==null && surname==null){
            return "<nije izabran>";
        }
        return name + " " + surname ;
    }

    @Override
    public String getTableName()
    {
        return "zaposleni";
    }

    @Override
    public String getColumnNamesForInsert()
    {
        return "id,ime,prezime,username,tip,obrisan";
    }

    @Override
    public String getInsertValues()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("null,")
                .append("'").append(name).append("',")
                .append("'").append(surname).append("',")
                .append("'").append(username).append("',")
                .append(userType).append(",")
                .append(deleted);
        return sb.toString();
    }
    
    public String getUpdateValues(){
        return "ime="+"'"+name+"'"+",prezime="+"'"+surname+"'" +
        ",username='"+username+"'"+ 
        ",tip="+userType+
        ",obrisan="+deleted+ "WHERE id="+id;
    }

    
    
    
    
    

}
