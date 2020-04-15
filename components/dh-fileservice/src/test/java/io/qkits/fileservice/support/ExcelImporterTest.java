package io.qkits.fileservice.support;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.ClassLoaderUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ExcelImporterTest {
    private ExcelImporter excelImporter;
    @Before
    public void setupExcelImport(){
        excelImporter = new ExcelImporter();
    }
    @Test
    public void toObjectList() {
        String xlsFilePath = "/Users/patrick/Desktop/"+ "第一轮Staking.xls";
        List<ExcelClass> result = excelImporter.toObjectList(xlsFilePath,ExcelClass.class);
        System.out.println(result);
    }

    @Data
    public static class ExcelClass{
        @Excel(name = "Address")
        private String address;
        @Excel(name = "Amount")
        private BigDecimal amount;
        @Excel(name="Ont")
        private BigDecimal stakingAmount;
        @Excel(name="PeerPubkey")
        private String peerPubKey;
    }
}