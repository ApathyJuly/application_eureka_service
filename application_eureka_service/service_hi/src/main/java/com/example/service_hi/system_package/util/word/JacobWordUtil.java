package com.example.service_hi.system_package.util.word;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Jacob是Java-COM Bridge的缩写，
 * 它在Java与微软的COM组件之间构建一座桥梁。使用Jacob自带的DLL动态链接库，
 * 并通过JNI的方式实现了在Java平台上对COM程序的调用。DLL动态链接库的生成需要windows平台的支持。
 * 该方案只能在windows平台实现，是其局限性。
 */

public class JacobWordUtil {
    // word运行程序对象
    private ActiveXComponent word;
    // 所有word文档集合
    private Dispatch documents;
    // word文档
    private Dispatch doc;
    // 选定的范围或插入点
    private Dispatch selection;
    // 保存退出
    private boolean saveOnExit;

    /**
     * 1、是否可见word程序
     * @param visible true-可见word程序，false-后台默默执行。
     */
    public JacobWordUtil(boolean visible) {
        word = new ActiveXComponent("Word.Application");
        word.setProperty("Visible", new Variant(visible));
        documents = word.getProperty("Documents").toDispatch();
    }
    /**
     * 2、创建一个新的word文档
     */
    public void createNewDocument() {
        doc = Dispatch.call(documents, "Add").toDispatch();
        //获得当前word文档文本
        selection = Dispatch.get(word, "Selection").toDispatch();
    }
    /**
     * 3、打开一个已经存在的Word文档
     * @param docPath 文件的路径
     */
    public void openDocument(String docPath) {
        closeDocument();
        doc = Dispatch.call(documents, "Open", docPath).toDispatch();
        selection = Dispatch.get(word, "Selection").toDispatch();
    }
    /**
     * 4、把选定的内容或插入点向上移动
     *
     * @param pos
     *            移动的距离
     */
    public void moveUp(int pos) {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        for (int i = 0; i < pos; i++)
            Dispatch.call(selection, "MoveUp");
    }

    /**
     * 5、把选定的内容或者插入点向下移动
     *
     * @param pos
     *            移动的距离
     */
    public void moveDown(int pos) {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        for (int i = 0; i < pos; i++)
            Dispatch.call(selection, "MoveDown");
    }
    /**
     * 6、把选定的内容或者插入点向左移动
     *
     * @param pos
     *            移动的距离
     */
    public void moveLeft(int pos) {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        for (int i = 0; i < pos; i++) {
            Dispatch.call(selection, "MoveLeft");
        }
    }
    /**
     * 7、把选定的内容或者插入点向右移动
     *
     * @param pos
     *            移动的距离
     */
    public void moveRight(int pos) {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        for (int i = 0; i < pos; i++)
            Dispatch.call(selection, "MoveRight");
    }
    /**
     * 8、把插入点移动到文件首位置
     */
    public void moveStart() {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        Dispatch.call(selection, "HomeKey", new Variant(6));
    }
    /**
     * 9、把插入点移动到文件末位置
     */
    public void moveEnd() {
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        Dispatch.call(selection, "EndKey", new Variant(6));
    }
    /**
     * 10、向 document 中插入文本内容
     *
     * @param textToInsert
     *            插入的文本内容
     */
    public void insertText(String textToInsert) {
        // 在指定的位置插入文本内容
        Dispatch.put(selection, "Text", textToInsert);
        // 取消选中,应该就是移动光标
        Dispatch format = Dispatch.get(selection, "ParagraphFormat").toDispatch();
        // 设置段落格式为首行缩进2个字符
        Dispatch.put(format, "CharacterUnitFirstLineIndent", new Variant(2));
        Dispatch.call(selection, "MoveRight", new Variant(1), new Variant(1));
        //moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        //Dispatch.call(selection, "MoveUp");
        //moveDown(1);
    }
    /**
     * 11、插入标题
     * @param num  标题编号
     * @param level 标题级别：-2：一级标题；-3：二级标题；-4：三级标题；-5：四级标题
     * @param text 标题题目
     */
    public void insertTitle(String num, int level, String text) {
        Dispatch activeDocument = Dispatch.get(word, "ActiveDocument").toDispatch();

        //Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        //moveDown(1);
        Dispatch.put(selection, "Text", num + " " + text);
        Dispatch style = Dispatch.call(activeDocument, "Styles", new Variant(level)).toDispatch();;
        Dispatch.put(selection, "Style", style);
        moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        //moveDown(1);
    }
    /**
     * 12、创建目录
     */
    public void createCatalog() {
        Dispatch activeDocument = Dispatch.get(word, "ActiveDocument").toDispatch();

        Dispatch.call(selection, "HomeKey", new Variant(6)); // 将光标移到文件首的位置
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        moveUp(1);

        Dispatch.put(selection, "Text", "目录");
        Dispatch style = Dispatch.call(activeDocument, "Styles", new Variant(-2)).toDispatch();
        Dispatch.put(selection, "Style", style);
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();// 段落格式
        Dispatch.put(alignment, "Alignment", "1"); // (1:置中 2:靠右 3:靠左)
        moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行

        Dispatch myRange = Dispatch.call(selection, "Range").toDispatch();

        /** 获取目录 */
        Dispatch tablesOfContents = Dispatch.get(activeDocument, "TablesOfContents").toDispatch();
//        Dispatch add = Dispatch.call(tablesOfContents, "Add", myRange, new Variant(true), new Variant(1), new Variant(3), new Variant(true), new Variant(true), new Variant('T'), new Variant(true), new Variant(true), new Variant(1), new Variant(true)).toDispatch();

//        Dispatch.put(add, "RightAlignPageNumbers", new Variant(true));
//        Dispatch.put(add, "UseHeadingStyles", new Variant(true));
//        Dispatch.put(add, "UpperHeadingLevel", new Variant(1));
//        Dispatch.put(add, "LowerHeadingLevel", new Variant(3));
//        Dispatch.put(add, "IncludePageNumbers", new Variant(true));
//        Dispatch.put(add, "UseHyperlinks", new Variant(true));
//        Dispatch.put(add, "HidePageNumbersInWeb", new Variant(true));

//        Dispatch.call(add, "Add", myRange);

        //插入一个分页符
        Dispatch.call(selection, "InsertBreak", new Variant(7));
        Dispatch.call(selection, "TypeBackspace");
    }

