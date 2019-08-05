package io.hedwig.modules.jodd.db;

public interface BasicQuery {
    void createConnection();
    public void basicQuery();
    public void basicUpdate();
    public void basicInsert();
}
