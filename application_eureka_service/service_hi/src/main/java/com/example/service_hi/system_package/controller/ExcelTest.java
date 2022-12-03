package com.example.service_hi.system_package.controller;

import com.example.service_hi.system_package.entity.FullOfStaff;
import com.example.service_hi.system_package.util.excel.ExcelPoiUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ExcelTest {

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }
    @PostMapping("/excel")
    public void getAllExcel(HttpServletRequest request, HttpServletResponse response){
        List<FullOfStaff> list = ExcelPoiUtil.inExcel(request);
        ExcelPoiUtil.outExcels(list, "数据转换",response);
    }

    @RequestMapping
    public void gitZipExcels(HttpServletRequest request, HttpServletResponse response){

    }
}
