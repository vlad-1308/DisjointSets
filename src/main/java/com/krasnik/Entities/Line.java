package com.krasnik.Entities;

import java.util.*;

public class Line {
    
    private final List<Field> fields = new ArrayList<Field>();

    public Line() {
    }
    
    public void addField(Field field) {
        fields.add(field);
    }
    
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Line eqLine = (Line) obj;
        return eqLine.getFields() != null && eqLine.getFields().equals(this.fields);
    }
    
    public int hashCode() {
        return fields.hashCode();
    }
    
    public List<Field> getFields() {
        return fields;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Field field : fields) {
            str.append("\"").append(field.getItem()).append("\"").append("; ");
        }
        return str.toString();
    }
    
}