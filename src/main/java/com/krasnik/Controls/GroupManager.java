package com.krasnik.Controls;

import com.krasnik.Entities.*;

import java.util.*;

public class GroupManager {

    private HashMap<Field, Group> heapFields = new HashMap<>();
    
    public GroupManager() {

    }
    
    public Group createGroup(Line line){
        Group group = new Group();
        group.addLine(line);
        for (Field field: line.getFields()) {
            heapFields.put(field, group);
        }
        return group;
    }

    public void addInHeap(Line line, Group group) {
        for (Field field: line.getFields()) {
            heapFields.put(field, group);
        }
    }

    public Group checkInHeap(Field field) {
            return heapFields.get(field);
    }
    
    public List<Group> getGroups() {
        Set<Group> groupsSet = new HashSet<>();
        for (Group group : heapFields.values()) {
            groupsSet.add(group);
        }
        List<Group> listGroup = new ArrayList<>(groupsSet);
        Collections.sort(listGroup, new Comparator<Group>() {
            @Override
            public int compare(Group o1, Group o2) {
                return o2.getLines().size() - o1.getLines().size();
            }
        });
        return listGroup;
     }

     public int getCountFillGroup() {
        int count = 0;
        for(Group group : this.getGroups()) {
            if (group.getLines().size() > 1) {
                count++;
            }
        }
        return count;
     }

    public Map<Field, Group> getHeapFields() {
        return heapFields;
    }
}