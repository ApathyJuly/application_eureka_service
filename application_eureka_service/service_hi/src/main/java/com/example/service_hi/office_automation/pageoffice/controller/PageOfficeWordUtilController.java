package com.example.service_hi.office_automation.pageoffice.controller;

//import com.zhuozhengsoft.pageoffice.OpenModeType;
//import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/pageoffice")
public class PageOfficeWordUtilController {

//    @RequestMapping("/index")
//    public String toIndex(){
//        return "/pageoffice/index";
//    }
//
//    @RequestMapping(value="/word", method= RequestMethod.GET)
//    public ModelAndView showWord(HttpServletRequest request, Map<String,Object> map){
//        //--- PageOffice的调用代码 开始 -----
//        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
//        poCtrl.setServerPage("/poserver.zz");//设置授权程序servlet
//        poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
//        poCtrl.setSaveFilePage("/save");//设置保存的action
//        poCtrl.webOpen("D:\\test.doc", OpenModeType.docAdmin,"张三");
//        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
//        //--- PageOffice的调用代码 结束 -----
//        ModelAndView mv = new ModelAndView("Word");
//        return mv;
//    }

}
