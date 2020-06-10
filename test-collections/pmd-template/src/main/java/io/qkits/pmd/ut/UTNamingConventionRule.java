package io.qkits.pmd.ut;

import net.sf.saxon.functions.StringJoin;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class UTNamingConventionRule extends AbstractJavaRule {

    private static List<String> allowedEndings = Arrays.asList(
            "IntegrationTest",
            "IntTest",
            "ManualTest",
            "SlowTest",
            "FlakyTest",
            "JdbcTest",
            "UnitTest",
            "jmhTest",
            "Test",
            "Tests");

    @Override
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        String className = node.getSimpleName();
        System.out.println("scanning class,%s".format(className));
        Objects.requireNonNull(className, "className is not valid");
        if (className.endsWith("SpringContextTest")) return data;
        if (allowedEndings.stream().noneMatch(className::endsWith)) {
            addViolation(data, node);
            System.out.println(String.format("%s violate UT naming convention,the name should end with %s", className,
                    StringUtils.join(allowedEndings,",")));
        }

        return data;
    }
}
