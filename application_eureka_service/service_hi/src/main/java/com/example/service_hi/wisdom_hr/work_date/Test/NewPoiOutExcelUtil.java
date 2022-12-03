package com.example.service_hi.wisdom_hr.work_date.Test;

import com.example.service_hi.wisdom_hr.work_date.entity.WorkDate;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class NewPoiOutExcelUtil {
    public static void main(String[] args) {
        List<WorkDate> list = input();
        output(list);
    }
    public static List<WorkDate> input(){
        List<WorkDate> temp = new ArrayList();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream= new FileInputStream(new File("E:/NX/省公司/5-10月计划作业.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Workbook wb0 = null;
        try {
            wb0 = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        for (Row r : sht0) {
            if(r.getRowNum()<1){
                continue;
            }
            WorkDate workDate = new WorkDate();
            Date dateStart = null;
            Date dateEnd = null;
            try {
                dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(r.getCell(25).getStringCellValue());
                workDate.setStart(dateStart);
            } catch (NullPointerException | ParseException e) {

            }
            try {
                dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(r.getCell(26).getStringCellValue());
                workDate.setEnd(dateEnd);
            } catch (NullPointerException | ParseException e) {

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
            wb0.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
    public static void output(List<WorkDate> list){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream= new FileInputStream(new File("E:/NX/省公司/官渡供电局人员台账.xls"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Workbook wb0 = null;
        try {
            wb0 = new HSSFWorkbook(fileInputStream);//xls格式
//            wb0 = new XSSFWorkbook(fileInputStream);//xlsx格式
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        int num = 2;
        for (Row row : sht0) {
            row=sht0.getRow(num);  //获取第三行
            //工作负责人工作次数
            String name = null;
            try {
                name = row.getCell(5).getStringCellValue();
            } catch (NullPointerException e) {
                name = "";
            }
            if(num>5){
                if("".equals(name)){
                    //名字为空时停止
                    break;
                }
            }
            int count = 0;
            int teamCount = 0;
            long oneJobTime = 0;
            long teamJobTime = 0;
            long countJobTime = 0;
            for (WorkDate workDate:list
            ) {
                String str = null;
                try {
                    str = workDate.getWorkMan();
                } catch (NullPointerException e) {
                    str = "";
                }
                if(name.equals(str)){
                    count = count+1;
                }
                String[] teamMainArry = workDate.getTeamMain().split(";");
                for (int i = 0; i < teamMainArry.length; i++) {
                    String arr = null;
                    try {
                        arr = teamMainArry[i];
                    } catch (NullPointerException e) {
                        arr = "";
                        e.printStackTrace();
                    }
                    if (name.equals(arr)){
                        teamCount = teamCount+1;
                    }
                }

            }
            //统计工作负责人；名字下的所有工作数据
            String thisName = name;
            List<WorkDate> listName = list.stream()
                    .filter(listNames->listNames.getWorkMan().equals(thisName))
                    .sorted(Comparator.comparing(WorkDate::getStart))//.reversed()倒叙排序
                    .collect(Collectors.toList());
            long hours = 0;
            long maxHours = 0;
            for (int i = 0; i < listName.size(); i++) {

                if(i==0){
                    //取出第一次数据
                    WorkDate workTime = listName.get(i);
                    long time = workTime.getEnd().getTime()-workTime.getStart().getTime();
                    hours = time / (1000 * 60 * 60);
                    maxHours = workTime.getEnd().getTime();
                }

                if(i>0){
                    //取出上一条数据
                    WorkDate lastWorkTime = listName.get(i-1);
                    //取出下一条数据，即本条数据
                    WorkDate nextWorkTime = listName.get(i);
                    if(nextWorkTime.getStart().getTime()<=maxHours&&nextWorkTime.getEnd().getTime()>=maxHours){
                        //开始时间小于或等于最后时间，结束时间大于或大于最后时间
                        long times = nextWorkTime.getEnd().getTime()-maxHours;
                        if(nextWorkTime.getEnd().getTime()>=maxHours){
                            //当前末尾时间大于最大末尾时间，赋值最大末尾时间
                            maxHours = nextWorkTime.getEnd().getTime();
                            hours = times / (1000 * 60 * 60);
                        }else if(nextWorkTime.getEnd().getTime()<maxHours){
                            //当前末尾时间小于最大末尾时间，不赋值最大末尾时间
                            hours = 0;
                        }else {
                            hours = 0;
                        }
                    }else if(nextWorkTime.getStart().getTime()>=maxHours&&nextWorkTime.getEnd().getTime()>=maxHours){
                        //开始时间和结束时间都大于最后结束时间
                        long times = nextWorkTime.getEnd().getTime()-nextWorkTime.getStart().getTime();
                        if(nextWorkTime.getEnd().getTime()>=maxHours){
                            //当前末尾时间大于最大末尾时间，赋值最大末尾时间
                            maxHours = nextWorkTime.getEnd().getTime();
                            hours = times / (1000 * 60 * 60);
                        }else if(nextWorkTime.getEnd().getTime()<maxHours){
                            //当前末尾时间小于最大末尾时间，赋值最大末尾时间
                            hours = 0;
                        }else {
                            hours = 0;
                        }
                    }else if(nextWorkTime.getStart().getTime()<=maxHours&&nextWorkTime.getEnd().getTime()<=maxHours){
                        //开始时间和结束时间都小于最大时间
                        if(nextWorkTime.getEnd().getTime()>=maxHours){
                            //当前末尾时间大于最大末尾时间，赋值最大末尾时间
                            maxHours = nextWorkTime.getEnd().getTime();
                            hours = 0;
                        }else if(nextWorkTime.getEnd().getTime()<maxHours){
                            //当前末尾时间小于最大末尾时间，赋值最大末尾时间
                            hours = 0;
                        }else {
                            hours = 0;
                        }
                    }else {
                        hours = 0;
                    }
                }
                //计算工时
                oneJobTime=oneJobTime+hours;
            }

            //统计工作组成员，该名字下的所有工作数据
            String thisTeamName = name+";";
            List<WorkDate> listTeamName = list.stream()
                    .filter(listNames->
                            (listNames.getTeamMain()+";").indexOf(thisTeamName)!=-1
                    )
                    .sorted(Comparator.comparing(WorkDate::getStart))//.reversed()倒叙排序
                    .collect(Collectors.toList());
            if(listTeamName.size()>0){
                System.out.println(listTeamName);
            }
            long maxHoursTeam = 0;
            long teamHours = 0;
            if(listTeamName.size()>0){
                if("罗燕妮".equals(name)){
                    System.out.println();
                }
                System.out.println(listTeamName);
            }
            for (int i = 0; i < listTeamName.size(); i++) {
                if(i==0){
                    //取出第一次数据
                    WorkDate workTime = listTeamName.get(i);
                    long time = workTime.getEnd().getTime()-workTime.getStart().getTime();
                    teamHours = time / (1000 * 60 * 60);
                    maxHoursTeam = workTime.getEnd().getTime();
                }

                if(i>0){
                    //取出上一条数据
                    WorkDate lastWorkTime = listTeamName.get(i-1);
                    //取出下一条数据，即本条数据
                    WorkDate nextWorkTime = listTeamName.get(i);
                    if(nextWorkTime.getStart().getTime()<=maxHoursTeam&&nextWorkTime.getEnd().getTime()>=maxHoursTeam){
                        //开始时间小于上一条记录的结束时间且结束时间大于上次记录的结束时间,去掉中间重叠时间。当前末尾时间减去以前最大末尾时间
                        long times = nextWorkTime.getEnd().getTime()-maxHoursTeam;
                        if(nextWorkTime.getEnd().getTime()>=maxHoursTeam){
                            //当前末尾时间大于最大末尾时间，赋值最大末尾时间
                            maxHoursTeam = nextWorkTime.getEnd().getTime();
                            teamHours = times / (1000 * 60 * 60);
                        }else if(nextWorkTime.getEnd().getTime()<maxHoursTeam){
                            //当前末尾时间小于最大末尾时间，赋值最大末尾时间
                            teamHours = 0;
                        }else {
                            teamHours = 0;
                        }
                    }else if(nextWorkTime.getStart().getTime()>=maxHoursTeam&&nextWorkTime.getEnd().getTime()>=maxHoursTeam){
                        //本次记录开始时间大于或等于上次记录的结束时间，结尾时间也大于上次记录结束时间,计算本次完整时长。
                        long times = nextWorkTime.getEnd().getTime()-nextWorkTime.getStart().getTime();
                        if(nextWorkTime.getEnd().getTime()>=maxHoursTeam){
                            //当前末尾时间大于最大末尾时间，赋值最大末尾时间
                            maxHoursTeam = nextWorkTime.getEnd().getTime();
                            teamHours = times / (1000 * 60 * 60);
                        }else if(nextWorkTime.getEnd().getTime()<maxHoursTeam){
                            //当前末尾时间小于最大末尾时间，赋值最大末尾时间
                            teamHours = 0;
                        }else {
                            teamHours = 0;
                        }
                    }else if(nextWorkTime.getStart().getTime()<=maxHoursTeam&&nextWorkTime.getEnd().getTime()<=maxHoursTeam){
                        //本次记录开始时间和结束时间都小于或等于上次记录的结束时间,本次记录不计算
                        if(nextWorkTime.getEnd().getTime()>=maxHoursTeam){
                            //当前末尾时间大于最大末尾时间，赋值最大末尾时间
                            maxHoursTeam = nextWorkTime.getEnd().getTime();
                            teamHours = 0;
                        }else if(nextWorkTime.getEnd().getTime()<maxHoursTeam){
                            //当前末尾时间小于最大末尾时间，赋值最大末尾时间
                            teamHours = 0;
                        }else {
                            teamHours = 0;
                        }
                    }else {
                        teamHours = 0;
                    }
                }
                //计算工时
                teamJobTime=teamJobTime+teamHours;
            }
            //计算总时长
            List<WorkDate> listcountNames = list.stream()
                    .filter(listcountName->
                            listcountName.getWorkMan().equals(thisName)||(listcountName.getTeamMain()+";").indexOf(thisTeamName)!=-1
                    )
                    .sorted(Comparator.comparing(WorkDate::getStart))//.reversed()倒叙排序
                    .collect(Collectors.toList());
            if(listcountNames.size()>0){
                if("罗燕妮".equals(name)){
                    System.out.println(listcountNames);
                }
            }
            long countHours = 0;
            long maxHoursCount = 0;
            for (int i = 0; i < listcountNames.size(); i++) {
                if(i==0){
                    //取出第一次数据
                    WorkDate workTime = listcountNames.get(i);
                    long time = workTime.getEnd().getTime()-workTime.getStart().getTime();
                    countHours = time / (1000 * 60 * 60);
                    maxHoursCount = workTime.getEnd().getTime();
                }

                if(i>0){
                    //取出上一条数据
                    WorkDate lastWorkTime = listcountNames.get(i-1);
                    //取出下一条数据，即本条数据
                    WorkDate nextWorkTime = listcountNames.get(i);
                    if(nextWorkTime.getStart().getTime()<=maxHoursCount&&nextWorkTime.getEnd().getTime()>=maxHoursCount){
                        //开始时间小于上一条记录的结束时间且结束时间大于上次记录的结束时间,去掉中间重叠时间。当前末尾时间减去以前最大末尾时间
                        long times = nextWorkTime.getEnd().getTime()-maxHoursCount;
                        if(nextWorkTime.getEnd().getTime()>=maxHoursCount){
                            //当前末尾时间大于最大末尾时间，赋值最大末尾时间
                            maxHoursCount = nextWorkTime.getEnd().getTime();
                            countHours = times / (1000 * 60 * 60);
                        }else if(nextWorkTime.getEnd().getTime()<maxHoursCount){
                            //当前末尾时间小于最大末尾时间，赋值最大末尾时间
                            countHours = 0;
                        }else {
                            countHours = 0;
                        }
                    }else if(nextWorkTime.getStart().getTime()>=maxHoursCount&&nextWorkTime.getEnd().getTime()>=maxHoursCount){
                        //本次记录开始时间大于或等于上次记录的结束时间，结尾时间也大于上次记录结束时间,计算本次完整时长。
                        long times = nextWorkTime.getEnd().getTime()-nextWorkTime.getStart().getTime();
                        if(nextWorkTime.getEnd().getTime()>=maxHoursCount){
                            //当前末尾时间大于最大末尾时间，赋值最大末尾时间
                            maxHoursCount = nextWorkTime.getEnd().getTime();
                            countHours = times / (1000 * 60 * 60);
                        }else if(nextWorkTime.getEnd().getTime()<maxHoursCount){
                            //当前末尾时间小于最大末尾时间，赋值最大末尾时间
                            countHours = 0;
                        }else {
                            countHours = 0;
                        }
                    }else if(nextWorkTime.getStart().getTime()<=maxHoursCount&&nextWorkTime.getEnd().getTime()<=maxHoursCount){
                        //本次记录开始时间和结束时间都小于或等于上次记录的结束时间,本次记录不计算
                        if(nextWorkTime.getEnd().getTime()>=maxHoursCount){
                            //当前末尾时间大于最大末尾时间，赋值最大末尾时间
                            maxHoursCount = nextWorkTime.getEnd().getTime();
                            countHours = 0;
                        }else if(nextWorkTime.getEnd().getTime()<maxHoursCount){
                            //当前末尾时间小于最大末尾时间，赋值最大末尾时间
                            countHours = 0;
                        }else {
                            countHours = 0;
                        }
                    }else {
                        countHours = 0;
                    }
                }
                //计算工时
                countJobTime=countJobTime+countHours;
            }


            try {
                row.createCell(8).setCellValue(count); //设置第9个（从0开始）单元格的数据
            } catch (NullPointerException e) {
                System.out.println(count);
            }
            //工作成员工作次数
            try {
                row.createCell(9).setCellValue(teamCount); //设置第10个（从0开始）单元格的数据
            } catch (NullPointerException e) {

            }
            try {
                row.createCell(10).setCellValue(oneJobTime); //设置第11个（从0开始）单元格的数据
                row.createCell(11).setCellValue(teamJobTime); //设置第12个（从0开始）单元格的数据
                row.createCell(12).setCellValue(countJobTime); //设置第13个（从0开始）单元格的数据
            } catch (NullPointerException e) {

            }
            num++;
            System.out.println(num+"条记录");
        }
        FileOutputStream fos = null;
        String fileName = "E:/NX/省公司/人员工作绩效总和统计";
        File file = null;
        for (int i = 1; i < 100; i++) {
            file = new File(fileName+".xls");
            if (!file.exists()) {
                break;
            }
            fileName = "E:/NX/省公司/人员工作绩效总和统计";
            fileName = fileName+"("+i+")";
        }
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
                wb0.write(fos);
                fos.close();
                wb0.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {

            }
        }

    }
    /**
     * 合并单元格
     */
    private static void mergedRegion(int firstRow, int lastRow, int firstCol, int lastCol, Sheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }
}
