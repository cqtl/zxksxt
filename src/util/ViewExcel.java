package util;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ViewExcel {
	public static HSSFWorkbook createWorkbook(List<Map<String, Object>>list,String []keys,String columnnames[]){
		//创建一个Excel工作簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		//创建第一个sheet页，并命名
		System.out.println(list.get(0).get("sheetname".toString()));
		HSSFSheet sheet=workbook.createSheet((String) list.get(0).get("sheetname".toString()));
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }
     // 创建第一行
        HSSFRow row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        HSSFCellStyle cs = workbook.createCellStyle();
        HSSFCellStyle cs2 = workbook.createCellStyle();

        // 创建两种字体
        HSSFFont f = workbook.createFont();
        HSSFFont f2 = workbook.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(HSSFFont.COLOR_RED);
        f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        //f2.setColor(HSSFColor.BLACK);

//        Font f3=wb.createFont();
//        f3.setFontHeightInPoints((short) 10);
//        f3.setColor(IndexedColors.RED.getIndex());

    

        //设置列名
        for(int i=0;i<columnnames.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(columnnames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            HSSFRow row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                HSSFCell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return workbook;
    
	}
	
}
