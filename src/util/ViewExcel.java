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
		//����һ��Excel������
		HSSFWorkbook workbook=new HSSFWorkbook();
		//������һ��sheetҳ��������
		System.out.println(list.get(0).get("sheetname".toString()));
		HSSFSheet sheet=workbook.createSheet((String) list.get(0).get("sheetname".toString()));
		// �ֶ������п���һ��������ʾҪΪ�ڼ����裻���ڶ���������ʾ�еĿ�ȣ�nΪ�иߵ���������
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }
     // ������һ��
        HSSFRow row = sheet.createRow((short) 0);

        // �������ֵ�Ԫ���ʽ
        HSSFCellStyle cs = workbook.createCellStyle();
        HSSFCellStyle cs2 = workbook.createCellStyle();

        // ������������
        HSSFFont f = workbook.createFont();
        HSSFFont f2 = workbook.createFont();

        // ������һ��������ʽ������������
        f.setFontHeightInPoints((short) 10);
        f.setColor(HSSFFont.COLOR_RED);
        f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // �����ڶ���������ʽ������ֵ��
        f2.setFontHeightInPoints((short) 10);
        //f2.setColor(HSSFColor.BLACK);

//        Font f3=wb.createFont();
//        f3.setFontHeightInPoints((short) 10);
//        f3.setColor(IndexedColors.RED.getIndex());

    

        //��������
        for(int i=0;i<columnnames.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(columnnames[i]);
            cell.setCellStyle(cs);
        }
        //����ÿ��ÿ�е�ֵ
        for (short i = 1; i < list.size(); i++) {
            // Row ��,Cell ���� , Row �� Cell ���Ǵ�0��ʼ������
            // ����һ�У���ҳsheet��
            HSSFRow row1 = sheet.createRow((short) i);
            // ��row���ϴ���һ������
            for(short j=0;j<keys.length;j++){
                HSSFCell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return workbook;
    
	}
	
}
