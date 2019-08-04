//package io.qkits.walkthough.netty.adapter.ext;
//
//import io.qkits.walkthough.netty.adapter.DataAdapter;
//import io.qkits.walkthough.netty.common.DataEntry;
//import io.qkits.walkthough.netty.common.DataType;
//import io.qkits.walkthough.netty.common.NettyWalkthroughConsts;
//import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
//import lombok.NonNull;
//
//
//public class PropertyAdapter extends DataAdapter<DataEntry> {
//    public PropertyAdapter(DataType dataType) {
//        super(dataType, (data) -> {
//            return data;
//        });
//    }
//
//    /**
//     * Strongly recommended to hold the reference of the result, </br>by requesting the reference instead of calling this method.
//     */
//    public IProperties getProperties(@NonNull String service, @NonNull String cluster) throws
//                                                                                       NettyWalkthroughException {
//        return new Properties(prepareEntry(service, cluster).getCaches());
//    }
//
//    /**
//     * Strongly recommended to hold the reference of the result,
//     * </br>by requesting the reference instead of calling this method.
//     */
//    public IProperties getMergedProperties(@NonNull String service, @NonNull String cluster) throws NettyWalkthroughException {
//        return new MergedProperties(getProperties(service, cluster), getProperties(service,
//                                                                                   NettyWalkthroughConsts.CLUSTER_OVERALL));
//    }
//}
