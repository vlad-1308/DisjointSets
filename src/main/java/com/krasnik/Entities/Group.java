package com.krasnik.Entities;

import java.util.*;

public class Group {
    private boolean wasMerged = false;
    
    private List<Line> lines;
    private Set<Field> uniqFields;
    
    public Group() {
        lines = new LinkedList<Line>();
        uniqFields = new HashSet<Field>();
    }
    
    public boolean hasMatch(Line line) {
        if (line != null) {
            for (Field field : line.getFields()) {
                if (uniqFields.contains(field)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
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
            group.clearData();
        }
    }
    
    private void clearData() {
        this.lines = null;
        this.uniqFields = null;
        this.wasMerged = true;
    }
    
    public List<Line> getLines() {
        return lines;
    }
    
    public Set<Field> getUniqFields() {
        return uniqFields;
    }
    
}