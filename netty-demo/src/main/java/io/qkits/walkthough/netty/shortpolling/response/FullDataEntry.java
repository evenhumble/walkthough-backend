package io.qkits.walkthough.netty.shortpolling.response;

import io.qkits.walkthough.netty.common.DataEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FullDataEntry extends DataEntry {
    private String key;
}
