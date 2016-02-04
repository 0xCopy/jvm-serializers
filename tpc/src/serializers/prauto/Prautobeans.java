package serializers.prauto;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import media.prauto.*;
import prauto.ann.Optional;
import prauto.ann.ProtoNumber;
import prauto.io.PackedPayload;
import serializers.*;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.Collectors;

public class Prautobeans {


    /**
     * bring in autobeans duck typing.  not fast, but cheap.
     */
    interface XFac extends AutoBeanFactory {
        AutoBean<Image> createImage();

        AutoBean<Media> createMedia();

        AutoBean<MediaContent> createMediaContent();
    }

    public static final PackedPayload<MediaContent> MEDIA_CONTENT_PACKED_PAYLOAD = PackedPayload.create(MediaContent.class);

    public static void register(TestGroups groups) {
        SerFeatures features = new SerFeatures(SerFormat.BINARY, SerGraph.FLAT_TREE, SerClass.CLASSES_KNOWN);
        groups.media.add(new Transformer<data.media.MediaContent, media.prauto.MediaContent>() {
            @Override
            public MediaContent forward(data.media.MediaContent mediaContent) {
                return new fwdMediaContent(mediaContent);
            }

            @Override
            public data.media.MediaContent reverse(MediaContent a) {
                return new revMediaContent(a);
            }

            @Override
            public data.media.MediaContent shallowReverse(MediaContent a) {
                //todo:: rename ambiguousreverse -- no docs specifying...
                return new revMediaContent(a);
            }

            @Override
            public data.media.MediaContent[] sourceArray(int size) {
                return new data.media.MediaContent[size];
            }

            @Override
            public MediaContent[] resultArray(int size) {
                return new MediaContent[size];
            }

            ;
        }, new Serializer<MediaContent>() {
            @Override
            public MediaContent deserialize(byte[] array) throws Exception {

                ByteBuffer wrap = ByteBuffer.wrap(array);
                MediaContent mediaContent = MEDIA_CONTENT_PACKED_PAYLOAD.get(MediaContent.class, wrap);
                return mediaContent;
            }

            @Override
            public byte[] serialize(MediaContent content) throws Exception {
                ByteBuffer allocate = ByteBuffer.allocate(BUFFER_SIZE);
                MEDIA_CONTENT_PACKED_PAYLOAD.put(content, allocate);
                return (byte[]) allocate.flip().array();

            }

            @Override
            public String getName() {
                return "prautobeans";
            }

        }, features);
    }

    private static class reverseMedia extends data.media.Media {
        private final Media media;

        public reverseMedia(Media media) {
            this.media = media;
        }

        @ProtoNumber(3)
        public int getWidth() {
            return media.getWidth();
        }

        public void setWidth(int i) {
            media.setWidth(i);
        }

        @ProtoNumber(4)
        public int getHeight() {
            return media.getHeight();
        }

        public void setHeight(int i) {
            media.setHeight(i);
        }

        @Optional(1)
        @ProtoNumber(8)
        public int getBitrate() {
            return media.getBitrate();
        }

        public void setBitrate(int $bitrate$) {
            media.setBitrate($bitrate$);
        }

        @ProtoNumber(6)
        public long getDuration() {
            return media.getDuration();
        }

        public void setDuration(long l) {
            media.setDuration(l);
        }

        @ProtoNumber(7)
        public long getSize() {
            return media.getSize();
        }

        public void setSize(long l) {
            media.setSize(l);
        }

        @ProtoNumber(1)
        public String getUri() {
            return media.getUri();
        }

        public void setUri(String s) {
            media.setUri(s);
        }

        @Optional(2)
        @ProtoNumber(2)
        public String getTitle() {
            return media.getTitle();
        }

        public void setTitle(String $title$) {
            media.setTitle($title$);
        }

        @ProtoNumber(5)
        public String getFormat() {
            return media.getFormat();
        }

        public void setFormat(String s) {
            media.setFormat(s);
        }

        @ProtoNumber(9)
        public List<String> getPerson() {
            return media.getPerson();
        }

        public void setPerson(List<String> list) {
            media.setPerson(list);
        }

        @Optional(3)
        @ProtoNumber(11)
        public String getCopyright() {
            return media.getCopyright();
        }

        public void setCopyright(String $copyright$) {
            media.setCopyright($copyright$);
        }

        @ProtoNumber(10)
        public Player getPlayer() {
            return Player.values()[media.getPlayer().ordinal()];
        }

        public void setPlayer(media.prauto.Player player) {
            media.setPlayer(player);
        }
    }

    private static class FwdMedia implements Media {
        private final data.media.Media media;

        public FwdMedia(data.media.Media media) {
            this.media = media;
        }

        public void setUri(String uri) {
            media.setUri(uri);
        }

        public void setTitle(String title) {
            media.setTitle(title);
        }

        public void setWidth(int width) {
            media.setWidth(width);
        }

        public void setHeight(int height) {
            media.setHeight(height);
        }

        public void setFormat(String format) {
            media.setFormat(format);
        }

        @Override
        public List<String> getPerson() {
            return media.getPersons();
        }

