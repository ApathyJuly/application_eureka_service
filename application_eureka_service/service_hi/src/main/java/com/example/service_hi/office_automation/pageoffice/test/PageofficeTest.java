package com.example.service_hi.office_automation.pageoffice.test;

//import com.zhuozhengsoft.pageoffice.FileSaver;
//import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
//import com.zhuozhengsoft.pageoffice.wordwriter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;
import java.io.*;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public class PageofficeTest {

//    public static void main(String[] args) {
//        HttpServletRequest request = null;
//        HttpServletResponse response = null;
//        getWord(request, response);
//    }
//
//    public static void getWord(HttpServletRequest request, HttpServletResponse response){
//        WordDocument doc = new WordDocument();
//        //设置内容标题
//        //创建DataRegion对象，PO_title为自动添加的书签名称,书签名称需以“PO_”为前缀，切书签名称不能重复
//        //三个参数分别为要新插入书签的名称、新书签的插入位置、相关联的书签名称（“[home]”代表Word文档的页首）
//        DataRegion title = doc.createDataRegion("PO_title", DataRegionInsertType.After, "[home]");
//        //给DataRegion对象赋值
//        title.setValue("JAVA中编程实例\n");
//        //设置字体：粗细、大小、字体名称、是否是斜体
//        title.getFont().setBold(true);
//        title.getFont().setSize(20);
//        title.getFont().setName("黑体");
//        title.getFont().setItalic(false);
//        //定义段落对象
//        ParagraphFormat titlePara = title.getParagraphFormat();
//        //设置段落对齐方式
//        titlePara.setAlignment(WdParagraphAlignment.wdAlignParagraphCenter);
//        //设置段落行间距
//        titlePara.setLineSpacingRule(WdLineSpacing.wdLineSpaceMultiple);
//        //设置内容
//        //第一段
//        //创建DataRegion对象，PO_body为自动添加的书签名称
//        DataRegion body = doc.createDataRegion("PO_body", DataRegionInsertType.After, "PO_title");
//        //设置字体：粗细、是否是斜体、大小、字体名称、字体颜色
//        body.getFont().setBold(true);
//        body.getFont().setItalic(true);
//        body.getFont().setSize(10);
//        //设置中文字体名称
//        body.getFont().setName("楷体");
//        //设置英文字体名称
//        body.getFont().setNameAscii("Times New Roman");
//        body.getFont().setColor(Color.red);
//        //给DataRegion对象赋值
//        body.setValue("首先，我向大家介绍一下套接字的概念。\n");
//        //创建ParagraphFormat对象
//        ParagraphFormat bodyPara = body.getParagraphFormat();
//        //设置段落的行间距、对齐方式、首行缩进
//        bodyPara.setLineSpacingRule(WdLineSpacing.wdLineSpaceAtLeast);
//        bodyPara.setAlignment(WdParagraphAlignment.wdAlignParagraphLeft);
//        bodyPara.setFirstLineIndent(21);
//        //插入图片
//        // PO_body3是word模板中已存在的一个书签
//        DataRegion body4 = doc.createDataRegion("PO_body4", DataRegionInsertType.After, "PO_body3");
////        body4.setValue("[image]doc/logo.png[/image]");
//        body4.setValue("[image]E:\\SpringCloudApplication\\application_eureka_service\\service_hi\\target\\classes\\static\\staticDir\\uploadImg\\sds.jpg[/image]");
//        //嵌入其他Word文件
//        //body4.Value = "[word]doc/1.doc[/word]";
//        //嵌入其他Excel文件
//        //body4.Value = "[excel]doc/1.xls[/excel]";
//        ParagraphFormat bodyPara4 = body4.getParagraphFormat();
//        bodyPara4.setAlignment(WdParagraphAlignment.wdAlignParagraphCenter);
//        //添加水印 ，设置水印的内容
//        doc.getWaterMark().setText("公司所有 侵权必究");
//
//    }
}
