package util;

public class Field {
    private String name, type;
    private boolean notNull, primary, unique;
    
    public Field(){
        this("","int");
    }
    
    public Field(String name, String type){
        this(name,type,false,false,false);
    }
    
    public Field(String name, String type, boolean notNull, boolean primary, boolean unique){
        if(name!=null){
            this.name = name;
        }else{
            this.name = "";
        }
        if(type!=null){
            this.type = type.toLowerCase();
        }else{
            this.type = "int";
        }
        this.notNull = notNull;
        this.primary = primary;
        this.unique = unique;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        if(name!=null){
            this.name = name;
        }
    }
    
    public String getType(){
        return " " + type.toLowerCase();
    }
    
    public String getNotNull(){
        String result;
        if(notNull){
            result = " not null";
        }else{
            result = "";
        }
        return result;
    }
    
    public String getPrimary(){
        String result;
        if(primary){
            result = " primary key";
        }else{
            result = "";
        }
        return result;
    }
    
    public String getUnique(){
        String result;
        if(unique){
            result = " unique";
        }else{
            result = "";
        }
        return result;
    }
    
    public boolean isPrimary(){
        return primary;
    }
    
    public void setType(String type){
        if(type!=null){
            this.type = type.toLowerCase();
        }
    }
    
    public void setNotNull(boolean notNull){
        this.notNull = notNull;
    }
    
    public void setUnique(boolean unique){
        this.unique = unique;
    }
    
    public void setPrimary(boolean primary){
        this.primary = primary;
    }
}
