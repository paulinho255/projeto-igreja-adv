/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.teste;

import java.io.*;
import java.util.zip.*;

public class ComprimeZip {
   static final int BUFFER = 2048;
   public static void main (String argv[]) {
      try {
         BufferedInputStream origin = null;
         FileOutputStream dest; 
         ZipOutputStream out; 
         //out.setMethod(ZipOutputStream.DEFLATED);
         byte data[] = new byte[BUFFER];
         // get a list of files from current directory
         File f = new File("C:/src");
         File[] files = f.listFiles();
         String arquivos[];
         for (File file : files)
         {
//         for (int i=0; i<files.length; i++) {
//            System.out.println("Adding: "+files[i]);
          if (file.isFile()){
              
            dest = new FileOutputStream("C:/src/Saida/"+file.getName()+".zip");
            out = new ZipOutputStream(new BufferedOutputStream(dest));
            FileInputStream fi = new FileInputStream(file.getPath());
            origin = new BufferedInputStream(fi, BUFFER);
            ZipEntry entry = new ZipEntry(file.getPath());
            out.putNextEntry(entry);
            int count;
            while((count = origin.read(data, 0, 
              BUFFER)) != -1) {
               out.write(data, 0, count);
            }
            out.close();
            }   
            origin.close();
         }
      } catch(Exception e) {
         e.printStackTrace();
      }
   }
} 
