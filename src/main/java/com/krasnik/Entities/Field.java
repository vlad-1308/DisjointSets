package com.krasnik.Entities;

import java.util.Objects;

public class Field implements Comparable<Field> {
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
                        && (Objects.equals(field.getItem(), this.item)
                            || (field.getItem() != null && field.getItem().equals(this.item)));
    } 
    
    public int hashCode() {
        int res = 27 * position;
        res *= 27 * ((item == null) ? 0 : item.hashCode());
        return res;
    }

    public String getItem() {
        return item;
    }
    
    public int getPosition() {
        return position;
    }

    @Override
    public int compareTo(Field o) {
        return this.position - o.getPosition();
    }
}