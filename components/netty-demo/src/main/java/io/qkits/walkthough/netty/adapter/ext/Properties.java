//package io.qkits.walkthough.netty.adapter.ext;
//
//import java.util.Map;
//
//import io.qkits.walkthough.netty.common.DataEntry;
//
//public class Properties extends PropertyExtractor<DataEntry> {
//    private final static PropertyExtractor<DataEntry> DEFAULT_EXTRACTOR = (source) -> {
//        return source.getValue();
//    };
//
//    public Properties(Map<String, DataEntry> properties) {
//        super(properties, DEFAULT_EXTRACTOR);
//    }
//}
