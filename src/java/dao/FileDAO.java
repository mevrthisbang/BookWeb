/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author mevrthisbang
 */
public class FileDAO {
     public static byte[] readImg(String sourceFilePath) throws Exception {
        FileInputStream fis = null;
        byte[] result = null;
        try {
            fis = new FileInputStream(sourceFilePath);
            int size = fis.available();
            result = new byte[size];
            fis.read(result);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return result;
    }

    public static void writeImg(String destinationFilePath, byte[] imgByte) throws Exception {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(destinationFilePath);
            fos.write(imgByte);
            fos.flush();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}
