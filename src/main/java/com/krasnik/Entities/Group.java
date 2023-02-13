package com.krasnik.Entities;

import java.util.*;

public class Group {
    
    private Set<Line> lines;
    private Set<Field> uniqFields;
    
    public Group() {
        lines = new HashSet<>();
        uniqFields = new HashSet<>();
    }
    
    public Group addLine(Line line) {
        if (line != null) {
            lines.add(line);
            addUniqField(line.getFields());
        }
        return this;
    }
    
    private void addUniqField(List<Field> fields) {
        uniqFields.addAll(fields);
    }
    
    public void mergeGroup(Group group) {
        if (group != null) {
            this.lines.addAll(group.getLines());
            this.uniqFields.addAll(group.getUniqFields());
        }
    }

    public Set<Line> getLines() {
        return lines;
    }
    
    public Set<Field> getUniqFields() {
        return uniqFields;
    }
    
}