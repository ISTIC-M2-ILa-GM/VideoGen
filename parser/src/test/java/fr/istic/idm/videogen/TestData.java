package fr.istic.idm.videogen;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class TestData {


    private static final PodamFactory PODAM_FACTORY = new PodamFactoryImpl();

    public static <T> T some(Class<T> clazz) {
        return PODAM_FACTORY.manufacturePojoWithFullData(clazz);
    }
}
