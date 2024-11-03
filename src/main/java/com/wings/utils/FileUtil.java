package com.wings.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtil {


    Time time=new Time();
    String timeStamp= time.timeStamp();

    public static void unzip1(String zipFilePath, String destDir) throws Exception{
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getAbsolutePath()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void unzip(String zipFile, String destFolder) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            byte[] buffer = new byte[1024];
            while ((entry = zis.getNextEntry()) != null) {
                File newFile = new File(destFolder + File.separator + entry.getName());
                if (entry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    new File(newFile.getParent()).mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int length;
                        while ((length = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, length);
                        }
                    }
                }
            }
        }
    }

    public static void copyFile(File cfgFilePath,String strTarget) throws IOException {
        Path from = cfgFilePath.toPath(); //convert from File to Path
        Path to = Paths.get(strTarget); //convert from String to Path
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        //FileUtil.copyFile(new File(System.getProperty("user.dir")+"\\downloads\\target\\Build_14026_19013_24DBooks\\Wings Books 24D.exe"),"C:\\Program Files (x86)\\Wings Test");

    }

    public String getData(String fileName, String key) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(fileName);
        Object obj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;
        Object value = jsonObject.get(key);
        if(value ==null ){
            return null;
        } else if(value instanceof String) {
            return (String) value;
        } else if (value instanceof Long) {
            return Long.toString((Long) value);
        } else if (value instanceof Double) {
            return Double.toString((Double) value);
        } else if (value instanceof Boolean) {
            return Boolean.toString((Boolean) value);
        } else {
            return String.valueOf(value);
        }
    }

    public void createJson(JSONObject object){
        try {
            String filename = String.format("./results/json/jsonresult_%s.json",timeStamp);

            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(object.toJSONString());
            fileWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
