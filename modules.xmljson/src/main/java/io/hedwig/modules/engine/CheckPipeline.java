package io.hedwig.modules.engine;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import io.hedwig.modules.converters.Utils;
import io.hedwig.modules.engine.result.OverallResult;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CheckPipeline {

    private Map<String,String> sources = new ConcurrentHashMap<>();
    private Map<String,String> testSources = new ConcurrentHashMap<>();
    private List<OverallResult> result = new ArrayList<>();

    private static final String CSV_PATH="drool_1.csv";
    private static final String CLASS_PATH = CheckPipeline.class.getClassLoader().getResource("").getPath();

    public void setupBaseLine() throws IOException {
        sources = loadCsvData(CSV_PATH,sources);
    }

    public void setupTestSource() throws IOException {
        testSources = loadCsvData("drool_2.csv",testSources);
    }

    public void setUpOutputSource() throws IOException {

    }

    private Map<String,String> loadCsvData(String csvPath,Map<String,String> sources) throws IOException {
        FileReader reader = new FileReader(CLASS_PATH+csvPath);
        CSVReader csvReader = new CSVReaderBuilder(reader).build();
        List<String[]> records = csvReader.readAll();
        String[] headers = records.get(0);
        int keyIndex=0,valueIndex = 0;
        String keyName="id";
        String valueName ="content";
        for (int i = 0; i < headers.length; i++) {
            if(headers[i].equalsIgnoreCase(keyName)){
                keyIndex = i;
            }else if(headers[i].equalsIgnoreCase(valueName)){
                valueIndex = i;
            }
        }
        for (String[] record : records) {
            sources.put(record[keyIndex],record[valueIndex]);
        }

        return sources;
    }
    public void startChecking(){
        JSONChecker checker = new JSONChecker();
        for (Map.Entry<String, String> entry : testSources.entrySet()) {
            String sourceJson = Utils.xmlToJson(sources.getOrDefault(entry.getKey(),""));
            String testJson = Utils.xmlToJson(entry.getValue());
            this.result.add(checker.check(sourceJson,testJson));
        }
    }

    private void check(){}

    public void output() throws IOException {
        FileUtils.write(new File("result.json"),Utils.objectToJsonString(this.result
        .stream().filter(overallResult -> !overallResult.isSame()).collect(Collectors.toList())),Charset.defaultCharset());

        FileUtils.write(new File("result_all.json"),Utils.objectToJsonString(this.result));
        CSVWriter writer = new CSVWriter(new FileWriter("result.csv"));
        List<String[]> csvResult = new ArrayList<>();
        for (OverallResult overallResult : this.result) {
            String[] stringResult = new String[5];
            stringResult[0]=overallResult.getBaseLine().toString();
            stringResult[1]=overallResult.getTestLine().toString();
            stringResult[2]=overallResult.getResult().toString();
            stringResult[3]=Boolean.toString(overallResult.isSame());
            csvResult.add(stringResult);
        }
        writer.writeAll(csvResult);
    }

    private void getInput(){}
}
