package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.FileUtil;
import cn.mauth.crm.util.common.HexUtil;
import cn.mauth.crm.util.common.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("/crm/v1/up-down")
public class UploadController extends BaseController{

    //文件存储路径
    @Value("${img.local.path}")
    private String imgLocalPath;
    //文件网络访问路径
    @Value("${img.host}")
    private String imgHost;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping(value = "/file",  produces = "application/json")
    @ApiOperation("上传文件")
    public Result upload(MultipartFile file){

        if(null == file){
            return error("上传文件不能为空");
        }

        String random = RandomStringUtils.randomAlphabetic(16);

        String fileName = file.getOriginalFilename();

        String type=fileName.substring(fileName.lastIndexOf(".")+1);

        fileName=random+"_"+fileName;

        try {
            String uploadDirName = imgLocalPath.substring(imgLocalPath.lastIndexOf("/"), imgLocalPath.length());

            File upFile=new File(FileUtil.mkdirs(imgLocalPath + "/"+type)+"/"+fileName);

            FileCopyUtils.copy(file.getBytes(), upFile);

            String path=uploadDirName + "/"+type+"/" + fileName;

            path= HexUtil.encode(path);

            return ok(imgHost +"/"+ path);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return error("上传图片失败");
    }

    @GetMapping(value = "/image/{path}",produces = {"application/force-download;charset=UTF-8"})
    @ApiOperation("下载文件")
    public Result download(@PathVariable String path, HttpServletResponse response){

        path=HexUtil.decode(path);

        String filePath = imgLocalPath.substring(0,imgLocalPath.lastIndexOf("/"));

        File file = new File(filePath+path);

        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开

            response.setHeader("Cache-Control", "max-age=604800"); //设置缓存

            String fileName=path.substring(path.lastIndexOf("_")+1,path.length());

            response.addHeader("Content-Disposition",
                    "attachment;fileName=" + fileName);// 设置文件名

            byte[] buffer = new byte[1024];

            FileInputStream fis = null;

            BufferedInputStream bis = null;
            try {

                fis = new FileInputStream(file);

                bis = new BufferedInputStream(fis);

                OutputStream os = response.getOutputStream();

                int i = bis.read(buffer);

                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

                return ok();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return error("");
    }

}
