package com.sky.test;

import com.mysql.cj.x.protobuf.MysqlxResultset;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * 使用POI操作Excel文件
 */
public class POITest {

    /**
     * 通过POI创建Excel文件并写入内容
     */
    public static void write() throws IOException {
        //在内存中创建一个Excel文件
        XSSFWorkbook excel = new XSSFWorkbook();
        //在Excel文件中创建一个Sheet页
        XSSFSheet sheet = excel.createSheet("info");
        //在sheet页中创建对象（一行）,rownum编号从0开始
        XSSFRow row = sheet.createRow(1);
        //在行上面创建单元格并写入内容
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("城市");

        row = sheet.createRow(2);
        row.createCell(1).setCellValue("张三");
        row.createCell(2).setCellValue("北京");

        row = sheet.createRow(3);
        row.createCell(1).setCellValue("李四");
        row.createCell(2).setCellValue("南京");

        //通过输出流将内存中的excel文件写入磁盘
        FileOutputStream fileOutputStream = new FileOutputStream(new File("E://info.xlsx"));

        excel.write(fileOutputStream);

        //关闭资源
        fileOutputStream.close();
        excel.close();

    }

    /**
     * 通过POI读取excel文件中的内容
     * @throws IOException
     */
    public static void read() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E://info.xlsx"));
        XSSFWorkbook excel = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = excel.getSheetAt(0);

        //获取sheet中最后一行的行号
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {
            //获得某一行
            XSSFRow row = sheet.getRow(i);
            //获得单元格对象
            String cellValue1 = row.getCell(1).getStringCellValue();
            String cellValue2 = row.getCell(2).getStringCellValue();
            System.out.println(cellValue1 + " " + cellValue2);

        }
        //关闭资源
        fileInputStream.close();
        excel.close();
    }

    public static void main(String[] args) throws IOException {
//        write();
        read();
    }
}
