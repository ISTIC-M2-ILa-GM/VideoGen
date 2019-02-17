package fr.istic.idm.videogen.factory;

import fr.istic.idm.videogen.factory.impl.RandomVideoGenGeneratorFactoryImpl;
import fr.istic.idm.videogen.randomizer.RandomVideoGenGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RandomVideoGenGeneratorFactoryTest {

    private RandomVideoGenGeneratorFactory randomVideoGenGeneratorFactory;

    @Mock
    private VideoGenParserFactory mockVideoGenParserFactory;

    @Before
    public void setUp() {
        randomVideoGenGeneratorFactory = new RandomVideoGenGeneratorFactoryImpl(mockVideoGenParserFactory);
    }

    @Test
    public void shouldCreateARandomVideoGenGenerator() {

        RandomVideoGenGenerator randomVideoGenGenerator = randomVideoGenGeneratorFactory.create("a-file");

        verify(mockVideoGenParserFactory).create("a-file");

        assertThat(randomVideoGenGenerator, notNullValue());
    }
}
