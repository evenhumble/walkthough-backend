package io.hedwig.modules.demos;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvLoaderDemo {

    private static final String CSV_PATH="drool_1.csv";
    private static final String CLASS_PATH = CsvLoaderDemo.class.getClassLoader().getResource("").getPath();
    @Test
    public void testLoadCsv() throws IOException {
        FileReader reader = new FileReader(CLASS_PATH+CSV_PATH);
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

        List<String[]> records = csvReader.readAll();
        String[] headers = records.get(0);
        int keyIndex=0,valueIndex = 0;
        String keyName="id";
        String valueName ="content";
        for (int i = 0; i < headers.length; i++) {
            if(headers[i].equalsIgnoreCase(keyName)){
                keyIndex = i;
            }
            if(headers[i].equalsIgnoreCase(valueName)){
                valueIndex = i;
            }
        }
        Map<String,String> extractedKV = new HashMap<>();

        for (String[] record : records) {
            extractedKV.put(record[keyIndex],record[valueIndex]);
        }
        System.out.println(extractedKV);


//        CsvToBean<Map> csvToBean = new CsvToBeanBuilder(reader)
//            .withType(Map.class)
//            .build();
//
//        for (Map<String, String> entry : csvToBean) {
//            for (Map.Entry<String, String> item : entry.entrySet()) {
//                System.out.println(item.getKey());
//                System.out.println(item.getValue());
//            }
//        }
//        Iterator<Map> csvIterator = csvToBean.iterator();
//        while(csvIterator.hasNext()){
//            Map<String,String> result = csvIterator.next();
//            for (Map.Entry<String, String> entry : result.entrySet()) {
//                System.out.println(entry.getKey());
//                System.out.println(entry.getValue());
//            }
//        }
    }
}
