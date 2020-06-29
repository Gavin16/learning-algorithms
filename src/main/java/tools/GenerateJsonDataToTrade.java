package tools;

import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tools.dto.DetailDto;
import tools.dto.HeadDto;
import tools.dto.ResponseToTrade;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取生产库查询数据生成JSON字符串
 */
public class GenerateJsonDataToTrade {

    static String prodUrl;
    static String dataPath;

    static {
        prodUrl = "http://b2b.pagoda.com.cn/uniseq_micro_cgdd/api/erp/postArrivalOrder";
        dataPath = "C:\\Users\\kupat\\Desktop\\msgToTrade.xlsx";
    }

    public static void main(String[] args) throws Exception {

        FileInputStream fileInput = new FileInputStream(dataPath);
        XSSFWorkbook wb = new XSSFWorkbook(fileInput);
        XSSFSheet sheet = wb.getSheetAt(0);
        int firstRowNum = 0,lastRowNum = sheet.getLastRowNum();

        List<ResponseToTrade> list = new ArrayList<>();
        Map<Integer,String> fieldMap = new HashMap<>();
        for (int i = firstRowNum; i <= lastRowNum; ++i) {
            XSSFRow row = sheet.getRow(i);//获取每一行
            int columnNum = row.getLastCellNum();//获取每一行的最后一列的列号，即总列数
            Map<String,Object> dataMap = new HashMap<>();
            for (int j=0; j<columnNum; ++j) {
                XSSFCell cell = row.getCell(j);//获取每个单元格
                if(i == 0){
                    fieldMap.put(j,cell.getStringCellValue());
                }else{
                    // 最后一列为数值，其它列均为字符串
                    if(j != columnNum - 1){
                        // 二代供应商转一代，原料果转成品果
                        if("vendorCode".equals(fieldMap.get(j)) || "goodsCode".equals(fieldMap.get(j))){
                            dataMap.put(fieldMap.get(j),cell.getStringCellValue().substring(1));
                        }else{
                            dataMap.put(fieldMap.get(j),cell.getStringCellValue());
                        }

                    }else{
                        dataMap.put(fieldMap.get(j),cell.getNumericCellValue());
                    }
                }
            }
            if(i > 0){
                ResponseToTrade ele = JSON.parseObject(JSON.toJSONString(dataMap), ResponseToTrade.class);
                list.add(ele);
            }
        }
        for(ResponseToTrade rtt : list){
            System.out.println(JSON.toJSONString(rtt));
        }
        wb.close();
        fileInput.close();
    }


}
