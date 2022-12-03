package com.example.service_hi.wisdom_hr.work_date.controller;

import com.example.service_hi.wisdom_hr.work_date.entity.WorkDate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/work")
public class workDateController {
    @RequestMapping("/workDate")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/getWorkDate")
    public void getIndex(HttpServletRequest request, HttpServletResponse response){
        List<WorkDate> temp = input(request,response);
    }

    public List<WorkDate> input(HttpServletRequest request, HttpServletResponse response){
        List<WorkDate> temp = new ArrayList();
        // 转换request，解析出request中的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //获取文件
        MultipartFile excel = multipartRequest.getFile("input");
        Workbook wb0 = null;
        try {
            wb0 = new XSSFWorkbook(excel.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        for (Row r : sht0) {
            WorkDate workDate = new WorkDate();
            try {
                workDate.setEnd(r.getCell(25).getDateCellValue());
            } catch (NullPointerException e) {

            }

            temp.add(workDate);
        }
        return temp;
    }

    @RequestMapping(value = "tomap")
    public String toMap(){
        return "map/magomap/map";
    }
}
