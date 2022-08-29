package com.yxh.weshare.utils.fileUpload;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

/**
 * @author Xinhao Yi
 * @date 2022/7/27 18:09
 * @description:
 */
public class FileUploadUtil {
    /**
     * 传到服务器的target中
     */

    public static String upload(MultipartFile originFile, HttpServletRequest request) {

        //拿到文件原本的名字
        //这个是去掉后缀
        // String fileName = originFile.getOriginalFilename().substring(0,originFile.getOriginalFilename().lastIndexOf("."));
        //这个是不去后缀
        //get the original name of the file
        // This removes the suffix
        // String fileName = originFile.getOriginalFilename().substring(0,originFile.getOriginalFilename().lastIndexOf(".")) ;
        // this is without the suffix
        String fileName = originFile.getOriginalFilename();

        //在文件前面加一个随机字符串然后把它和文件名用-连接起来
        // prefix the file with a random string and connect it to the file name with -
        String uuid = UUID.randomUUID().toString();
        fileName = uuid + "-" + fileName;

        //文件夹用hash分散 比如说算出来的hash值是  abcdef
        //那么变成char[]后，就是 a  b  c  d  e   f
        //我们的basePath就变成 image/a/b/c/d/e/f   这样就创建了很多有层次的文件夹，把图片分散开了
        // Folders are scattered using hash Let's say the calculated hash value is abcdef
        //then when it becomes char[], it is a b c d e f
        //our basePath becomes image/a/b/c/d/e/f This creates a number of hierarchical folders and spreads out the images
        int hashCode = fileName.hashCode();
        String hexString = Integer.toHexString(hashCode);
        char[] chars = hexString.toCharArray();

        // basePath基地址
        String basePath = "static/img/goods";
        for (char aChar : chars) {
            basePath = basePath + "/" + aChar;
        }

        // 比如   static/img/goods/a/b/c/d/12321234-a.jpg
        String relativePath = basePath + "/" + fileName;


        //todo 这一行是因为我们是把图片部署out中的到webapp下的，也就是服务器真实部署地址下static/img/goods，一旦重新部署，所有的图片都没了
        // 所以，我们在测试阶段，还要额外部署一份图片到本地的static/img/goods，这样避免丢失图片
        //现在上传云服务器，注释掉下面这一行
//        FileUploadUtil.upload_assist(originFile, relativePath);

        //拿到 image/a/b/c/d/12321234-a.jpg 在服务器中的真正的地址，这里指的是tomcat部署后的真正的地址，也就是out中的内容
        //这个realPath 就是我们真正要保存的地址
        //举个例子，比如项目名叫Demo4   a.txt放在webapp下面
        //那么realpath如下所示
        //D:\eclipsework\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Demo4\a.txt
        //b.txt 在WEB-INF下
        //结果为：D:\eclipsework\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Demo4\WEB-INF\b.txt
        //src下的文件编译后，会放在WEB-INF下的classes文件夹，所以，如果c.txt直接放在src下面
        //结果为：D:\eclipsework\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Demo4\WEB-INF\classes\c.txt

        //注意类加载器是做不到这一点的，类加载器只能获得src下的真实目录，不能获取web下的真实目录
        //pro.load(JdbcUtil.class.getClassLoader().getResourceAsStream("Druid.properties"));
        ///E:/Glasgow_IT/The_Graduate_project/weshare/target/weshare/
        String realPath = request.getSession().getServletContext().getRealPath(relativePath);

        System.out.println("realpath: " + realPath);

        //目的地
        File file = new File(realPath);
        //如果当前文件12321234-a.jpg 的父类文件夹不存在，就一口气都创建出来
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //写入到目的地
        try {
            // originFile.transferTo(file);
            inputStreamToFile(originFile, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //把保存的地址：  服务器部署地址(xxxxxxxx/项目名字)/image/a/b/c/d/12321234-a.jpg  返回出去
        //错了，我们只返回 static/image/goods/a/b/c/d/12321234-a.jpg
        return relativePath;

    }

    /**
     * 辅助函数
     * @param originFile
     * @param relativePath
     */
    private static void upload_assist(MultipartFile originFile, String relativePath) {
        //relativePath = static/img/goods/a/b/c/d/12321234-a.jpg
        String basePath = "E:\\Glasgow_IT\\The_Graduate_project\\weshare\\src\\main\\webapp\\";
        String replaceRelativePath = relativePath.replace('/', '\\');
        //  replaceRelativePath = static\\img\\goods\\a\\b\\c\\d\\12321234-a.jpg


        //  E:\Glasgow_IT\The_Graduate_project\weshare\src\main\webapp\\replaceRelativePath = static\\img\\goods\\a\\b\\c\\d\\12321234-a.jpg
        String realPath = basePath + replaceRelativePath;

        //目的地
        File file = new File(realPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //写入到目的地
        try {
            // originFile.transferTo(file);
            inputStreamToFile(originFile, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 传到本地的硬盘上
     */
    public static String upload_disk(MultipartFile originFile, HttpServletRequest request) {
        //拿到文件原本的名字
        //这个是去掉后缀
        // String fileName = originFile.getOriginalFilename().substring(0,originFile.getOriginalFilename().lastIndexOf("."));
        //这个是不去后缀
        String fileName = originFile.getOriginalFilename();


        //在文件前面加一个随机字符串然后把它和文件名用-连接起来
        String uuid = UUID.randomUUID().toString();
        fileName = uuid + "-" + fileName;

        //文件夹用hash分散 比如说算出来的hash值是  abcdef
        //那么变成char[]后，就是 a  b  c  d  e   f
        //我们的basePath就变成 image/a/b/c/d/e/f   这样就创建了很多有层次的文件夹，把图片分散开了
        int hashCode = fileName.hashCode();
        String hexString = Integer.toHexString(hashCode);
        char[] chars = hexString.toCharArray();

        // basePath基地址
        String basePath = "static\\img\\goods";
        for (char aChar : chars) {
            basePath = basePath + "\\" + aChar;
        }

        // 比如 static/img/goods/a/b/c/d/12321234-a.jpg
        String relativePath = basePath + "\\" + fileName;

        //拿到 image/a/b/c/d/12321234-a.jpg 在服务器中的真正的地址，这里指的是tomcat部署后的真正的地址，也就是out中的内容
        //这个realPath 就是我们真正要保存的地址
        //举个例子，比如项目名叫Demo4   a.txt放在webapp下面
        //那么realpath如下所示
        //D:\eclipsework\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Demo4\a.txt
        //b.txt 在WEB-INF下
        //结果为：D:\eclipsework\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Demo4\WEB-INF\b.txt
        //src下的文件编译后，会放在WEB-INF下的classes文件夹，所以，如果c.txt直接放在src下面
        //结果为：D:\eclipsework\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Demo4\WEB-INF\classes\c.txt

        //注意类加载器是做不到这一点的，类加载器只能获得src下的真实目录，不能获取web下的真实目录
        //pro.load(JdbcUtil.class.getClassLoader().getResourceAsStream("Druid.properties"));


        // E:\\Glasgow_IT\The_Graduate_project\weshare\target\weshare\
        String realPath = request.getSession().getServletContext().getRealPath("");

        // E:/Glasgow_IT\The_Graduate_project\weshare\target\weshare\
        realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
        // E:/Glasgow_IT/\The_Graduate_project\weshare\target\weshare

        // +1是为了保留最后一个斜杠
        // E:/Glasgow_IT\The_Graduate_project\weshare\target\weshare
        realPath = realPath.substring(0, realPath.lastIndexOf("\\") + 1);
        // E:/Glasgow_IT\The_Graduate_project\weshare\target\

        // E:/Glasgow_IT\The_Graduate_project\weshare\target\
        realPath = realPath.substring(0, realPath.lastIndexOf("\\"));
        // E:/Glasgow_IT\The_Graduate_project\weshare\target


        // E:\Glasgow_IT\The_Graduate_project\weshare\target
        realPath = realPath.substring(0, realPath.lastIndexOf("\\") + 1);
        // E:\Glasgow_IT\The_Graduate_project\weshare\   这是我们想要的答案

        // E:\Glasgow_IT\The_Graduate_project\weshare\static/img/goods/a/b/c/d/12321234-a.jpg
        realPath = realPath + relativePath;

        //目的地
        File file = new File(realPath);
        //如果当前文件12321234-a.jpg 的父类文件夹不存在，就一口气都创建出来
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //写入到目的地
        try {
            // originFile.transferTo(file);
            inputStreamToFile(originFile, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //把保存的地址：  服务器部署地址(xxxxxxxx/项目名字)/image/a/b/c/d/12321234-a.jpg  返回出去
        //错了，我们只返回 static/img/goods/a/b/c/d/12321234-a.jpg
        return relativePath;

    }


    /**
     * 通过流来传输文件
     */

    public static void inputStreamToFile(MultipartFile multipartFile, File file) throws IOException {
        FileUploadUtil.inputStreamToFile(multipartFile.getInputStream(), file);
    }


    /**
     * 通过流来传输文件
     */

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
