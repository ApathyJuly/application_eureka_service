package com.example.service_hi.wisdom_hr.editor.controller;

import com.alibaba.fastjson.JSON;
import com.example.service_hi.system_package.util.url.ApplicationURLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/editor")
public class EditorController {
    @Autowired
    private HttpServletRequest request;
    @RequestMapping("/index")
    public String toIndex(){
        return "editor/index";
    }
    @RequestMapping("/imgEditor")
    @ResponseBody
    public String  imgEditor(@RequestParam(value="imgFileName") MultipartFile mf){
        //获取图片名称
        String imgName = mf.getOriginalFilename();
        String path = null;
        //直接写入target中
        try {
            path = ResourceUtils.getURL("classpath:").getPath()+"static/staticDir/uploadImg/";
            if(!new File(path).exists()) {
                //如果没有目录应该创建目录
                new File(path).mkdirs();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //写入项目的resource中
//        path = ApplicationURLUtils.getCreateFileURL("uploadImg");
        //文件实现上传,
        File file = new File(path+ "/" + imgName);
        //写出文件
        try {
            mf.transferTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()){
            System.out.println("文件是否存在：" + file.exists());
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("data","http://localhost:8763/staticDir/uploadImg/"+imgName);
        return JSON.toJSONString(map);
    }
}
