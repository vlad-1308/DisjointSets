package com.krasnik.Entities;

public class Field {
    private final String item;
    private final int position;
    
    public Field(String item, int position) {
        this.item = item;
        this.position = position;
    }
    
    public boolean equals(Object obj) {
        if (obj == this) {
        return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
        return false;
        }
        Field field = (Field) obj;
        return field.getPosition() == this.position 
                        && (field.getItem() == this.item 
                            || (field.getItem() != null && field.getItem().equals(this.item)));
    } 
    
    public int hashCode() {
        int res = 31 * position;
        res *= 31 * ((item == null) ? 0 : item.hashCode());
        return res;
    }
    
    public String getItem() {
        return item;
    }
    
    public int getPosition() {
        return position;
    }
}