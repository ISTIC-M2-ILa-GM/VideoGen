package fr.istic.idm.videogen;

import fr.istic.idm.videogen.factory.RandomVideoGenGeneratorFactory;
import fr.istic.idm.videogen.factory.impl.RandomVideoGenGeneratorFactoryImpl;
import fr.istic.idm.videogen.factory.impl.VideoGenParserFactoryImpl;
import fr.istic.idm.videogen.generated.VideoGenHelper;
import fr.istic.idm.videogen.mapper.impl.MediaMapperImpl;
import fr.istic.idm.videogen.randomizer.RandomVideoGenGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;

public class VideoGenTransformerIntegrationTest {

    private RandomVideoGenGeneratorFactory randomVideoGenGeneratorFactory;

    @Before
    public void setUp() {
        randomVideoGenGeneratorFactory = new RandomVideoGenGeneratorFactoryImpl(new VideoGenParserFactoryImpl(new VideoGenHelper(), new MediaMapperImpl()));
    }

    @Test
    public void shouldGenerateAMandatoryVideo() {

        RandomVideoGenGenerator randomVideoGenGenerator = randomVideoGenGeneratorFactory.create("target/test-classes/mandatory.videogen");

        List<String> files = randomVideoGenGenerator.generateRandomConfiguration();

        assertThat(files, notNullValue());
        assertThat(files, hasSize(2));
        assertThat(files, hasItem("v1.mp4"));
        assertThat(files, hasItem("v2.mp4"));
    }
}
