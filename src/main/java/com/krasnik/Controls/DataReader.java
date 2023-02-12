package com.krasnik.Controls;

import com.krasnik.Entities.*;

import java.io.*;
import java.util.*;

public class DataReader {
    
    private String fileName = "";
    private String filePath = "";
    
    private GroupManager groupManager;
    
    public DataReader(String filePath, String fileName) {
        if (filePath != null && fileName != null) {
            this.filePath = filePath;
            this.fileName = fileName;
            groupManager = new GroupManager();
        }
    }
    
    public void getFieldsFromFile() {
        File file = new File(filePath + fileName);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str;
            char delimeter = ';';
            while((str = br.readLine()) != null){
                char[] arLine = str.toCharArray();
                Line line = new Line();
                int position = 0;
                for (char c : arLine) {
                    StringBuilder sb = new StringBuilder();
                    if (c != delimeter) {
                        sb.append(c);
                    } else {
                        if (sb.toString() != "") {
                            Field field = new Field(sb.toString(), ++position);
                            line.addField(field);
                        }
                        sb.setLength(0);
                    }
                }
                groupManager.checkMatchOrCreate(line);
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