        @Override
        public void setPerson(List<String> list) {
            media.setPersons(list);
        }

        public void setDuration(long duration) {
            media.setDuration(duration);
        }

        public void setSize(long size) {
            media.setSize(size);
        }

        public void setBitrate(int bitrate) {
            media.setBitrate(bitrate);
        }

        public void setPersons(List<String> persons) {
            media.setPersons(persons);
        }

        public void setPlayer(data.media.Media.Player player) {
            media.setPlayer(player);
        }

        public void setCopyright(String copyright) {
            media.setCopyright(copyright);
        }

        public String getUri() {
            return media.getUri();
        }

        public String getTitle() {
            return media.getTitle();
        }

        public int getWidth() {
            return media.getWidth();
        }

        public int getHeight() {
            return media.getHeight();
        }

        public String getFormat() {
            return media.getFormat();
        }

        public long getDuration() {
            return media.getDuration();
        }

        public long getSize() {
            return media.getSize();
        }

        public int getBitrate() {
            return media.getBitrate();
        }

        public List<String> getPersons() {
            return media.getPersons();
        }

        public Player getPlayer() {
            return Player.values()[media.getPlayer().ordinal()];
        }

        @Override
        public void setPlayer(Player player) {
            media.setPlayer(data.media.Media.Player.values()[player.ordinal()]);
        }

        public String getCopyright() {
            return media.getCopyright();
        }
    }

    private static class reverseImage extends data.media.Image {
        private final Image image;

        public reverseImage(Image image) {
            this.image = image;
        }

        public void setSize(media.prauto.Size size) {
            image.setSize(size);
        }

        @ProtoNumber(3)
        public int getWidth() {
            return image.getWidth();
        }

        public void setWidth(int i) {
            image.setWidth(i);
        }

        @ProtoNumber(4)
        public int getHeight() {
            return image.getHeight();
        }

        public void setHeight(int i) {
            image.setHeight(i);
        }

        @ProtoNumber(1)
        public String getUri() {
            return image.getUri();
        }

        public void setUri(String s) {
            image.setUri(s);
        }

        @Optional(1)
        @ProtoNumber(2)
        public String getTitle() {
            return image.getTitle();
        }

        public void setTitle(String $title$) {
            image.setTitle($title$);
        }

        @ProtoNumber(5)
        public Size getSize() {
            return Size.values()[image.getSize().ordinal()];
        }
    }

    private static class fwdImage implements Image {
        private final data.media.Image image;

        public fwdImage(data.media.Image image) {
            this.image = image;
        }

        @Override
        public String getTitle() {
            return image.getTitle();
        }

        public int getWidth() {
            return image.getWidth();
        }

        public int getHeight() {
            return image.getHeight();
        }

        public Size getSize() {
            return Size.values()[image.getSize().ordinal()];
        }

        @Override
        public void setSize(Size size) {
            image.setSize(data.media.Image.Size.values()[size.ordinal()]);
        }

        public void setSize(data.media.Image.Size size) {
            image.setSize(size);
        }

        @Override
        public boolean equals(Object o) {
            return image.equals(o);
        }

        @Override
        public int hashCode() {
            return image.hashCode();
        }

        @Override
        public String toString() {
            return image.toString();
        }

        public void setUri(String uri) {
            image.setUri(uri);
        }

        @Override
        public void setTitle(String $title$) {
            image.setTitle($title$);
        }

        public void setWidth(int width) {
            image.setWidth(width);
        }

        public void setHeight(int height) {
            image.setHeight(height);
        }

        public String getUri() {
            return image.getUri();
        }
    }

    private static class fwdMediaContent implements MediaContent {

        private final data.media.MediaContent mediaContent;

        public fwdMediaContent(data.media.MediaContent mediaContent) {
            this.mediaContent = mediaContent;
        }

        public void setImages(List<data.media.Image> images) {
            mediaContent.setImages(images);
        }

        public List<data.media.Image> getImages() {
            return mediaContent.getImages();
        }


        @Override
        public List<Image> getImage() {
            return mediaContent.getImages().stream().map(image -> new fwdImage(image)).collect(Collectors.toList());
        }

        @Override
        public void setImage(List<Image> list) {
            mediaContent.setImages(list.stream().map(image -> new reverseImage(image)).collect(Collectors.toList()));
        }

        @Override
        public Media getMedia() {
            final data.media.Media media = mediaContent.getMedia();

            return new FwdMedia(media);
        }

        ;

        @Override
        public void setMedia(Media media) {

            mediaContent.setMedia(new reverseMedia(media));
        }

        ;


    }

    private static class revMediaContent extends data.media.MediaContent {
        private final MediaContent a;

        public revMediaContent(MediaContent a) {
            this.a = a;
        }

        @ProtoNumber(1)
        public List<Image> getImage() {
            return a.getImage();
        }

        public void setImage(List<Image> list) {
            a.setImage(list);
        }

        public void setMedia(Media media) {
            a.setMedia(media);
        }

        @ProtoNumber(2)
        public data.media.Media getMedia() {
            return new reverseMedia(a.getMedia());
        }

    }
}
