package com.example.service_hi.wisdom_hr.work_date.Test.word;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputWordFile {
    public static void main(String[] args) {
        pageOfficeUtil();
    }
    public static void pageOfficeUtil(){
        //创建word对象
        XWPFDocument document = new XWPFDocument();

        for (int i = 1; i < 5; i++) {
            //创建题目。创建段落
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.LEFT);//设置对齐方式,左对齐
//            paragraph.setFirstLineIndent(500);//首行缩进。+500
            XWPFRun run = paragraph.insertNewRun(0);
            run.setFontFamily("宋体");
            run.setFontSize(14);//14磅对应四号，12pt对应小四
            run.setText(i+"、（）这一个段落是题目内容");
//            run.addBreak();//换行

            //创建答案a
            XWPFParagraph paragraphs = document.createParagraph();
            paragraphs.setAlignment(ParagraphAlignment.LEFT);//设置对齐方式,左对齐
            paragraphs.setFirstLineIndent(500);//首行缩进。+500
            XWPFRun runA = paragraphs.insertNewRun(0);
            runA.setFontFamily("宋体");
            runA.setFontSize(12);//14磅对应四号，12pt对应小四
            runA.setText("A、这个选项是错的"+i);
//            runA.addBreak();//换行

            XWPFParagraph paragraphB = document.createParagraph();
            paragraphB.setAlignment(ParagraphAlignment.LEFT);//设置对齐方式,左对齐
            paragraphB.setFirstLineIndent(500);//首行缩进。+500
            XWPFRun runB = paragraphB.insertNewRun(0);
            runB.setFontFamily("宋体");
            runB.setFontSize(12);//14磅对应四号，12pt对应小四
            runB.setText("B、这个选项是错的"+i);
//            runB.addBreak();//换行

        }

        XWPFParagraph paragraphJpg = document.createParagraph();
        XWPFRun runJpg = paragraphJpg.createRun();

        FileInputStream is = null;
        try {
            is = new FileInputStream("E:\\NX\\安规考试\\新建文件夹\\ceshi.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        runJpg.addBreak();
        try {
            runJpg.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, "ceshi.jpg", Units.toEMU(200), Units.toEMU(200)); // 添加图片，格式jpg200x200 pixels
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        runJpg.setText("这是真的狗！");

        //网页导出
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/msword");
//        response.setHeader("Content-disposition","filename=demo.doc");

        //本地导出
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("E:/NX/安规考试/导出word文件.docx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            document.write(fos);
        } catch (IOException e) {
            System.out.println("ee");
            e.printStackTrace();
        }finally {
            try {
                fos.close();
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
