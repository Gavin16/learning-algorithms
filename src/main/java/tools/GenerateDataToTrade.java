package tools;

import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tools.dto.DetailDto;
import tools.dto.HeadDto;
import tools.dto.PurStockinConfirm2TradeOutput;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GenerateDataToTrade
 * @CopyRight: 百果科技
 * @Description:
 * @Author: wufangmin
 * @Date: 2020/4/17 上午 11:28
 * @Version:
 */
public class GenerateDataToTrade {



    public static void main(String[] args) throws Exception{
        String inputFilePath = "C:\\Users\\Administrator\\Desktop\\2020-4-17.xlsx";
        FileInputStream fileInput = new FileInputStream(inputFilePath);//创建文件输入流
        XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
        XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
        int lastRowNum = sheet.getLastRowNum(); //获取表格内容的最后一行的行数
        //rowBegin代表要开始读取的行号，下面这个循环的作用是读取每一行内容
        int rowBegin = 1;
        for (int i = rowBegin; i <= lastRowNum; ++i) {
            XSSFRow row = sheet.getRow(i);//获取每一行
            int columnNum = row.getLastCellNum();//获取每一行的最后一列的列号，即总列数
            List<PurStockinConfirm2TradeOutput> list = new ArrayList<>();
            PurStockinConfirm2TradeOutput output = new PurStockinConfirm2TradeOutput();
            for (int j=0; j<columnNum; ++j) {
                XSSFCell cell = row.getCell(j);//获取每个单元格
                setFieldsAccordToColumnId(j+1,output,cell);
            }
            list.add(output);
            System.out.println(output.getShipNo() + ": " + JSON.toJSONString(list));
        }
        wb.close();
        fileInput.close();
    }

    private static void setFieldsAccordToColumnId(int j, PurStockinConfirm2TradeOutput output,XSSFCell cell) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (j){
            case 1 : output.setBalanceOrgCode(cell.getStringCellValue()); break;
            case 2 : output.setBalanceOrgDesc(cell.getStringCellValue()); break;
            case 3 : output.setOrgCode(cell.getStringCellValue()); break;
            case 4 : output.setOrgDesc(cell.getStringCellValue()); break;
            case 5 : output.setRemark(""); break;
            case 6 : output.setShipDate(sdf.format(cell.getDateCellValue())); break;
            case 7 : output.setShipNo(cell.getStringCellValue()); break;
            case 8 : output.setVendorCode(cell.getStringCellValue()); break;
            case 9 : output.setVendorName(cell.getStringCellValue()); break;
            case 10 : output.setShipAmt(new BigDecimal(cell.getNumericCellValue())); break;
            case 11 : output.setPurchaseType(String.valueOf(cell.getNumericCellValue())); break;
            default:break;
        }
    }

}
