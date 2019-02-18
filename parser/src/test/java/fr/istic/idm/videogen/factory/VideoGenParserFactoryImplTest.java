package fr.istic.idm.videogen.factory;

import fr.istic.idm.videogen.factory.impl.VideoGenParserFactoryImpl;
import fr.istic.idm.videogen.generated.VideoGenHelper;
import fr.istic.idm.videogen.parser.VideoGenParser;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VideoGenParserFactoryImplTest {

    @Mock
    private VideoGenHelper mockVideoGenHelper;

    @InjectMocks
    private VideoGenParserFactoryImpl videoGenParserFactory;

    @Test
    public void shouldCreateAVideoGenParser() {

        VideoGenParser videoGenParser = videoGenParserFactory.create("path");

        verify(mockVideoGenHelper).loadVideoGenerator(URI.createURI("path"));

        assertThat(videoGenParser, notNullValue());
    }
}
