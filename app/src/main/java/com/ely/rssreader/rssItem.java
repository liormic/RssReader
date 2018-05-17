package com.ely.rssreader;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


@Root(name = "item", strict = false)
public class rssItem {
    @Element(name = "title")
    private String title;

    @Element(name = "link")
    private String linkToArticle;

    @Element(name ="description")
    private String description;

    @ElementList(name = "enclosure",inline=true)
    private List<String> imageDetails;

    public String getTitle() {
        return title;
    }

    public String getLinkToArticle() {
        return linkToArticle;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return imageDetails.get(0);
    }


}
