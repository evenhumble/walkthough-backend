package io.qkits.walkthough.netty.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataEntry {
    private String value;
    private String runtime;

    public DataEntry(DataEntry dataEntry) {
        setValue(dataEntry.getValue());
        setRuntime(dataEntry.getRuntime());
    }
}
