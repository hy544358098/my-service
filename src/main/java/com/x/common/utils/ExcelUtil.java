package com.x.common.utils;

import com.x.common.exception.ServiceException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class ExcelUtil {


  private int[] inCell = {1, 3, 5, 6, 8, 9, 10, 15};

  private int[] buCell = {1, 2, 3, 4, 7};

  /**
   * 创建excel文档，
   *
   * @param list 数据
   * @param keys list中map的key数组集合
   * @param columnNames excel的列名
   */
  public static Workbook createWorkBook(
      List<Map<String, Object>> list, String[] keys, String columnNames[]) {
    // 创建excel工作簿  在内存中只保留100行记录,超过100就将之前的存储到磁盘里
    SXSSFWorkbook wb = new SXSSFWorkbook(100);

    // 创建第一个sheet（页），并命名
    Sheet sheet = wb.createSheet("sheet1");

    // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
    for (int i = 0; i < keys.length; i++) {
      sheet.setColumnWidth(i, (int) (35.7 * 150));
    }

    // 创建第一行
    Row row = sheet.createRow(0);

    // 创建两种单元格格式
    CellStyle cs = wb.createCellStyle();
    CellStyle cs2 = wb.createCellStyle();

    // 创建两种字体
    Font f = wb.createFont();
    Font f2 = wb.createFont();

    // 创建第一种字体样式（用于列名）
    f.setFontHeightInPoints((short) 10);
    f.setColor(IndexedColors.BLACK.getIndex());

    // 创建第二种字体样式（用于值）
    f2.setFontHeightInPoints((short) 10);
    f2.setColor(IndexedColors.BLACK.getIndex());

    // 设置第一种单元格的样式（用于列名）
    cs.setFont(f);
    // 设置第二种单元格的样式（用于值）
    cs2.setFont(f2);
    // 设置列名
    for (int i = 0; i < columnNames.length; i++) {
      Cell cell = row.createCell(i);
      cell.setCellValue(columnNames[i]);
      cell.setCellStyle(cs);
    }
    // 设置每行每列的值
    for (int i = 1; i < list.size(); i++) {
      // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
      // 创建一行，在页sheet上
      Row row1 = sheet.createRow(i);
      // 在row行上创建一个方格
      for (int j = 0; j < keys.length; j++) {
        Cell cell = row1.createCell(j);
        cell.setCellValue(
            list.get(i).get(keys[j]) == null ? " " : list.get(i).get(keys[j]).toString());
        cell.setCellStyle(cs2);
      }
    }
    return wb;
  }

  /**
   * 导入值校验--业务观察----每个工作表效验不同
   *
   * @param sheet 工作表
   * @param row 行
   * @param colNum 列编号
   * @param errorHint 错误提示
   * @return 校验通过返回空，否则抛出异常
   */
  public void validCellValueBu(Sheet sheet, Row row, int colNum, String errorHint) {
    if ("".equals(this.getCellValue(sheet, row, colNum - 1))) {
      for (int cell : buCell) {
        if (colNum == cell) {
          throw new ServiceException(
              "error", "校验 :第" + (row.getRowNum() + 1) + "行" + colNum + "列" + errorHint + "不能为空");
        }
      }
    }
  }

  /**
   * 导入值校验--纪检观察----每个工作表效验不同
   *
   * @param sheet 工作表
   * @param row 行
   * @param colNum 列编号
   * @param errorHint 错误提示
   * @return 校验通过返回空，否则抛出异常
   */
  public void validCellValueIn(Sheet sheet, Row row, int colNum, String errorHint) {
    if ("".equals(this.getCellValue(sheet, row, colNum - 1))) {
      for (int col : inCell) {
        if (colNum == col) {
          throw new ServiceException(
              "error", "校验 :第" + (row.getRowNum() + 1) + "行" + colNum + "列" + errorHint + "不能为空");
        }
      }
    }
  }

  /**
   * 从输入流中获取excel工作表
   *
   * @param iStream 输入流
   * @param fileName 带 .xls或.xlsx 后缀的文件名
   * @return 文件名为空返回空; 格式不正确抛出异常; 正常返回excel工作空间对象
   */
  public Workbook getWorkbookByInputStream(InputStream iStream, String fileName) {
    Workbook workbook = null;

    try {
      if (null == fileName) {
        return null;
      }
      if (fileName.endsWith(".xls")) {
        workbook = new XSSFWorkbook(iStream);
      } else if (fileName.endsWith(".xlsx")) {
        workbook = new XSSFWorkbook(iStream);
      } else {
        throw new IOException("The document type don't support");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (iStream != null) {
        try {
          iStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return workbook;
  }

  /**
   * 从Workbook中获取一个sheet,如果没有就创建一个
   *
   * @param workbook 工作空间
   * @param index 第几个sheet
   * @return 返回sheet
   */
  public Sheet getSheetByWorkbook(Workbook workbook, int index) {
    Sheet sheet = workbook.getSheetAt(index);
    if (null == sheet) {
      sheet = workbook.createSheet();
    }

    sheet.setDefaultRowHeightInPoints(20); // 行高
    sheet.setDefaultColumnWidth(20); // 列宽

    return sheet;
  }

  /**
   * 获取指定sheet指定row中指定column的cell值
   *
   * @param sheet 工作表
   * @param row 行
   * @param column 第几列
   * @return 返回单元格的值或""
   */
  public String getCellValue(Sheet sheet, Row row, int column) {
    if (sheet == null || row == null) {
      return "";
    }
    Cell cell = row.getCell(column);
    if (null == cell || cell.toString().equals("")) {
      return "";
    }
    return this.getCellValue(row.getCell(column));
  }

  /**
   * 从单元格中获取单元格的值
   *
   * @param cell 单元格
   * @return 返回值或""
   */
  public String getCellValue(Cell cell) {
    if (cell == null) {
      return "";
    }

    switch (cell.getCellType()) {
      case Cell.CELL_TYPE_NUMERIC:
        Number number = cell.getNumericCellValue();
        String numberStr = String.valueOf(number);

        if (numberStr.endsWith(".0")) {
          numberStr = numberStr.replace(".0", ""); // 取整数
        }
        if (numberStr.indexOf("E") >= 0) {
          numberStr = new DecimalFormat("#").format(number); // 取整数
        }

        return numberStr;
      case Cell.CELL_TYPE_STRING:
        return cell.getStringCellValue().trim();
      case Cell.CELL_TYPE_FORMULA: // 公式
        return "";
      case Cell.CELL_TYPE_BLANK:
        return "";
      case Cell.CELL_TYPE_BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      default:
        break;
    }

    return "";
  }

  /**
   * 判断该行是否为空行
   *
   * @param row 行
   * @return 为空行返回true,不为空行返回false
   */
  public boolean isBlankRow(Row row) {
    if (row == null) {
      return true;
    }

    Iterator<Cell> iter = row.cellIterator();
    while (iter.hasNext()) {
      Cell cell = iter.next();
      if (cell == null) {
        continue;
      }

      String value = this.getCellValue(cell);
      if (!this.isNULLOrBlank(value)) {
        return false;
      }
    }

    return true;
  }

  /**
   * 判断一个对象是否为空
   *
   * @param obj 对象
   * @return 为空返回true,不为空返回false
   */
  public boolean isNULLOrBlank(Object obj) {
    if (obj != null && !"".equals(obj.toString())) {
      return false;
    }

    return true;
  }
}
