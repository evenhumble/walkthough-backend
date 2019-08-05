package io.hedwig.notes.reflections;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import io.hedwig.notes.reflections.tobescaned.AnnotationSample;
import io.hedwig.notes.reflections.tobescaned.EntitySample;

public class ReflectionSamples {


    public static void main(String[] args) {
        Reflections reflections =
            new Reflections("io.hedwig.notes.reflections.tobescaned");
        //or using ConfigurationBuilder
//        new Reflections(new ConfigurationBuilder()
//                            .setUrls(ClasspathHelper.forPackage("my.project.prefix"))
//                            .setScanners(new SubTypesScanner(),
//                                         new TypeAnnotationsScanner().filterResultsBy(optionalFilter), ...),
//     .filterInputsBy(new FilterBuilder().includePackage("my.project.prefix"))
//     ...);

        Set<Class<? extends EntitySample>> entities =
            reflections.getSubTypesOf(EntitySample.class);

        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(AnnotationSample.class);

        System.out.println(entities);
        System.out.println(annotated);

//        //SubTypesScanner
//        Set<Class<? extends Module>> modules =
//            reflections.getSubTypesOf(com.google.inject.Module.class);
//        //TypeAnnotationsScanner
//        Set<Class<?>> singletons =
//            reflections.getTypesAnnotatedWith(javax.inject.Singleton.class);

        //ResourcesScanner:ResouceScanner configuration
//        Set<String> properties =
//            reflections.getResources(Pattern.compile(".*\\.properties"));
//        System.out.println(properties);

//FieldAnnotationsScanner
        Set<Field> ids =
            reflections.getFieldsAnnotatedWith(AnnotationSample.class);
        System.out.println(ids);

        //MethodParameterScanner
        Set<Method> someMethods =
            reflections.getMethodsMatchParams(long.class, int.class);
        Set<Method> voidMethods =
            reflections.getMethodsReturn(void.class);
//        Set<Method> pathParamMethods =
//            reflections.getMethodsWithAnyParamAnnotated(PathParam.class);
        //MethodParameterNamesScanner
//        List<String> parameterNames =
//            reflections.getMethodParamNames(Method.class)
        //MemberUsageScanner
//        Set<Member> usages =
//            reflections.getMethodUsages(Method.class)

    }
}
