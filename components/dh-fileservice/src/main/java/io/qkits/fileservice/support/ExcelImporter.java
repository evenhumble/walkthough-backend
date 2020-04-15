package io.qkits.fileservice.support;

import ch.qos.logback.core.util.FileUtil;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class ExcelImporter {

    public <T> List<T> toObjectList(String excelPath, Class clazz) {
        File file = new File(excelPath);
        if (!file.exists()) throw new RuntimeException("Excel 不存在，请输入正确的Excel路径");
        return ExcelImportUtil.importExcel(file, clazz, new ImportParams());
    }

    public <T> Workbook exportToWorkbook(List<T> objects, Class clazz) {
        return ExcelExportUtil.exportExcel(new ExportParams(), clazz, objects);
    }

    public <T> boolean exportToExcelFile(List<T> objects, Class clazz, String fileName) {

        File file = new File(fileName);
        Workbook wb = exportToWorkbook(objects, clazz);
        try {
            wb.write(new FileOutputStream(file));
            wb.close();
        } catch (IOException e) {
            log.error("output to excel file failed,error = ", e);
            return false;
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
