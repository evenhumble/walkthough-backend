package io.hedwig.refactoring.replaceConditionWithPolymorphism;

/**
 * Created by patrick on 15/11/4.
 */
public class Employee extends Customer {
    @Override
    double tax() {
        return 0.15;
    }
}
