package com.miles.testexport.com.miles.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Miles Hu on 2018/8/27 10:36.
 */
public class DrawImageDemo {

    @Test
    public void testDrawImage() {
        try{
            long l = System.currentTimeMillis();
            String coordinate = "{ \"Rect\":{ \"left\":743,\"top\":66,\"right\":847,\"bottom\":170}, \"FaceImageRect\":{ \"left\":691,\"top\":14,\"right\":899,\"bottom\":222}}";
            JSONObject jsonObject = JSON.parseObject(coordinate);
            JSONObject rectjson = JSON.parseObject(jsonObject.get("Rect").toString());
            int x = Integer.parseInt(rectjson.get("left").toString());
            int y = Integer.parseInt(rectjson.get("top").toString());
            int width = (Integer.parseInt(rectjson.get("right").toString())-Integer.parseInt(rectjson.get("left").toString()));
            int height = (Integer.parseInt(rectjson.get("bottom").toString())-Integer.parseInt(rectjson.get("top").toString()));
            //图片路径
            BufferedImage image = ImageIO.read(new File("F:\\人脸\\20180212T120135_0.jpg"));
            Graphics2D g = (Graphics2D)image.getGraphics();
            //画笔颜色
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(4f));
            //矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
            g.drawRect(x, y, width, height);
            //输出图片的地址
            FileOutputStream out = new FileOutputStream("F:\\人脸\\20180212T120135_0_加红框框4.jpg");
            ImageIO.write(image, "jpeg", out);
            System.out.println("总共耗时："+(System.currentTimeMillis() - l)+" 毫秒");
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
