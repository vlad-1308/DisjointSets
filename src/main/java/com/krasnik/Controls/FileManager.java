package com.krasnik.Controls;

import com.krasnik.Entities.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileManager {
    
    private String fileName = "";
    private String filePath = "";
    private final String resultFileName = "resultList.txt";
    
    private GroupManager groupManager;

    public FileManager(String filePath, String fileName) {
        if (filePath != null && fileName != null) {
            this.filePath = filePath;
            this.fileName = fileName;
            groupManager = new GroupManager();
        }
    }

    private Set<String> getListFromFile() {
        HashSet<String> list = new HashSet<>();
        File file = new File(filePath + fileName);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str;
            while((str = br.readLine()) != null){
                list.add(str);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public GroupManager getFieldsFromList() {
        for(String str : getListFromFile()) {
            char delimeter = ';';
                char[] arLine = str.toCharArray();
                Line line = new Line();
                Set<Group> groupsForMerge = new HashSet<>();
                int position = 1;
                StringBuilder sb = new StringBuilder();
                for (char c : arLine) {
                    if (c != delimeter && (c != '\"') && c != '\n' && c != ' ' && c != ',') {
                        sb.append(c);
                    } else {
                        if (sb.length() != 0 || !sb.toString().equals("")) {
                            if (sb.toString().matches("[0-9]")) {
                                arLine = new char[0];
                                continue;
                            }
                            Field field = new Field(sb.toString(), position);
                            if (groupManager.checkInHeap(field) != null){
                                groupsForMerge.add(groupManager.checkInHeap(field));
                            }
                            line.addField(field);
                        }
                        sb.setLength(0);
                        if (c == delimeter || c == ',') {
                            position++;
                        }
                    }
                }
            Collections.sort(line.getFields());
            if (groupsForMerge.size() == 0) {
                groupManager.createGroup(line);
            } else if (groupsForMerge.size() == 1) {
                Group group = groupsForMerge.iterator().next();
                group.addLine(line);
                groupManager.addInHeap(line, group);
            } else {
                Group group = groupManager.createGroup(line);
                for (Group groupForMerge : groupsForMerge) {
                    group.mergeGroup(groupForMerge);
                }
                for (Field field : group.getUniqFields()) {
                    groupManager.getHeapFields().put(field, group);
                }
            }
        }
        System.out.println(groupManager.getGroups().size() + " групп всего.");
        return this.groupManager;
    }

    public void createResultFile(GroupManager groupManager) {
        File file = new File(filePath + resultFileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            int groupNum = 1;
            List<Group> listGroup = groupManager.getGroups();
            for (Group group : listGroup) {
                fos.write(("Группа " + groupNum++).getBytes(StandardCharsets.UTF_8));
                fos.write("\n".getBytes(StandardCharsets.UTF_8));
                for(Line line : group.getLines()) {
                    fos.write(line.toString().getBytes(StandardCharsets.UTF_8));
                    fos.write(("\n").getBytes(StandardCharsets.UTF_8));
                }
                fos.write("\n".getBytes(StandardCharsets.UTF_8));
            }
        }
        catch (IOException ignored) {

        }
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public String getFilePath() {
        return filePath;
    }
}