    /**
     * 13、更新目录
     * @param outputPath
     * @param doc
     */
    public void updateCatalog(String outputPath, Dispatch doc) {
        /** 打开word文档 */
        //Dispatch doc = Dispatch.invoke(documents, "Open", Dispatch.Method,
        //        new Object[] { outputPath, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
        //Dispatch doc = Dispatch.call(documents, "Open", outputPath).toDispatch();

        Dispatch activeDocument = word.getProperty("ActiveDocument").toDispatch();
        /** 获取目录 */
        Dispatch tablesOfContents = Dispatch.get(activeDocument, "TablesOfContents").toDispatch();
        /** 获取第一个目录。若有多个目录，则传递对应的参数  */
        Variant tablesOfContent = Dispatch.call(tablesOfContents, "Item", new Variant(1));
        /** 更新目录，有两个方法：Update 更新域，UpdatePageNumbers 只更新页码 */
        Dispatch toc = tablesOfContent.toDispatch();
        toc.call(toc, "Update");

        /** 另存为 */
        Dispatch.call(Dispatch.call(word, "WordBasic").getDispatch(), "FileSaveAs", outputPath);
        //System.out.println("更新目录");
        /** 关闭word文档 */
        Dispatch.call(doc, "Close", new Variant(false));

        /** 退出word进程 */
        close();
    }
    /**
     * 14、在当前插入点插入图片
     *
     * @param imagePath
     *            图片路径
     */
    public void insertImage(String imagePath, int c, int tc, String title) {
        Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(), "AddPicture", imagePath);

        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();// 段落格式
        Dispatch.put(alignment, "Alignment", "1"); // (1:置中 2:靠右 3:靠左)
        //moveRight(1);
        putText("图" + c + "-" + tc + " " + title);
        moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
    }

    /**
     * 15、插入空行
     */
    public void insertBlankRow() {
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();// 段落格式
        Dispatch.put(alignment, "Alignment", "3"); // (1:置中 2:靠右 3:靠左)
    }
    /**
     * 16、创建表格
     *
     * @param numCols
     *            列数
     * @param numRows
     *            行数
     */
    public void createTable(int numCols, int numRows, int c, int tc, String title) {
//        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
//        Dispatch range = Dispatch.get(selection, "Range").toDispatch();
//        Dispatch newTable = Dispatch.call(tables, "Add", range, new Variant(numRows), new Variant(numCols))
//                .toDispatch();
//        Dispatch.call(selection, "MoveRight");
//        moveEnd();

        Dispatch.call(selection, "TypeParagraph");// 插入一个空行
        Dispatch alignment = Dispatch.get(selection, "ParagraphFormat").toDispatch();// 段落格式
        Dispatch.put(alignment, "Alignment", "1"); // (1:置中 2:靠右 3:靠左)
        putText("表" + c + "-" + tc + " " + title);
        moveRight(1);
        Dispatch.call(selection, "TypeParagraph");// 插入一个空行

        Dispatch activeDocument = Dispatch.get(word, "ActiveDocument").toDispatch();
        Dispatch tables1 = Dispatch.get(activeDocument, "Tables").toDispatch();

        Dispatch range = Dispatch.get(selection, "Range").toDispatch();
        Dispatch.call(tables1, "Add", range, new Variant(numRows), new Variant(numCols), new Variant(1), new Variant(0)).toDispatch();

        Dispatch.call(selection, "MoveDown", new Variant(5), new Variant(numRows), new Variant(1));
        Dispatch format = Dispatch.get(selection, "ParagraphFormat").toDispatch();
        Dispatch.put(format, "Alignment", new Variant(1));

        moveLeft(1);
    }
    /**
     * 17、向选中的单元格中写入数据
     * @param text
     */
    public void putText(String text) {
        Dispatch.put(selection, "Text", text);
    }
    /**
     * 18、增加一行
     *
     * @param tableIndex
     *            word文档中的第N张表(从1开始)
     */
    public void addRow(int tableIndex) {
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch rows = Dispatch.get(table, "Rows").toDispatch();
        Dispatch.call(rows, "Add");
    }

    /**
     * 19、合并单元格
     *
     * @param tableIndex
     * @param fstCellRowIdx
     * @param fstCellColIdx
     * @param secCellRowIdx
     * @param secCellColIdx
     */
    public void mergeCell(int tableIndex, int fstCellRowIdx, int fstCellColIdx, int secCellRowIdx, int secCellColIdx) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        Dispatch fstCell = Dispatch.call(table, "Cell", new Variant(fstCellRowIdx), new Variant(fstCellColIdx))
                .toDispatch();
        Dispatch secCell = Dispatch.call(table, "Cell", new Variant(secCellRowIdx), new Variant(secCellColIdx))
                .toDispatch();
        Dispatch.call(fstCell, "Merge", secCell);
    }
    /**
     * 20、在指定的单元格里填写数据
     *
     * @param tableIndex
     * @param cellRowIdx
     * @param cellColIdx
     * @param txt
     */
    public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx, String txt) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx), new Variant(cellColIdx)).toDispatch();
        Dispatch.call(cell, "Select");
        Dispatch.put(selection, "Text", txt);
    }
    /**
     * 21、增加一列
     *
     * @param tableIndex
     *            word文档中的第N张表(从1开始)
     */
    public void addCol(int tableIndex) {
        // 所有表格
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch();
        // 要填充的表格
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex)).toDispatch();
        // 表格的所有行
        Dispatch cols = Dispatch.get(table, "Columns").toDispatch();
        Dispatch.call(cols, "Add").toDispatch();
        Dispatch.call(cols, "AutoFit");
    }
    /**
     * 22、设置当前选定内容的字体
     * @param bold 粗体
     * @param italic 斜体
     * @param underLine 下划线
     * @param colorSize 字体颜色
     * @param size 字体大小
     * @param name 字体名称
     */
    public void setFont(boolean bold, boolean italic, boolean underLine, String colorSize, String size, String name) {
        Dispatch font = Dispatch.get(selection, "Font").toDispatch();
        Dispatch.put(font, "Name", new Variant(name));
        Dispatch.put(font, "Bold", new Variant(bold));
        Dispatch.put(font, "Italic", new Variant(italic));
        Dispatch.put(font, "Underline", new Variant(underLine));
        Dispatch.put(font, "Color", colorSize);
        Dispatch.put(font, "Size", size);
    }
    /**
     * 24、文件保存或另存为
     *
     * @param savePath
     *            保存或另存为路径
     */
    public void save(String savePath) {
        Dispatch.call((Dispatch) Dispatch.call(word, "WordBasic").getDispatch(), "FileSaveAs", savePath);
    }
    /**
     * 文档另存为
     * @param savePath
     */
    public void saveAs(String savePath){
        Dispatch.call(doc, "SaveAs", savePath);
    }
    /**
     * 25、关闭当前word文档
     */
    public void closeDocument() {
        if (doc != null) {
            Dispatch.call(doc, "Save");
            Dispatch.call(doc, "Close", new Variant(saveOnExit));
            doc = null;
        }
    }
    /**
     * 26、关闭全部应用
     */
    public void close() {
        closeDocument();
        if (word != null) {
            Dispatch.call(word, "Quit");
            word = null;
        }
        selection = null;
        documents = null;
    }

    /**
     * 27、全局将指定的文本替换成图片
     * @param findText
     * @param imagePath
     */
    public void replaceAllImage(String findText, String imagePath, int width, int height){
        moveStart();
        while (find(findText)){
            Dispatch picture = Dispatch.call(Dispatch.get(getSelection(), "InLineShapes").toDispatch(), "AddPicture", imagePath).toDispatch();
            Dispatch.call(picture, "Select");
            Dispatch.put(picture, "Width", new Variant(width));
            Dispatch.put(picture, "Height", new Variant(height));
            moveStart();
        }
    }


    /**
     * 28、获取当前的选定的内容或者插入点
     * @return 当前的选定的内容或者插入点
     */
    public Dispatch getSelection(){
        if (selection == null)
            selection = Dispatch.get(word, "Selection").toDispatch();
        return selection;
    }

    /**
     * 29、从选定内容或插入点开始查找文本
     * @param findText 要查找的文本
     * @return boolean true-查找到并选中该文本，false-未查找到文本
     */
    public boolean find(String findText){
        if(findText == null || findText.equals("")){
            return false;
        }
        // 从selection所在位置开始查询
        Dispatch find = Dispatch.call(getSelection(), "Find").toDispatch();
        // 设置要查找的内容
        Dispatch.put(find, "Text", findText);
        // 向前查找
        Dispatch.put(find, "Forward", "True");
        // 设置格式
        Dispatch.put(find, "Format", "True");
        // 大小写匹配
        Dispatch.put(find, "MatchCase", "True");
        // 全字匹配
        Dispatch.put(find, "MatchWholeWord", "True");
        // 查找并选中
        return Dispatch.call(find, "Execute").getBoolean();
    }

    /**
     * 30、把签名图片插入到保密承诺书模板中
     * @param wordPath
     * @param ImgPaht
     * @param previewFile
     * @param wordKey
     * @return
     */
    public static byte[] wordInsertImg(String wordPath,String ImgPaht,String previewFile,String wordKey) {
        JacobWordUtil wordUtilImg = new JacobWordUtil(false);//获取工具类对象
        wordUtilImg.openDocument(wordPath);//打开word
        //在指定位置插入指定的图片
        wordUtilImg.replaceAllImage(wordKey,ImgPaht,70,30);//AAA是指定的图片位置。后面的两个参数是指定图片的长和宽。

        String saveWordPath = previewFile +"保密协议书.docx";
        wordUtilImg.saveAs(saveWordPath);//插入成功后生成的新word
        wordUtilImg.closeDocument();//关闭对象。
        System.out.println("插入成功");


        return readFile(saveWordPath);
        //插入成功后获取对应的字节流数组返回
    }


    /**
     * 31、方法功能：读取文件内容返回字节流
     * @param  filePath
     * @return byte[]
     * */
    public static byte[] readFile(String filePath){
        InputStream fis = null;
        ByteArrayOutputStream baos = null;
        try{
            Thread.sleep(1000);
            baos = new ByteArrayOutputStream();
            fis = new FileInputStream(filePath);
            byte[] ch = new byte[1024];
            int readLen = 0;
            while ((readLen = fis.read(ch)) != -1) {
                baos.write(ch, 0, readLen);
            }
            return baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if (fis != null) {
                    fis.close();
                }
            }catch (IOException e) {}
            try{
                if (baos != null) {
                    baos.close();}
            }catch (IOException e) {}
        }
        return null;
    }





}
