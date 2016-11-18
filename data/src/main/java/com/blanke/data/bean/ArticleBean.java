package com.blanke.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by blanke on 16-6-1.
 */
public class ArticleBean implements Serializable {

    /**
     * image_source : 《豪斯医生》
     * title : 深夜惊奇 · 要穿内衣
     * image : http://pic4.zhimg.com/595550e659c4880d00bb5bea6953666f.jpg
     * share_url : http://daily.zhihu.com/story/8387524
     * js : []
     * ga_prefix : 060122
     * section : {"thumbnail":"http://pic3.zhimg.com/91125c9aebcab1c84f58ce4f8779551e.jpg","id":1,"name":"深夜惊奇"}
     * images : ["http://pic3.zhimg.com/91125c9aebcab1c84f58ce4f8779551e.jpg"]
     * type : 0
     * id : 8387524
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    /**
     * thumbnail : http://pic3.zhimg.com/91125c9aebcab1c84f58ce4f8779551e.jpg
     * id : 1
     * name : 深夜惊奇
     */

    private SectionBean section;
    private int type;
    private int id;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public SectionBean getSection() {
        return section;
    }

    public void setSection(SectionBean section) {
        this.section = section;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public static class SectionBean implements Serializable {
        private String thumbnail;
        private int id;
        private String name;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "SectionBean{" +
                    "thumbnail='" + thumbnail + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ArticleBean{" +
                "body='" + (body == null) + '\'' +
                ", image_source='" + image_source + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", share_url='" + share_url + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", section=" + section +
                ", type=" + type +
                ", id=" + id +
                ", js=" + js +
                ", images=" + images +
                ", css=" + css +
                '}';
    }
}
