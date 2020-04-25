package com.news.elvezanews.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsModelList{
    public NewsModelList(String newsID, String releaseTS, String headline, String bodyText, String mainImage, String mainVideo, String attachedIMGList, String category) {
        NewsID = newsID;
        ReleaseTS = releaseTS;
        Headline = headline;
        BodyText = bodyText;
        MainImage = mainImage;
        MainVideo = mainVideo;
        AttachedIMGList = attachedIMGList;
        Category = category;
    }

    public String NewsID;
    public String ReleaseTS;
    public String Headline;
    public String BodyText;
    public String MainImage;
    public String MainVideo;
    public String AttachedIMGList;
    public String Category;

    public String getNewsID() {
        return NewsID;
    }

    public void setNewsID(String newsID) {
        NewsID = newsID;
    }

    public String getReleaseTS() {
        return ReleaseTS;
    }

    public String getMainImage() {
        return MainImage;
    }

    public void setMainImage(String mainImage) {
        MainImage = mainImage;
    }

    public void setReleaseTS(String releaseTS) {
        ReleaseTS = releaseTS;
    }

    public String getHeadline() {
        return Headline;
    }

    public void setHeadline(String headline) {
        Headline = headline;
    }

    public String getBodyText() {
        return BodyText;
    }

    public void setBodyText(String bodyText) {
        BodyText = bodyText;
    }

    public String getAttachedIMGList() {
        return AttachedIMGList;
    }

    public void setAttachedIMGList(String attachedIMGList) {
        AttachedIMGList = attachedIMGList;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getMainVideo() {
        return MainVideo;
    }

    public void setMainVideo(String mainVideo) {
        MainVideo = mainVideo;
    }
}
