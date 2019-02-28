package cn.mauth.crm.util.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {

    /**
     * 判断文件是否存在，不存在创建
     *
     * @param pathname
     * @return
     */
    public static String mkdirs(String pathname) {
        File file = new File(pathname);
        if (!file.exists()) {
            file.mkdirs();
        }
        return pathname;
    }

    /**
     * 复制文件
     *
     * @param
     * @param file
     * @return
     */
    public static boolean copy(MultipartFile multipartFile, File file) {
        boolean falg = false;
        OutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream(file);
            dos = new DataOutputStream(fos);
            dos.write(multipartFile.getBytes());
            dos.flush();
            falg = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null)
                    dos.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return falg;
    }
}
