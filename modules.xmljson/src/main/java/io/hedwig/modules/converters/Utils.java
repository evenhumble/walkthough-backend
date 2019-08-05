package io.hedwig.modules.converters;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Utils {

    public static String xmlToJson(String xmlString){
        JSONObject result = XML.toJSONObject(xmlString);
        return result.toString();
    }

    public static String xmlFileToJson(String filePath)  {
        try {
            File file = new File(filePath);
            return xmlToJson(FileUtils.readFileToString(file, "utf-8"));
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private  static <T> Workbook exportToWorkbook(List<T> objects, Class type){
        return  ExcelExportUtil.exportExcel(new ExportParams(), type, objects);
    }

    public static  <T> boolean exportToExcelFile(List<T> objects, Class type,String filePath){

        File file = new File(filePath);
        Workbook wb = exportToWorkbook(objects,type);
        try {
            wb.write(new FileOutputStream(file));
            wb.close();
        } catch (IOException e) {
            return false;
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static String objectToJsonString(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
