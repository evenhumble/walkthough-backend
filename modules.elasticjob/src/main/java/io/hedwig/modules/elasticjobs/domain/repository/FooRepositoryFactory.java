package io.hedwig.modules.elasticjobs.domain.repository;

public final class FooRepositoryFactory {

    private static FooRepository fooRepository = new FooRepository();

    public static FooRepository getFooRepository() {
        return fooRepository;
    }

}
