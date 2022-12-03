package com.example.service_hi.system_package.util.excel;

import com.example.service_hi.system_package.entity.FullOfStaff;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelPoiUtil {
    public static List<FullOfStaff> inExcel(HttpServletRequest request){
        List temp = new ArrayList();
        // 转换request，解析出request中的文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //获取文件
        MultipartFile excel = multipartRequest.getFile("myFile");
        Workbook wb0 = null;
        try {
            wb0 = new XSSFWorkbook(excel.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if(r.getRowNum()<1){
                continue;
            }
            //创建实体类
            FullOfStaff fullOfStaff = new FullOfStaff();
            //id
            try {
                fullOfStaff.setWorkSteamHid(r.getCell(9).getStringCellValue());
            } catch (NullPointerException e) {
                fullOfStaff.setWorkSteamHid("");
            }
            //是否一线班组
            try {
                fullOfStaff.setIsFrontLine(r.getCell(4).getStringCellValue());
            } catch (NullPointerException e) {
                fullOfStaff.setIsFrontLine("");
            }
            //二级分类
            try {
                fullOfStaff.setMajorType(r.getCell(5).getStringCellValue());
            } catch (NullPointerException e) {
                fullOfStaff.setMajorType("");
            }
            //在岗数
            try {
                fullOfStaff.setOnDutyNum((int)r.getCell(6).getNumericCellValue());
            } catch (NullPointerException e) {
                fullOfStaff.setOnDutyNum(0);
            }
            //定编数
            try {
                fullOfStaff.setStaffNum((int)r.getCell(7).getNumericCellValue());
            } catch (NullPointerException e) {
                fullOfStaff.setStaffNum(0);
            }
            //班组名称
            try {
                fullOfStaff.setWorkName(r.getCell(8).getStringCellValue());
            } catch (NullPointerException e) {
                fullOfStaff.setWorkName("");
            }
            //一级分类
            try {
                fullOfStaff.setMajorOneLevel(r.getCell(10).getStringCellValue());
            } catch (NullPointerException e) {
                fullOfStaff.setMajorOneLevel("");
            }
            //部门路径
            try {
                fullOfStaff.setOrgPath(r.getCell(11).getStringCellValue());
            } catch (NullPointerException e) {
                fullOfStaff.setOrgPath("");
            }

            temp.add(fullOfStaff);
        }
        return temp;
    }

    public static void outExcel(List<FullOfStaff> list, HttpServletResponse response){
        //1、创建workbook，对应一个excel
        Workbook wb = new XSSFWorkbook();
        //1-5、生成excel中可能用到的单元格样式
        //首先创建字体样式
        Font font = wb.createFont();//创建字体样式
        font.setFontName("宋体");//使用宋体
        font.setFontHeightInPoints((short) 10);//字体大小
//        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// poi3.12加粗
        font.setBold(true);//poi4.0加粗
        //然后创建单元格样式style
        CellStyle style1 = wb.createCellStyle();
        style1.setFont(font);//将字体注入
        style1.setWrapText(true);// 自动换行
//        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 左右居中，3.12XSSFCellStyle.ALIGN_CENTER
        style1.setAlignment(HorizontalAlignment.CENTER);//左右居中 poi4.0 HorizontalAlignment.CENTER
//        style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 上下居中poi.3.12
        style1.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中 poi4.0
        style1.setFillForegroundColor(IndexedColors.TURQUOISE.getIndex());// 设置单元格的背景颜色,x46.
//        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置背景poi3.12
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置背景poi4.0
//        style1.setBorderTop((short) 1);// 上边框的大小
//        style1.setBorderBottom((short) 1);// 下边框的大小
//        style1.setBorderLeft((short) 1);// 左边框的大小
//        style1.setBorderRight((short) 1);// 右边框的大小
        style1.setBorderTop(BorderStyle.THIN);// 上边框的大小
        style1.setBorderBottom(BorderStyle.THIN);// 下边框的大小
        style1.setBorderLeft(BorderStyle.THIN);// 左边框的大小
        style1.setBorderRight(BorderStyle.THIN);// 右边框的大小
        //2、生成一个sheet，对应excel的sheet，参数为excel中sheet显示的名字
        Sheet sheet = wb.createSheet("数据");
        //3、设置sheet中每列的宽度，第一个参数为第几列，0为第一列；第二个参数为列的宽度，可以设置为0。
        sheet.setColumnWidth(0, 20*256);
        sheet.setColumnWidth(1, 20*256);
        sheet.setColumnWidth(2, 20*256);
        sheet.setColumnWidth(3, 20*256);
        sheet.setColumnWidth(4, 20*256);
        sheet.setColumnWidth(5, 20*256);
        sheet.setColumnWidth(6, 20*256);
        sheet.setColumnWidth(7, 20*256);
        sheet.setColumnWidth(8, 20*256);
        //4、生成sheet中一行，从0开始
        Row row = sheet.createRow(0);
        row.setHeight((short) 600);
        // 设定行的高度
        // 5、创建row中的单元格，从0开始
        Cell cell = row.createCell(0);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式
        cell = row.createCell(0);//从第1(A)个单元格开始，设置每个单元格样式
        cell.setCellValue("机构/部门");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式
        cell = row.createCell(1);//第三个单元格
        cell.setCellValue("是否一线班组");
        cell.setCellStyle(style1);
        cell = row.createCell(2);//第四个单元格
        cell.setCellValue("班组专业分类");
        cell.setCellStyle(style1);
        cell = row.createCell(3);//第五个单元格
        cell.setCellValue("定编人数");
        cell.setCellStyle(style1);
        cell = row.createCell(4);//第六个单元格
        cell.setCellValue("在岗人数");
        cell.setCellStyle(style1);
        cell = row.createCell(5);//第七个单元格
        cell.setCellValue("状态（缺员/超员）");
        cell.setCellStyle(style1);
        cell = row.createCell(6);//第八个单元格
        cell.setCellValue("超员/缺员人数");
        cell.setCellStyle(style1);
        cell = row.createCell(7);//第九个单元格
        cell.setCellValue("超员/缺员率");
        cell.setCellStyle(style1);
        cell = row.createCell(8);//第十个单元格
        cell.setCellValue("统计时间");
        cell.setCellStyle(style1);
        int num = 1;
        for (FullOfStaff staff:list
        ) {
            Row rows = sheet.createRow(num);
            rows.setHeight((short) 600);
            Cell cells = rows.createCell(num);
            cells = rows.createCell(0);
            cells.setCellValue(staff.getOrgPath());//设置单位单元格中内容
            cells = rows.createCell(1);
            cells.setCellValue(staff.getIsFrontLine());//是否一线班组
            cells = rows.createCell(2);
            cells.setCellValue(staff.getMajorType());//班组专业分类
            cells = rows.createCell(3);
            cells.setCellValue(staff.getStaffNum());//定编人数
            cells = rows.createCell(4);
            cells.setCellValue(staff.getOnDutyNum());//在岗人数
            if(staff.getStaffNum()>staff.getOnDutyNum()){
                cells = rows.createCell(5);
                cells.setCellValue("缺员");//状态（缺员/超员）
                cells = rows.createCell(6);
                cells.setCellValue(staff.getStaffNum()-staff.getOnDutyNum());//超员/缺员人数
                cells = rows.createCell(7);
                cells.setCellValue(certRateCalculate(staff.getStaffNum()-staff.getOnDutyNum(),staff.getStaffNum())+"%");//超员/缺员率
            }else if (staff.getStaffNum()<staff.getOnDutyNum()){
                cells = rows.createCell(5);
                cells.setCellValue("超员");//状态（缺员/超员）
                cells = rows.createCell(6);
                cells.setCellValue(staff.getOnDutyNum()-staff.getStaffNum());//超员/缺员人数
                cells = rows.createCell(7);
                cells.setCellValue(certRateCalculate(staff.getOnDutyNum()-staff.getStaffNum(),staff.getStaffNum())+"%");//超员/缺员率
            }
            cells = rows.createCell(8);
            cells.setCellValue("无");//统计时间
            num++;
        }
        //8、输入excel
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            String filename = "数据处理导出.xlsx";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename,"UTF-8"));
            wb.write(outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void outExcels(List<FullOfStaff> list,String fileName, HttpServletResponse response){
        //1、创建workbook，对应一个excel
        Workbook wb = new XSSFWorkbook();
        //1-5、生成excel中可能用到的单元格样式
        //首先创建字体样式
        Font font = wb.createFont();//创建字体样式
        font.setFontName("宋体");//使用宋体
        font.setFontHeightInPoints((short) 10);//字体大小
        font.setBold(true);//poi4.0加粗
        //然后创建单元格样式style
        CellStyle style1 = wb.createCellStyle();
        style1.setFont(font);//将字体注入
        style1.setWrapText(true);// 自动换行
        style1.setAlignment(HorizontalAlignment.CENTER);//左右居中 poi4.0 HorizontalAlignment.CENTER 水平居中
        style1.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中 poi4.0 垂直居中
        style1.setFillForegroundColor(IndexedColors.TURQUOISE.getIndex());// 设置单元格的背景颜色,x46.
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置背景固定全景 poi4.0,
        style1.setBorderTop(BorderStyle.THIN);// 上边框的大小
        style1.setBorderBottom(BorderStyle.THIN);// 下边框的大小
        style1.setBorderLeft(BorderStyle.THIN);// 左边框的大小
        style1.setBorderRight(BorderStyle.THIN);// 右边框的大小
        //2、生成一个sheet，对应excel的sheet，参数为excel中sheet显示的名字
        Sheet sheet = wb.createSheet(fileName);
        /**
         * 冻结窗格
         * 第一个参数表示要冻结的列数，0
         *第二个参数表示要冻结的行数，,1
         *第三个参数表示右边区域可见的首列序号，从0开始计算；
         *第四个参数表示下边区域可见的首行序号，也是从1开始计算，这里是冻结列，所以为1
         */
        sheet.createFreezePane( 0, 1, 0, 1 );
        //给sheet添加筛选功能
        CellRangeAddress screenings = CellRangeAddress.valueOf("A1:O1");
        sheet.setAutoFilter(screenings);
        //3、设置sheet中每列的宽度，第一个参数为第几列，0为第一列；第二个参数为列的宽度，可以设置为0。
        sheet.setColumnWidth(0, 20*256);
        sheet.setColumnWidth(1, 20*256);
        sheet.setColumnWidth(2, 20*256);
        sheet.setColumnWidth(3, 20*256);
        sheet.setColumnWidth(4, 20*256);
        sheet.setColumnWidth(5, 20*256);
        sheet.setColumnWidth(6, 20*256);
        sheet.setColumnWidth(7, 20*256);
        sheet.setColumnWidth(8, 20*256);
        sheet.setColumnWidth(9, 20*256);
        sheet.setColumnWidth(10, 20*256);
        sheet.setColumnWidth(11, 20*256);
        sheet.setColumnWidth(12, 20*256);
        sheet.setColumnWidth(13, 20*256);
        sheet.setColumnWidth(14, 20*256);
        //4、生成sheet中一行，从0开始
        Row row = sheet.createRow(0);
        row.setHeight((short) 600);
        // 设定行的高度
        // 5、创建row中的单元格，从0开始
        Cell cell = row.createCell(0);//我们第一列设置宽度为0，不会显示，因此第0个单元格不需要设置样式
        cell = row.createCell(0);//从第1(A)个单元格开始，设置每个单元格样式
        cell.setCellValue("机构/部门一级");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式
        cell = row.createCell(1);//从第1(A)个单元格开始，设置每个单元格样式
        cell.setCellValue("机构/部门二级");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式
        cell = row.createCell(2);//从第1(A)个单元格开始，设置每个单元格样式
        cell.setCellValue("机构/部门三级");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式
        cell = row.createCell(3);//从第1(A)个单元格开始，设置每个单元格样式
        cell.setCellValue("机构/部门四级");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式
        cell = row.createCell(4);//从第1(A)个单元格开始，设置每个单元格样式
        cell.setCellValue("机构/部门五级");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式
        cell = row.createCell(5);//从第1(A)个单元格开始，设置每个单元格样式
        cell.setCellValue("机构/部门六级");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式
        cell = row.createCell(6);//从第1(A)个单元格开始，设置每个单元格样式
        cell.setCellValue("机构/部门七级");//设置单元格中内容
        cell.setCellStyle(style1);//设置单元格样式
        cell = row.createCell(7);//第三个单元格
        cell.setCellValue("是否一线班组");
        cell.setCellStyle(style1);
        cell = row.createCell(8);//第四个单元格
        cell.setCellValue("班组专业分类");
        cell.setCellStyle(style1);
        cell = row.createCell(9);//第五个单元格
        cell.setCellValue("定编人数");
        cell.setCellStyle(style1);
        cell = row.createCell(10);//第六个单元格
        cell.setCellValue("在岗人数");
        cell.setCellStyle(style1);
        cell = row.createCell(11);//第七个单元格
        cell.setCellValue("状态（缺员/超员）");
        cell.setCellStyle(style1);
        cell = row.createCell(12);//第八个单元格
        cell.setCellValue("超员/缺员人数");
        cell.setCellStyle(style1);
        cell = row.createCell(13);//第九个单元格
        cell.setCellValue("超员/缺员率");
        cell.setCellStyle(style1);
        cell = row.createCell(14);//第十个单元格
        cell.setCellValue("统计时间");
        cell.setCellStyle(style1);
        int num = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(new Date());
        for (FullOfStaff staff:list
        ) {
            Row rows = sheet.createRow(num);
            rows.setHeight((short) 600);
            Cell cells = rows.createCell(num);
            String [] orgPathArrays = staff.getOrgPath().split("-");
            int i = 0;
            for (i = 0; i < orgPathArrays.length; i++) {
                cells = rows.createCell(i);
                cells.setCellValue(orgPathArrays[i]);//设置单位单元格中内容
            }
            i = 6;
            cells = rows.createCell(i+1);
            cells.setCellValue(staff.getIsFrontLine());//是否一线班组
            cells = rows.createCell(i+2);
            cells.setCellValue(staff.getMajorType());//班组专业分类
            cells = rows.createCell(i+3);
            cells.setCellValue(staff.getStaffNum());//定编人数
            cells = rows.createCell(i+4);
            cells.setCellValue(staff.getOnDutyNum());//在岗人数
            if(staff.getStaffNum()>staff.getOnDutyNum()){
                cells = rows.createCell(i+5);
                cells.setCellValue("缺员");//状态（缺员/超员）
                cells = rows.createCell(i+6);
                cells.setCellValue(staff.getStaffNum()-staff.getOnDutyNum());//超员/缺员人数
                cells = rows.createCell(i+7);
                cells.setCellValue(certRateCalculate(staff.getStaffNum()-staff.getOnDutyNum(),staff.getStaffNum())+"%");//超员/缺员率
            }else if (staff.getStaffNum()<staff.getOnDutyNum()){
                cells = rows.createCell(i+5);
                cells.setCellValue("超员");//状态（缺员/超员）
                cells = rows.createCell(i+6);
                cells.setCellValue(staff.getOnDutyNum()-staff.getStaffNum());//超员/缺员人数
                cells = rows.createCell(i+7);
                cells.setCellValue(certRateCalculate(staff.getOnDutyNum()-staff.getStaffNum(),staff.getStaffNum())+"%");//超员/缺员率
            }else if (staff.getStaffNum()==staff.getOnDutyNum()){
                cells = rows.createCell(i+5);
                cells.setCellValue("整编满员");//状态（缺员/超员）
                cells = rows.createCell(i+6);
                cells.setCellValue(0);//超员/缺员人数
                cells = rows.createCell(i+7);
                cells.setCellValue("0.00%");//超员/缺员率
            }
            cells = rows.createCell(i+8);
            cells.setCellValue(dateStr);//统计时间
            num++;
        }
        //8、输入excel
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            String filename = fileName+".xlsx";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename,"UTF-8"));
            wb.write(outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 合并单元格
     */
    private static void mergedRegion(int firstRow, int lastRow, int firstCol, int lastCol, Sheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    public static String certRateCalculate(Integer alreadyNum, Integer shouldNum){
        if(alreadyNum==0||shouldNum==0||alreadyNum==null||shouldNum==null){
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(((float)alreadyNum/shouldNum) * 100);
    }

}
