package fr.istic.idm.videogen.mapper.impl;

import fr.istic.idm.videogen.generated.videoGen.AlternativesMedia;
import fr.istic.idm.videogen.generated.videoGen.MandatoryMedia;
import fr.istic.idm.videogen.generated.videoGen.Media;
import fr.istic.idm.videogen.generated.videoGen.OptionalMedia;
import fr.istic.idm.videogen.generated.videoGen.impl.AlternativesMediaImpl;
import fr.istic.idm.videogen.generated.videoGen.impl.MandatoryMediaImpl;
import fr.istic.idm.videogen.generated.videoGen.impl.MediaDescriptionImpl;
import fr.istic.idm.videogen.generated.videoGen.impl.OptionalMediaImpl;
import fr.istic.idm.videogen.mapper.MediaMapper;
import fr.istic.idm.videogen.mapper.impl.MediaMapperImpl;
import fr.istic.idm.videogen.model.MediaType;
import fr.istic.idm.videogen.model.ParsedMedia;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;

public class MediaMapperImplTest {

    private MediaMapperImpl mediaMapper;

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
        assertThat(parsedMedia.getCurrentAlternatives(), equalTo(0));
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
        assertThat(parsedMedia.getCurrentAlternatives(), equalTo(0));
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
        assertThat(parsedMedias.get(0).getCurrentAlternatives(), equalTo(2));
        assertThat(parsedMedias.get(0).getIndex(), equalTo(0));
        assertThat(parsedMedias.get(0).getFileName(), equalTo("1"));
        assertThat(parsedMedias.get(0).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedias.get(0).isActive(), equalTo(false));
        assertThat(parsedMedias.get(1).getCurrentAlternatives(), equalTo(2));
        assertThat(parsedMedias.get(1).getIndex(), equalTo(1));
        assertThat(parsedMedias.get(1).getFileName(), equalTo("2"));
        assertThat(parsedMedias.get(1).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedias.get(1).isActive(), equalTo(false));
    }

    @Test
    public void shouldMapANullListOfMedia() {
        assertThat(mediaMapper.toParsedMedias(null), empty());
    }

    @Test
    public void shouldMapAListOfMedia() {

        AlternativesMedia alternative1 = new AlternativesMediaImpl();
        alternative1.getMedias().add(new MediaDescriptionImpl());
        alternative1.getMedias().add(new MediaDescriptionImpl());

        AlternativesMedia alternative2 = new AlternativesMediaImpl();
        alternative2.getMedias().add(new MediaDescriptionImpl());
        alternative2.getMedias().add(new MediaDescriptionImpl());

        OptionalMedia optionalMedia = new OptionalMediaImpl();
        optionalMedia.setDescription(new MediaDescriptionImpl());

        MandatoryMedia mandatoryMedia = new MandatoryMediaImpl();
        mandatoryMedia.setDescription(new MediaDescriptionImpl());

        List<Media> medias = asList(alternative1, alternative2, optionalMedia, mandatoryMedia);

        List<ParsedMedia> parsedMedia = mediaMapper.toParsedMedias(medias);

        assertThat(parsedMedia, notNullValue());
        assertThat(parsedMedia, hasSize(6));
        assertThat(parsedMedia.get(0).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedia.get(0).getPreviousAlternatives(), equalTo(1));
        assertThat(parsedMedia.get(1).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedia.get(1).getPreviousAlternatives(), equalTo(1));
        assertThat(parsedMedia.get(2).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedia.get(2).getPreviousAlternatives(), equalTo(2));
        assertThat(parsedMedia.get(3).getType(), equalTo(MediaType.ALTERNATIVE));
        assertThat(parsedMedia.get(3).getPreviousAlternatives(), equalTo(2));
        assertThat(parsedMedia.get(4).getType(), equalTo(MediaType.OPTIONAL));
        assertThat(parsedMedia.get(5).getType(), equalTo(MediaType.MANDATORY));
    }
}