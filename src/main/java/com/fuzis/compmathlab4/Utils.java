package com.fuzis.compmathlab4;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Random;

@Service
public class Utils {
    public File getPhoto(String path){
        File dir = new File(".\\imgs\\"+path);
        File[] files = dir.listFiles();
        System.out.println(dir.getAbsolutePath());
        Random rand = new Random();
        if(files != null && files.length > 0)
        {
            return files[rand.nextInt(files.length)];
        }
        return null;
    }
}
