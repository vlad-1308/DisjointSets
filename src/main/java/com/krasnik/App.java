package com.krasnik;

import com.krasnik.Controls.FileManager;
import com.krasnik.Controls.GroupManager;
import com.krasnik.Entities.Group;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        long time = System.currentTimeMillis();

        FileManager fileManager = new FileManager("src/main/resources/", "lng.txt");
        GroupManager manager = fileManager.getFieldsFromList();
        fileManager.createResultFile(manager);
        System.out.println(manager.getCountFillGroup() + " групп с 2 и более строками.");

        System.out.println((System.currentTimeMillis() - time) / 1000f + " секунд.");
    }
}
