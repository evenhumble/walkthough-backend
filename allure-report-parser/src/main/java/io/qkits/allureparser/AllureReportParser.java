package io.qkits.allureparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.qameta.allure.ConfigurationBuilder;
import io.qameta.allure.DefaultConfiguration;
import io.qameta.allure.ReportGenerator;
import io.qameta.allure.core.Configuration;


/**
 * @author: patrick on 2019-08-15
 * @Description:
 */
public class AllureReportParser {

  private static Path output;
  private static Path temp = Paths.get("./");

  public static void main(String[] args) throws IOException {
    String
        reportLocation =
        "/Users/patrick/workspace/blockchain/integration-executor-py/my_allure_results";
    String first;
    temp = Paths.get(reportLocation);
    final Configuration configuration = new ConfigurationBuilder().useDefault().build();
    final ReportGenerator generator = new ReportGenerator(configuration);
    output = temp.resolve("report");
    final Path resultsDirectory = Files.createDirectories(temp.resolve("results"));
//    allure1data().forEach(resource -> unpackFile(
//        "allure1data/" + resource,
//        resultsDirectory.resolve(resource)
//    ));
    generator.generate(output, resultsDirectory);
  }
}
