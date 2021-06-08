package com.test.util;

import java.io.UnsupportedEncodingException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 自定义工具类
 */

public class Util {
  
  public Util() {
    super();
  }
  
  /**
   * 字符串转MD5码加密
   * @param str：需要转码的字符串
   * @return newstr：转码后的字符串
   */
  public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    //确定计算方法
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    BASE64Encoder base64en = new BASE64Encoder();
    //加密后的字符串
    String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
    return newstr;
  }
  
  /**
   * 导出Excel
   * @param sheetName：sheet名称
   * @param title：标题
   * @param values：内容
   * @param wb：HSSFWorkbook对象
   * @return
   */
  public static HSSFWorkbook getExcel(String sheetName,String []title,String [][]values, HSSFWorkbook wb){
    //1、创建一个HSSFWorkbook，对应一个Excel文件
    if(wb == null){wb = new HSSFWorkbook();}
    //2、在workbook中添加一个sheet,对应Excel文件中的sheet
    HSSFSheet sheet = wb.createSheet(sheetName);
    //3、在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
    HSSFRow row = sheet.createRow(0);
    //4、创建单元格，并设置值表头 设置表头居中
    HSSFCellStyle style = wb.createCellStyle();
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //创建一个居中格式
    //5、生成数据列
    HSSFCell cell = null;//声明列对象
    for(int i=0;i<title.length;i++){//创建标题
      cell = row.createCell(i);
      cell.setCellValue(title[i]);
      cell.setCellStyle(style);
    }
    for(int i=0;i<values.length;i++){//创建内容
      row = sheet.createRow(i + 1);
      for(int j=0;j<values[i].length;j++){
        row.createCell(j).setCellValue(values[i][j]);//将内容按顺序赋给对应的列对象
      }
    }
    return wb;
  }
  
}
