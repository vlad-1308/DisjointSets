package com.krasnik.Controls;

import com.krasnik.Entities.*;

import java.util.*;

public class GroupManager {
    private List<Group> groups;
    
    public GroupManager() {
        groups = new ArrayList<Group>();
    }
    
    private void createGroup(Line line){
        if (groups == null) {
            groups = new ArrayList<Group>();
        }
        groups.add(new Group().addLine(line));
    }
    
    public void checkMatchOrCreate(Line line) {
        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext()) {
            Group group = iterator.next();
            if (group.hasMatch(line)) {
                group.addLine(line);
            } else {
                createGroup(line);
            }
        }
    }
    
    public List<Group> getGroups() {
        return groups;
    }
}