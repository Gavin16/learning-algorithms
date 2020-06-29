package tools;

import com.alibaba.fastjson.JSON;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tools.dto.DetailDto;
import tools.dto.HeadDto;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 仅限到货推送WMS使用
 */
public class GenerateJsonDataToWms {

    public static void main(String[] args) throws Exception{
        String inputFilePath = "C:\\Users\\kupat\\Desktop\\messageToWms.xlsx";
        FileInputStream fileInput = new FileInputStream(inputFilePath);//创建文件输入流
        XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
        XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
        int lastRowNum = sheet.getLastRowNum(); //获取表格内容的最后一行的行数
        //rowBegin代表要开始读取的行号，下面这个循环的作用是读取每一行内容
        int rowBegin = 1;
        HeadDto headDto = new HeadDto();
        List<DetailDto> detailList = new ArrayList<>();
        for (int i = rowBegin; i <= lastRowNum; ++i) {
            XSSFRow row = sheet.getRow(i);//获取每一行
            int columnNum = row.getLastCellNum();//获取每一行的最后一列的列号，即总列数
            DetailDto detailDto = new DetailDto();
            for (int j=0; j<columnNum; ++j) {
                XSSFCell cell = row.getCell(j);//获取每个单元格

                if(i == 1){
                    setHeadInfo(headDto,cell,j);
                }
                setDetailInfo(detailList,detailDto,cell,j);
            }
        }
        headDto.setDetailsItem(detailList);
        System.out.println(JSON.toJSONString(headDto));
        wb.close();
        fileInput.close();
    }

    protected static void setDetailInfo(List<DetailDto> detailList,DetailDto detailDto, XSSFCell cell, int j) {
        switch (j){
            case 1:detailDto.setDetailId(cell.getStringCellValue());break;
            case 9:detailDto.setGoodsCode(cell.getStringCellValue());break;
            case 10:detailDto.setGoodsSpec(cell.getStringCellValue());break;
            case 11:detailDto.setPurOrderNo(cell.getStringCellValue());break;
            case 7:detailDto.setArrivedOrg(cell.getStringCellValue());break;
            case 8:detailDto.setArrivedDepotCode(cell.getStringCellValue());break;
            case 12:detailDto.setArrivedItem(cell.getNumericCellValue());break;
            case 13:detailDto.setArrivedNumber(cell.getNumericCellValue());break;
            case 14:detailDto.setPrice(cell.getNumericCellValue());break;
            case 15:detailDto.setUom(cell.getStringCellValue());break;
            default:break;
        }
        if(j == 15){
            detailList.add(detailDto);
        }
    }

    static void setHeadInfo(HeadDto headDto, XSSFCell cell, int j) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (j){
            case 0:headDto.setArrivedOrderNo(cell.getStringCellValue()); break;
            case 1:headDto.setDetailId(cell.getStringCellValue());break;
            case 2:headDto.setExpectedArriveTime(sdf.format(cell.getDateCellValue()));break;
            case 3:headDto.setSupplier(cell.getStringCellValue());break;
            case 4:headDto.setSupplierName(cell.getStringCellValue());break;
            case 5:headDto.setOperator(cell.getStringCellValue());break;
            case 6:headDto.setSourceType(cell.getStringCellValue());break;
            case 7:headDto.setArrivedOrg(cell.getStringCellValue());break;
            case 8:headDto.setArrivedDepotCode(cell.getStringCellValue());break;
            default: break;
        }
    }

}
