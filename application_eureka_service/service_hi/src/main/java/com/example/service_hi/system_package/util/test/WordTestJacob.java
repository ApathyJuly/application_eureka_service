package com.example.service_hi.system_package.util.test;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.text.NumberFormat;
import java.util.Date;

public class WordTestJacob {
    public static void main(String[] args) {
        getWord();
//        getRatio();
    }

    public static void getWord(){
        // 初始化com的线程，非常重要！！使用结束后要调用 realease方法
        ComThread.InitSTA();
        // 实例化ActiveX组件对象：对word进行操作，创建word运行程序对象
        ActiveXComponent word = new ActiveXComponent("Word.Application");
        //设置打开的word应用程序是否可见
        word.setProperty("Visible", new Variant(true));
        // 获取Dispatch的Documents对象
        Dispatch documents = word.getProperty("Documents").toDispatch();
        //创建新的word文档文本
        Dispatch doc = Dispatch.call(documents, "Add").toDispatch();
        //获得当前word文档文本
        Dispatch selection = Dispatch.get(word, "Selection").toDispatch();
        // 输入文字
        Dispatch.put(selection, "Text", "测试TextWord");
        // 另存为,保存一个新文档
        Dispatch.call(doc, "SaveAs", new Variant("D:\\testWord.doc"));
        // 保存关闭
        if (doc != null) {
            Dispatch.call(doc, "Save");
            Dispatch.call(doc, "Close", new Variant(true));
            Dispatch.call(word, "Quit");
            doc = null;
        }
        // 关闭word文件
        word.invoke("Quit", new Variant[]{});
        // 释放com线程。根据jacob的帮助文档，com的线程回收不由java的垃圾回收器处理
        ComThread.Release();
    }
}
