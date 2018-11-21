package com.min.controller;

import com.min.common.utils.FastDFSClient;
import com.min.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName PictureController.java
 * @Description ok
 * @createTime 2018年11月20日 20:50:00
 */
@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    //设置响应结果的text-type
    @RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String FileUpload(MultipartFile uploadFile){

        try {
            //1获取文件的扩展名
            String filename = uploadFile.getOriginalFilename();
            String substring = filename.substring(filename.lastIndexOf(".") + 1);
            //2创建一个FastDFS
            FastDFSClient dfsClient = new FastDFSClient("classpath:conf/client.conf");
            //3 执行上传处理
            String path = dfsClient.uploadFile(uploadFile.getBytes(), substring);
            //拼接返回的URI和IP地址。
            String url=IMAGE_SERVER_URL+path;
            Map<String,Object> map=new HashMap<>();
            map.put("error",0);
            map.put("url",url);
            return JsonUtils.objectToJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String,Object> map=new HashMap<>();
            map.put("error",0);
            map.put("url","图片上传失败");
            return JsonUtils.objectToJson(map);
        }
    }
}
