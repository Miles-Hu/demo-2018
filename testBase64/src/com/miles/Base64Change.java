package com.miles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Change {
	
	 /**
     * @param imgStr base64编码字符串
     * @param path   图片路径-具体到文件
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
        	// 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //图片转化成base64字符串  
    public static String getImageStr(String filePath){//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
       // String imgFile = "F:\\20180409T143535_136894_181239.jpg";//待处理的图片
        // 地址也有写成"F:/deskBG/86619-107.jpg"形式的
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(filePath);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }

	public static void main(String[] args) {
		//String base64Str = getImageStr("F:\\20180409T143535_136894_181239.jpg");
		String base64Str = "VBORw0KGgoAAAANSUhEUgAAAL0AAABoCAIAAAArRmb7AAABzklEQVR4Ae3SsQ0AIAwEMWD/nYMYgaudOt+cvGdmOQU+C5zPf+8KvALccFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZoNNwyUAtyUajbcMFAKcFOq2XDDQCnATalmww0DpQA3pZrNBVh2A82QQWbJAAAAAElFTkSuQmCC";
		//String base64Str = "iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3ZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTExIDc5LjE1ODMyNSwgMjAxNS8wOS8xMC0wMToxMDoyMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDplMzdmMTBlMy1iMjFhLWYyNDAtOGI5Mi04MzczN2ZhMzc1ZTEiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MkE4ODgwODI2NjFCMTFFN0IwMzc4Mjk3RTZCMkQyNDkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MkE4ODgwODE2NjFCMTFFN0IwMzc4Mjk3RTZCMkQyNDkiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6OGIzMzdmM2ItYjNlOC02NTQ2LTg1OGEtMWY1MjkzNDE3NjUxIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOmUzN2YxMGUzLWIyMWEtZjI0MC04YjkyLTgzNzM3ZmEzNzVlMSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PhpSXbEAAAJkSURBVHjatJfBS1RRFIfvjM+xdqNm+zIq3KoL27iwklq6KC2SSVRSF1kLQ9BWEbbSokJrUNAoaCMRFiGIC91Ji3CjweAfMDrG2EKdQX8HzoXL5d337n3MHPgQedfz+d4797xzYyetrcIiKkE7uAmawUVQzddyIAM2wC+wDA7DEsZCxEnwBAyBWmEXu+AdmAT7pkXxgASdYAs8d5AKXkt/s805rMUV4D34As6L6FHHOT4AT7/o+Ui/gg5Ruujjp3AHFE13/LrEUhmU863pUd/lIipXPAL3dDFV7xufxcfgI1h1EGxyLr+KnpLbUIqHDYU0C/rBdbBgIV0H18BjMG4ouKdSnACDhkSXuOCoKB6GyNfALZDn3xsM68iViHM3qjMsagNpCzlJbyvSETBgyFlD/6DHrTAoUvyzV5FTPAiQvgrJeYPEjRbvziS/EEFK0eTxexQR5WfBgaOUot7jrSQiyqX0GZhwyFMdj9AI6AmdUb9wARUsgj4S/xzWy0L6r0hPQA+Yd8iTI/FfR2leebyzylZzkWfoHf8GLRGk6jvtVeQU3SH5NuI8rgiHjqRLU1qTsbnzZSneNSz4w1J1y0wYql2XfzPk3AM/SXzEM5JfrDjsU13+3bBumoZBOewleUbSezZNkGO8XWy/1YtgCYxSo9CuZcEVumt1yuwCn0V5g/r7J30CocFspozSOSn1m7mG+FGVOhZ5oDCOt0WeBtMllKY5ZyFsri7wSHqfiyFqZLmR9OnSsJMEFdpl8IL3nm3Q2pfgatCoFLM8tFVxy6Shr4kHgHPKne3woY0ObD9sDm2nAgwAVgmWZ65O96kAAAAASUVORK5CYII=";
		System.out.println(base64Str);
		String imgStr = base64Str;
		String path = "F:\\base64\\5.jpg";
		System.out.println(generateImage(imgStr, path));
	}

}
