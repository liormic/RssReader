package com.ely.rssreader;
import android.os.Parcel;
import android.os.Parcelable;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "enclosure", strict = false)
public class RssImage implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RssImage> CREATOR = new Parcelable.Creator<RssImage>() {
        @Override
        public RssImage createFromParcel(Parcel in) {
            return new RssImage(in);
        }

        @Override
        public RssImage[] newArray(int size) {
            return new RssImage[size];
        }
    };
    @Attribute(name = "url")
    private String url;

    public RssImage() {

    }

    protected RssImage(Parcel in) {
        url = in.readString();
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }
}