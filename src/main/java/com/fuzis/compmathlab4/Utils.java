package com.fuzis.compmathlab4;

import org.springframework.stereotype.Service;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

@Service
public class Utils {
    public Utils() {
        rand = new Random(System.currentTimeMillis());
    }
    Random rand;
    public File getRandomPhoto(String path){
        File dir = new File(".\\imgs\\"+path);
        File[] files = dir.listFiles();
        if(files != null && files.length > 0)
        {
            return files[rand.nextInt(files.length)];
        }
        return null;
    }
    public String saveGraph(String base) throws NoSuchAlgorithmException, IOException {
        byte[] encoded = base.getBytes();
        byte[] decoded = Base64.getDecoder().decode(encoded);
        String name = UUID.randomUUID().toString();
        File fl = new File(".\\imgs\\graph\\"+name+".jpg");
        OutputStream out = new FileOutputStream(fl);
        out.write(decoded);
        out.close();
        return name;
    }
    public String getNSymbols(Object inp, int n){
        StringBuilder pre = new StringBuilder(inp.toString());
        if(pre.length() > n){
            pre = new StringBuilder(pre.substring(0, n - 2) + "..");
        }
        if(pre.length() < n){
            int length = pre.length();
            for(int i = 0 ; i < n-length; i++){
                pre.append(" ");
            }
        }
        return pre.toString();
    }
}
