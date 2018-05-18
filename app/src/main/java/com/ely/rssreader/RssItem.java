
package com.ely.rssreader;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "item", strict = false)
public class RssItem implements Parcelable {

    private String noTitle = "NG photo";
    private String noDescription = "Looks like we are missing the description";
    @Element(name = "title")
    private String title;
    @Element(name = "pubDate")
    private String date;
    @Element(name = "link")
    private String linkToArticle;
    @Element(name = "description")
    private String description;
    @Element(name = "enclosure")
    private RssImage RssImage;

    public RssItem() {
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RssItem> CREATOR = new Parcelable.Creator<RssItem>() {
        @Override
        public RssItem createFromParcel(Parcel in) {
            return new RssItem(in);
        }

        @Override
        public RssItem[] newArray(int size) {
            return new RssItem[size];
        }
    };

    protected RssItem(Parcel in) {
        noTitle = in.readString();
        noDescription = in.readString();
        title = in.readString();
        date = in.readString();
        linkToArticle = in.readString();
        description = in.readString();
        RssImage = (RssImage) in.readValue(RssImage.class.getClassLoader());
    }

    public String getTitle() {
        if (TextUtils.isEmpty(title)) {
            title = noTitle;
        }
        return title;
    }

    public String getLinkToArticle() {
        return linkToArticle;
    }

    public String getDescription() {
        if (TextUtils.isEmpty(description)) {
            description = noDescription;
        }
        adjustDescription();
        return description;
    }

    private void adjustDescription() {
        description = description.replaceAll("<p>", "");
        String[] trimmedParts = description.split("</p>");
        description = trimmedParts[0];
    }

    public RssImage getRssImage() {
        return RssImage;
    }

    public String getDate() {
        date = date.replace("00:00:00 -0400", "");
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noTitle);
        dest.writeString(noDescription);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(linkToArticle);
        dest.writeString(description);
        dest.writeValue(RssImage);
    }

}