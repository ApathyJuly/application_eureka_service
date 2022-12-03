package com.example.service_hi.wisdom_hr.work_date.Test;

import com.example.service_hi.wisdom_hr.work_date.entity.WorkDate;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BigDataExcelRead {
    public static void main(String[] args) {
        List<WorkDate> list = input();
        Map<String,List<WorkDate>> type = list.stream()
                .collect(Collectors.groupingBy(WorkDate::getWorkType));
        output(type);
    }

    /**
     *Excel Streaming Reader 读取大量excel数据
     * @return
     */
    private static List<WorkDate> input() {
        final int WORK_BOOK_LINE = 100;
        final int WORK_BOOK_SIZE = 4096;
        long start = System.currentTimeMillis();
        List<WorkDate> temp = new ArrayList();
        String inName = "E:/NX/省公司/工作时长/5-10月计划作业2.0.xlsx";
        FileInputStream in = null;
        try {
            in = new FileInputStream(inName);
        } catch (FileNotFoundException e) {
            System.err.println("资源文件不存在！");
        }
        Workbook wk = StreamingReader.builder()
                .rowCacheSize(WORK_BOOK_LINE)  //缓存到内存中的行数，默认是10
                .bufferSize(WORK_BOOK_SIZE)  //读取资源时，缓存到内存的字节大小，默认是1024
                .open(in);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
        //获取Excel文档中的第一个表单
        System.out.println("正在获取excel文件sheet页。。。。");
        Sheet sht0 = wk.getSheetAt(0);
        System.out.println("sheet页获取结束");
        WorkDate workDate;
        for (Row r : sht0) {
            if(r.getRowNum()<1){
                continue;
            }
            workDate = new WorkDate();
            Date dateStart = null;
            Date dateEnd = null;
            Date zero = null;
            try {
                zero = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-1-1 08:00:00");
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            try {
                dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(r.getCell(25).getStringCellValue());
                workDate.setStart(dateStart);
            } catch (NullPointerException | ParseException e) {
                workDate.setStart(zero);
            }
            try {
                dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(r.getCell(26).getStringCellValue());
                workDate.setEnd(dateEnd);
            } catch (NullPointerException | ParseException e) {
                workDate.setEnd(zero);
            }
            try {
                workDate.setWorkMan(r.getCell(31).getStringCellValue());
            } catch (NullPointerException e) {
                workDate.setWorkMan("");
            }
            try {
                workDate.setTeamMain(r.getCell(32).getStringCellValue());
            } catch (NullPointerException e) {
                workDate.setTeamMain("");
            }
            try {
                workDate.setWorkType(r.getCell(13).getStringCellValue());
            } catch (NullPointerException e) {
                workDate.setWorkType("");
            }
            temp.add(workDate);
        }
        try {
            wk.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("加载时长：..."+(end-start)/1000+"秒");
        return temp;
    }

    public static void output(Map<String,List<WorkDate>> type){
        //1、创建workbook，对应一个excel
        Workbook wb = new XSSFWorkbook();
        //生成excel中可能用到的单元格样式
        //首先创建字体样式
        Font font = wb.createFont();//创建字体样式
        font.setFontName("宋体");//使用宋体
        font.setFontHeightInPoints((short) 10);//字体大小
        font.setBold(true);//poi4.0加粗
        //然后创建单元格样式style
        CellStyle style1 = wb.createCellStyle();
        style1.setFont(font);//将字体注入
        style1.setWrapText(true);// 自动换行
        style1.setAlignment(HorizontalAlignment.CENTER);//左右居中 poi4.0 HorizontalAlignment.CENTER
        style1.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中 poi4.0
        style1.setFillForegroundColor(IndexedColors.TURQUOISE.getIndex());// 设置单元格的背景颜色,x46.
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置背景poi4.0
        style1.setBorderTop(BorderStyle.THIN);// 设置单元格上边框
        style1.setBorderBottom(BorderStyle.THIN);// 下边框
        style1.setBorderLeft(BorderStyle.THIN);// 左边框
        style1.setBorderRight(BorderStyle.THIN);// 右边框
        //然后创建单元格样式style2
        CellStyle style2 = wb.createCellStyle();
        style2.setBorderTop(BorderStyle.THIN);// 设置单元格上边框
        style2.setBorderBottom(BorderStyle.THIN);// 下边框
        style2.setBorderLeft(BorderStyle.THIN);// 左边框
        style2.setBorderRight(BorderStyle.THIN);// 右边框
        //2、生成一个sheet，对应excel的sheet，参数为excel中sheet显示的名字
        Sheet sheet = wb.createSheet("数据");
        //3、设置sheet中每列的宽度，第一个参数为第几列，0为第一列；第二个参数为列的宽度，可以设置为0。
        sheet.setColumnWidth(0, 20*256);
        sheet.setColumnWidth(1, 20*256);
        sheet.setColumnWidth(2, 20*256);
        sheet.setColumnWidth(3, 20*256);
        //4、生成sheet中一行，从0开始
        Row row = sheet.createRow(0);
        row.setHeight((short) (30*20));//列宽30
        // 5、创建row中的单元格，从0开始
        Cell cell = row.createCell(0);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式
        cell = row.createCell(0);//从第1(A)个单元格开始，设置每个单元格样式
        cell.setCellValue("工作类型");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式
        cell = row.createCell(1);//第三个单元格
        cell.setCellValue("工作次数");
        cell.setCellStyle(style1);
        cell = row.createCell(2);//第四个单元格
        cell.setCellValue("工作总时长");
        cell.setCellStyle(style1);
        cell = row.createCell(3);//第五个单元格
        cell.setCellValue("平均时长");
        cell.setCellStyle(style1);
        final int[] num = {1};
        for(Map.Entry<String, List<WorkDate>> entry : type.entrySet()){
            String key= entry.getKey();
            List<WorkDate> values = entry.getValue();
            Row rows = sheet.createRow(num[0]);
            rows.setHeight((short) 600);
            Cell cells = rows.createCell(num[0]);
            cells = rows.createCell(0);
            cells.setCellValue(key);
            cells.setCellStyle(style2);
            //次数
            cells = rows.createCell(1);
            cells.setCellValue(values.size());
            cells.setCellStyle(style2);
            //总时长
            Double timeCount = new Double(0);
            Double hours = new Double(0);
            if(values.size()>0){
                for (WorkDate work:values
                ) {
                    if(work.getEnd().getTime()<work.getStart().getTime()){
                        System.out.println(work);
                    }
                    if(work.getEnd().getTime()==0||work.getStart().getTime()==0){
                        continue;
                    }
                    long time = work.getEnd().getTime()-work.getStart().getTime();
                    hours = Double.valueOf(time / (1000 * 60 * 60));
                    timeCount = timeCount+hours;
                }
            }
            cells = rows.createCell(2);
            cells.setCellValue(timeCount);
            cells.setCellStyle(style2);
            double averageTime = (double)timeCount/(double)(values.size());
            DecimalFormat df = new DecimalFormat("0.00");//格式化小数
            String str = df.format(averageTime);
            double d = Double.valueOf(str);


            if(values.size()==0){
                cells = rows.createCell(3);
                cells.setCellValue(0);
                cells.setCellStyle(style2);
            }else {
                cells = rows.createCell(3);
                cells.setCellValue(d);
                cells.setCellStyle(style2);
            }
            num[0]++;
        }
//        type.forEach((key,values)->{
//            Row rows = sheet.createRow(num[0]);
//            rows.setHeight((short) 600);
//            Cell cells = rows.createCell(num[0]);
//            cells = rows.createCell(0);
//            cells.setCellValue(key);
//            cells.setCellStyle(style2);
//            //次数
//            cells = rows.createCell(1);
//            cells.setCellValue(values.size());
//            cells.setCellStyle(style2);
//            //总时长
//            Double timeCount = new Double(0);
//            Double hours = new Double(0);
////            long timeCount = 0;
////            long hours = 0;
//            if(values.size()>0){
//                for (WorkDate work:values
//                ) {
//                    long time = work.getEnd().getTime()-work.getStart().getTime();
//                    hours = Double.valueOf(time / (1000 * 60 * 60));
//                    timeCount = timeCount+hours;
//                }
//            }
//            cells = rows.createCell(2);
//            cells.setCellValue(timeCount);
//            cells.setCellStyle(style2);
//            double averageTime = (double)timeCount/(double)(values.size());
//            DecimalFormat df = new DecimalFormat("0.00");//格式化小数
//            String str = df.format(averageTime);
//            double d = Double.valueOf(str);
//
//
//            if(values.size()==0){
//                cells = rows.createCell(3);
//                cells.setCellValue(0);
//                cells.setCellStyle(style2);
//            }else {
//                cells = rows.createCell(3);
//                cells.setCellValue(d);
//                cells.setCellStyle(style2);
//            }
//            num[0]++;
//        });

//        String fileName = "";
//        try {
//            fileName = new String("E:/NX/省公司/工作类型统计".getBytes("UTF-8"), "ISO_8859_1");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }
        String fileName = "E:/NX/省公司/工作类型统计";
        File file = null;
        for (int i = 1; i < 100; i++) {
            file = new File(fileName+".xlsx");
            if (!file.exists()) {
                break;
            }
            fileName = "E:/NX/省公司/工作类型统计";
            fileName = fileName+"("+i+")";
        }

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                wb.write(fos);
                fos.flush();
                fos.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {

            }
        }
    }
}
