package io.qkits.benchmarks.db.core;


/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 **/
public class DaoBenchmarkTestBuilder {

    public static BenchmarkTest build(String testName,
                                      DaoBenchmarkService service) {
        BenchmarkTest test = new BenchmarkTest();
        test.setName(testName);

        test.addTask(new BenchmarkTask(
                testName,
                BenchmarkTaskEnum.ADD,
                service::testAdd
        ));
        test.addTask(new BenchmarkTask(
                testName,
                BenchmarkTaskEnum.UPDATE,
                service::testUpdateById
        ));
        test.addTask(new BenchmarkTask(
                testName,
                BenchmarkTaskEnum.QUERY_BY_ID,
                service::testUnique
        ));
        test.addTask(new BenchmarkTask(
                testName,
                BenchmarkTaskEnum.PAGE_QUERY,
                service::testPageQuery
        ));
        test.addTask(new BenchmarkTask(
                testName,
                BenchmarkTaskEnum.CUSTOM_QUERY,
                service::testQuery
        ));
        return test;
    }

}
