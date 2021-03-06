package com.ely.rssreader;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss", strict = false)
public class RssFeedWrapper {

    @ElementList(name="item", inline=true)
    @Path("channel")
    private List<RssItem> rssItemsList;

    public List<RssItem> getRssItemsList() {
        return rssItemsList;
    }
}
