package com.oracle.xing.imagecutter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * https://github.com/oraclexing
 * <p>
 *
 * @author stardust
 * @version 1.0.0
 * 2019/10/8  12:04
 */

public class ImageCutter {

    public static void main(String[] args)throws Exception {
        new ImageCutter().cutImage();
        System.out.println("finished!");
    }

    public void cutImage()throws Exception {
        File file = new File("E:\\Game\\海贼王漫画全彩高清中文版\\OP_Color_Volume03_V2.1_HS");
        int num = 2;
        BufferedImage image ;
        for(File f: file.listFiles()){
            image = ImageIO.read(f);
            ImageIO.write(image.getSubimage(image.getWidth()/2,0,image.getWidth()/2,image.getHeight()),"jpg",new File("C:\\Users\\Oracle\\Desktop\\海贼王-03\\03-"+getNum(num)+".jpg"));
            num++;
            ImageIO.write(image.getSubimage(0,0,image.getWidth()/2,image.getHeight()),"jpg",new File("C:\\Users\\Oracle\\Desktop\\海贼王-03\\03-"+getNum(num)+".jpg"));
            num++;
        }
    }

    public String getNum(int num){
        if(num < 10){
            return "00"+num;
        }
        if(num < 100){
            return "0"+num;
        }
        return num+"";
    }
}
