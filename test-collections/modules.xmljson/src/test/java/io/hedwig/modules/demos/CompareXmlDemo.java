package io.hedwig.modules.demos;

import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.*;


public class CompareXmlDemo {

    public static void main(String[] args) {
//        Source control = Input.fromFile("test-data/output.xml").build();
//        Source test = Input.fromFile("test-data/output_1.xml").build();
//        DifferenceEngine diff = new DOMDifferenceEngine();
//        diff.addDifferenceListener((comparison, outcome) -> {
//            System.out.println("found a difference: " + comparison);
//        });
//        diff.compare(control, test);

        Diff diff1 = DiffBuilder.compare(
            Input.fromFile("test-data/output.xml"))
                .withTest(Input.fromFile("test-data/output_1.xml"))
            .ignoreComments()
            .build();



        for (Difference difference : diff1.getDifferences()) {
            System.out.println(difference);
            System.out.println(difference.getComparison().getControlDetails().getXPath());
            System.out.println(difference.getComparison().getTestDetails().getXPath());

        }
    }
}
