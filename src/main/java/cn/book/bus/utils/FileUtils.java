/**
 * @Project: jxoa
 * @Title: FileUtils.java
 * @Package com.oa.commons.util
 * @date 2013-4-12 下午4:30:05
 * @Copyright: 2013
 */
package cn.book.bus.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * 类名：FileUtils
 * 功能：文件工具
 * 详细：
 * 作者：
 * 版本：1.0
 * 日期：2013-4-12 下午4:30:05
 *
 */
public class FileUtils {

    public static Log log = LogFactory.getLog(FileUtils.class);

    public static long ONE_KB = 1024;
    public static long ONE_MB = ONE_KB * 1024;
    public static long ONE_GB = ONE_MB * 1024;
    public static long ONE_TB = ONE_GB * (long) 1024;
    public static long ONE_PB = ONE_TB * (long) 1024;
    //支持在线预览的文件后缀
    public static String[] onLineExt = {"txt", "pdf", "png", "jpg", "gif"};

    /**
     * 文件下载
     *
     * @param savePath 保存目录
     * @param name     文件原名
     * @param uuid     保存时的32位UUID文件名称 不包含后缀
     * @param ext      文件后缀
     * @param request
     * @param response
     * @return
     */
    public static String down(String savePath, String name, String uuid, String ext, HttpServletRequest request, HttpServletResponse response) {
        OutputStream toClient = null;
        try {
            String path = savePath + "/" + uuid + "." + ext;
            File file = new File(path);
            if (!file.exists()) {
                //不存在
                request.setAttribute("name", name);
                return "download_error";//返回下载文件不存在
            }
            response.setContentType("application/octet-stream");
            // 根据不同浏览器 设置response的Header
            String userAgent = request.getHeader("User-Agent").toLowerCase();

            if (userAgent.indexOf("msie") != -1) {
                //ie浏览器
                //System.out.println("ie浏览器");
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "utf-8"));

            } else {
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(name.getBytes("utf-8"), "ISO8859-1"));
            }

            response.addHeader("Content-Length", "" + file.length());
            //以流的形式下载文件
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            response.reset();
            return "exception";//返回异常页面
        } finally {
            if (toClient != null) {
                try {
                    toClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 预览文件
     *
     * @param savePath 保存目录
     * @param name     文件原名
     * @param uuid     保存时的UUID 不包含后缀
     * @param ext      文件后缀
     * @param request
     * @param response
     * @return
     */
    public static String inline(String savePath, String name, String uuid, String ext, HttpServletRequest request, HttpServletResponse response) {
        OutputStream toClient = null;
        try {
            String path = savePath + "/" + uuid + "." + ext;
            File file = new File(path);
            if (!file.exists()) {
                //不存在
                request.setAttribute("name", name);
                return "download_error";//返回下载文件不存在
            }
            if (!inOnLineExt(ext)) {
                response.setContentType("application/octet-stream");
            }
            // 根据不同浏览器 设置response的Header
            String userAgent = request.getHeader("User-Agent").toLowerCase();

            if (userAgent.indexOf("msie") != -1) {
                //ie浏览器
                //System.out.println("ie浏览器");
                response.addHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(name, "utf-8"));

            } else {
                response.addHeader("Content-Disposition", "inline;filename=" + new String(name.getBytes("utf-8"), "ISO8859-1"));
            }

            response.addHeader("Content-Length", "" + file.length());
            //以流的形式下载文件
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
            toClient.flush();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            response.reset();
            return "exception";//返回异常页面
        } finally {
            if (toClient != null) {
                try {
                    toClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取文件后缀 不包括.
     *
     * @param fileName
     * @return
     */
    public static String getFileExt(String fileName) {
        if (fileName == null) {
            return "";
        }
        String ext = "";
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex >= 0) {
            ext = fileName.substring(lastIndex + 1).toLowerCase();
        }
        return ext;
    }

    /**
     * 文件大小人性化显示
     * @param fileSize
     * @return
     */
    public static String getHumanReadableFileSize(Long fileSize) {
        if (fileSize == null) {
            return "";
        }
        return getHumanReadableFileSize(fileSize.longValue());
    }

    public static String getHumanReadableFileSize(long fileSize) {
        if (fileSize < 0) {
            return String.valueOf(fileSize);
        }
        String result = getHumanReadableFileSize(fileSize, ONE_PB, "PB");
        if (result != null) {
            return result;
        }

        result = getHumanReadableFileSize(fileSize, ONE_TB, "TB");
        if (result != null) {
            return result;
        }
        result = getHumanReadableFileSize(fileSize, ONE_GB, "GB");
        if (result != null) {
            return result;
        }
        result = getHumanReadableFileSize(fileSize, ONE_MB, "MB");
        if (result != null) {
            return result;
        }
        result = getHumanReadableFileSize(fileSize, ONE_KB, "KB");
        if (result != null) {
            return result;
        }
        return fileSize + " B";
    }

    private static String getHumanReadableFileSize(long fileSize, long unit, String unitName) {
        if (fileSize == 0) {
            return "0";
        }

        if (fileSize / unit >= 1) {
            double value = fileSize / (double) unit;
            DecimalFormat df = new DecimalFormat("######.## " + unitName);
            return df.format(value);
        }
        return null;
    }

    /**
     * 将传入的字符写入文件
     *
     * @param str  需要写入的内容
     * @param path 写入的文件路径
     * @return 返回文件对象
     */
    public static File stringToWrite(String str, String path) {

        FileOutputStream out = null;

        try {
            File file = new File(path);
            out = new FileOutputStream(file);
            out.write(str.getBytes("utf-8"));
            out.flush();
            return file;
        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;


    }

    private static boolean inOnLineExt(String ext) {

        for (String s : onLineExt) {
            if (s.equalsIgnoreCase(ext)) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        int width = 100;
        int height = 100;
        String text = "爱我中华";
        int x = 0;
        int y = 0;
        BufferedImage processDiagram = new BufferedImage(100, 100,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) processDiagram.createGraphics();
        // Font font = new Font("宋体", Font.BOLD, 12);
        Font font = new Font("宋体", Font.BOLD, 11);
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics();
        int textX = x + ((width - fontMetrics.stringWidth(text)) / 2);
        int textY = y + ((height - fontMetrics.getHeight()) / 2)
                + fontMetrics.getHeight();
        g.drawString(text, textX, textY);
        File outFile = new File("d:/newfile.png");
        try {
            ImageIO.write(processDiagram, "png", outFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 写图片
    }
    
}
