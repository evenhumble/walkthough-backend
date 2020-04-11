package io.qkits.benchmarks.db.core;

public interface DaoBenchmarkService {
    /**
     * Insert One
     */
    void testAdd();
    /**
     *  select By id which is primary key
     */
    void testUnique();
    /**
     * update by id which is primary key
     */
    void testUpdateById();
    /**
     * test page query it depends on how much data it is
     */
    void testPageQuery();
    /**
     * test complex query
     */
    void testQuery();

}
