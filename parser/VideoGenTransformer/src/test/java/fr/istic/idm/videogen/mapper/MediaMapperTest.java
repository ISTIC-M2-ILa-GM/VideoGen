package fr.istic.idm.videogen.mapper;

import fr.istic.idm.videogen.generated.videoGen.AlternativesMedia;
import fr.istic.idm.videogen.generated.videoGen.MandatoryMedia;
import fr.istic.idm.videogen.generated.videoGen.Media;
import fr.istic.idm.videogen.generated.videoGen.OptionalMedia;
import fr.istic.idm.videogen.generated.videoGen.impl.AlternativesMediaImpl;
import fr.istic.idm.videogen.generated.videoGen.impl.MandatoryMediaImpl;
import fr.istic.idm.videogen.generated.videoGen.impl.MediaDescriptionImpl;
import fr.istic.idm.videogen.generated.videoGen.impl.OptionalMediaImpl;
import fr.istic.idm.videogen.mapper.impl.MediaMapperImpl;
import fr.istic.idm.videogen.model.MediaType;
import fr.istic.idm.videogen.model.ParsedMedia;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;

public class MediaMapperTest {

    private MediaMapper mediaMapper;

    @Before
    public void setUp() {
        mediaMapper = new MediaMapperImpl();
    }

    @Test
    public void shouldMapANullMedia() {
        assertThat(mediaMapper.toParsedMedia(null), empty());
    }

    @Test
    public void shouldMapAMandatoryMedia() {

        MandatoryMedia media = new MandatoryMediaImpl();
        media.setDescription(new MediaDescriptionImpl());
        media.getDescription().setLocation("location");

        List<ParsedMedia> parsedMedias = mediaMapper.toParsedMedia(media);

        assertThat(parsedMedias, notNullValue());
        assertThat(parsedMedias, hasSize(1));
        ParsedMedia parsedMedia = parsedMedias.get(0);
        assertThat(parsedMedia, notNullValue());
        assertThat(parsedMedia.isActive(), equalTo(true));
        assertThat(parsedMedia.getFileName(), equalTo("location"));
        assertThat(parsedMedia.getType(), equalTo(MediaType.MANDATORY));
        assertThat(parsedMedia.getTotalAlternative(), equalTo(0));
    }

    @Test
    public void shouldMapAnOptionalMedia() {

        OptionalMedia media = new OptionalMediaImpl();
        media.setDescription(new MediaDescriptionImpl());
        media.getDescription().setLocation("location");

        List<ParsedMedia> parsedMedias = mediaMapper.toParsedMedia(media);

        assertThat(parsedMedias, notNullValue());
        assertThat(parsedMedias, hasSize(1));
        ParsedMedia parsedMedia = parsedMedias.get(0);
        assertThat(parsedMedia, notNullValue());
        assertThat(parsedMedia.isActive(), equalTo(false));
        assertThat(parsedMedia.getFileName(), equalTo("location"));
        assertThat(parsedMedia.getType(), equalTo(MediaType.OPTIONAL));
        assertThat(parsedMedia.getTotalAlternative(), equalTo(0));
    }

    @Test
    public void shouldMapAnAlternativeMedia() {

        MediaDescriptionImpl mediaDescription1 = new MediaDescriptionImpl();
        mediaDescription1.setLocation("1");
        MediaDescriptionImpl mediaDescription2 = new MediaDescriptionImpl();
        mediaDescription2.setLocation("2");
        AlternativesMedia media = new AlternativesMediaImpl();
        media.getMedias().add(mediaDescription1);
        media.getMedias().add(mediaDescription2);

        List<ParsedMedia> parsedMedias = mediaMapper.toParsedMedia(media);

        assertThat(parsedMedias, notNullValue());
        assertThat(parsedMedias, hasSize(2));
        assertThat(parsedMedias.get(0).getTotalAlternative(), equalTo(2));
        assertThat(parsedMedias.get(0).getFileName(), equalTo("1"));
        assertThat(parsedMedias.get(0).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).getTotalAlternative(), equalTo(2));
        assertThat(parsedMedias.get(1).getFileName(), equalTo("2"));
        assertThat(parsedMedias.get(1).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
    }

    @Test
    public void shouldMapANullListOfMedia() {
        assertThat(mediaMapper.toParsedMedias(null), empty());
    }
}