package util;

import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

/**
 * Proporciona mnhtodos auxiliares para realizar tareas comunes.
 * 
 * @author
 *  
 */
public class Tools {
    
    public final static int BYTES_READED=2048;
    
    public static Image imagePoint = Tools.loadBufferedImage("art" + File.separator
            + "point.png");

    static public BufferedImage loadBufferedImage(String fileName) {
        BufferedImage image = null;
        FileChannel channel;
        ByteBuffer buffer=ByteBuffer.allocateDirect(BYTES_READED);
        File file = new File(fileName);        
        byte[] imageBytes;
        int imageBytesPos=0;

        try {
            FileInputStream fis = new FileInputStream(file);
            channel=fis.getChannel();
            imageBytes=new byte[fis.available()];
            int blockLength;
            
            while (!((blockLength=(channel.read(buffer)))==-1)){                
                buffer.flip();
                buffer.get(imageBytes,imageBytesPos,blockLength);
                imageBytesPos=imageBytesPos+Math.min(imageBytes.length-imageBytesPos,2048);
                buffer.clear();
            }
            
            ByteArrayInputStream bais=new ByteArrayInputStream(imageBytes);
            image = ImageIO.read(bais);
        } catch (FileNotFoundException e) {
            System.out.println(file.toString() +" no existe, gilipollas.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (image.getType()==0){
            BufferedImage imageARGB=new BufferedImage(image.getWidth(null),
                    image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
            
            imageARGB.getGraphics().drawImage(image,0,0,null);
            image=imageARGB;
        }
        
        return image;
    }//loadImage()


    /**
     * Calcula el vector perpendicular al dado, y lo almacena en el mismo punto.
     * 
     * @param vector
     */
    static public void calcNormal(Point2D vector) {
        double x, y;

        x = vector.getX();
        y = vector.getY();
        vector.setLocation(y, -x);
    }

}