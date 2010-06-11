/*
 * ImageSource.java
 *
 */

package gistoolkit.display.renderer.images;
import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;

/**
 * Retrieves images from the jar file, or from the file system.
 */
public class ImageSource {

    /** Creates new ImageRetriever */
    public ImageSource() {
    }
    public Icon getIcon(String inIconName){
        if (inIconName == null) return null;
        byte[] tn = null;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        InputStream in = getClass().getResourceAsStream(inIconName);
        if (in == null) {
            System.out.println("Reading "+inIconName+" From the File System");
            String tempClass = this.getClass().getPackage().getName();
            tempClass = tempClass.replace('.','/');
            ImageIcon icon = null;
            URL iconURL = ClassLoader.getSystemResource(tempClass+"/"+inIconName);
            if (iconURL != null) {
                icon = new ImageIcon(iconURL);
                return icon;
            }
            return null;
        }
        Image image;
        try {
            int bufflen = 100;
            byte[] buff = new byte[bufflen];
            ByteArrayOutputStream out = new ByteArrayOutputStream(in.available());
            int length = 0;
            do {
                length = in.read(buff);
                if (length != -1)
                    out.write(buff, 0, length);
            }
            while (length != -1);
            image = toolkit.createImage(out.toByteArray());
        }
        catch (Exception exc) {
            System.out.println(exc + " getting resource " + inIconName);
            return null;
        }
        return new ImageIcon(image);
    }
    public Image getImage(String inImageName){
        if (inImageName == null) return null;
        byte[] tn = null;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        InputStream in = getClass().getResourceAsStream(inImageName);
        if (in == null) {
            System.out.println("Reading "+inImageName+" From the File System");
            String tempClass = this.getClass().getPackage().getName();
            tempClass = tempClass.replace('.','/');
            ImageIcon icon = null;
            in = ClassLoader.getSystemResourceAsStream(tempClass+"/"+inImageName);
            if (in == null) {
                System.out.println("Unable to read "+inImageName);
                return null;
            }
        }
        Image image;
        try {
            int bufflen = 100;
            byte[] buff = new byte[bufflen];
            ByteArrayOutputStream out = new ByteArrayOutputStream(in.available());
            int length = 0;
            do {
                length = in.read(buff);
                if (length != -1)
                    out.write(buff, 0, length);
            }
            while (length != -1);
            image = toolkit.createImage(out.toByteArray());
        }
        catch (Exception exc) {
            System.out.println(exc + " getting resource " + inImageName);
            return null;
        }
        return image;
    }
    public InputStream getResource(String inFileName){
        if (inFileName == null) return null;
        byte[] tn = null;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        InputStream in = getClass().getResourceAsStream(inFileName);
        if (in == null) {
            System.out.println("Reading "+inFileName+" From the File System");
            String tempClass = this.getClass().getPackage().getName();
            tempClass = tempClass.replace('.','/');
            in = ClassLoader.getSystemResourceAsStream(tempClass+"/"+inFileName);
            if (in == null) {
                System.out.println("Unable to read "+inFileName);
                return null;
            }
            return in;
        }
        return in;
    }
}