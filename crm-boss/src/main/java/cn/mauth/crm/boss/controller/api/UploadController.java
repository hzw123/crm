package cn.mauth.crm.boss.controller.api;

import cn.mauth.crm.util.base.BaseController;
import cn.mauth.crm.util.common.Result;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController("/crm/v1/upload")
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
    @PostMapping(value = "/image",  produces = "application/json")
    public Result uploadImage(MultipartFile file){

        if(null == file){
            return error("上传文件不能为空");
        }

        String random = RandomStringUtils.randomAlphabetic(16);

        String fileName = random + ".jpg";

        try {
            String uploadDirName = imgLocalPath.substring(imgLocalPath.lastIndexOf("/"), imgLocalPath.length());

            FileCopyUtils.copy(file.getBytes(), new File(imgLocalPath + "/", fileName));

            return ok(imgHost + uploadDirName + "/" + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return error("上传图片失败");
    }
}
