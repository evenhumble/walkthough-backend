//package io.qdriven.plugins.thrift.generators;
//
//
//import java.lang.reflect.Field;
//
///**
// * Created by patrick on 16/8/17.
// */
//public class ThriftInvokeSampleCodes {
//    public static final String CONTEXT_SAMPLECODE="context.getParameter(\"%s\")";
//    public static final String SET_CLAUSE="%s.set%s(%s)";
//
//    public static void buildSampleCode(Class thriftRequestClass){
//        System.out.println(thriftRequestClass.getSimpleName()+
//                " requestObject="+"new "+thriftRequestClass.getSimpleName()+"();");
//        for (Field field : thriftRequestClass.getDeclaredFields()) {
//            if (!field.getName().startsWith("_") && !field.getName().contains("FIELD_DESC")
//                    &&!field.getName().contains("STRUCT_DESC")
//                    && !field.getName().contains("schemes")
//                    && !field.getName().contains("optionals")
//                    && !field.getName().contains("MetaDataMap")){
//                System.out.println(String.format(SET_CLAUSE, "requestObject",StringUtil.capitalize(field.getName()),
//                        String.format(CONTEXT_SAMPLECODE,field.getName())
//                ));
//            }
//
//        }
//    }
//
//}
