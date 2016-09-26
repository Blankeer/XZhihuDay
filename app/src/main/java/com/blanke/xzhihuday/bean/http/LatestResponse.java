package com.blanke.xzhihuday.bean.http;

import com.blanke.xzhihuday.bean.ArticleBean;

import java.util.Date;
import java.util.List;

/**
 * Created by blanke on 16-6-1.
 */
public class LatestResponse {

    /**
     * date : 20160601
     * stories : [{"images":["http://pic3.zhimg.com/91125c9aebcab1c84f58ce4f8779551e.jpg"],"type":0,"id":8387524,"ga_prefix":"060122","title":"深夜惊奇 · 要穿内衣"},{"title":"《指环王》里的白袍巫师，成了有味道的老年版福尔摩斯","ga_prefix":"060121","images":["http://pic4.zhimg.com/349f55ed5db027bb03bf7e28a4411f13.jpg"],"multipic":true,"type":0,"id":8388003},{"images":["http://pic3.zhimg.com/37c1100e08b85e0a4d7e5adcffbfa006.jpg"],"type":0,"id":8362455,"ga_prefix":"060120","title":"作为一个游客，在岸边遇到搁浅的鲸鱼怎么办？"},{"images":["http://pic2.zhimg.com/2926ed20c23da7e4e76d38e1fe9a81c9.jpg"],"type":0,"id":8387810,"ga_prefix":"060119","title":"在六月这个「骄傲月」，聊聊性与性别"},{"title":"听腻了各版本的《南山南》，这些民谣够你再循环一阵","ga_prefix":"060118","images":["http://pic2.zhimg.com/b82dd5b9e1cc4943c86371c607d1b0c5.jpg"],"multipic":true,"type":0,"id":8388035},{"title":"日本有个世界级的艺术节，中国人却很少知道","ga_prefix":"060117","images":["http://pic4.zhimg.com/eaed4ffe25fba2c9406ab66ee0d814ff.jpg"],"multipic":true,"type":0,"id":8387651},{"images":["http://pic1.zhimg.com/29d78a0b968eb648b699f6a98b827004.jpg"],"type":0,"id":8387813,"ga_prefix":"060116","title":"体温计量出来 37.3℃，我到底发烧了没？"},{"images":["http://pic2.zhimg.com/dc318807dc1bea651d93ba37d05fdf41.jpg"],"type":0,"id":8383075,"ga_prefix":"060115","title":"《进击的巨人》中优秀的新兵反而被分配去安全的团中，合理吗？"},{"images":["http://pic2.zhimg.com/fbbc21616d084e8b88a986660f36d009.jpg"],"type":0,"id":8387308,"ga_prefix":"060114","title":"一个巨没有存在感、很多人从来都没听出来过的乐器"},{"images":["http://pic1.zhimg.com/b00267043be34bef8cc1d1b9d58c55c4.jpg"],"type":0,"id":8386821,"ga_prefix":"060113","title":"花了几次冤枉钱，我总结了挑日料店的正确方法"},{"images":["http://pic2.zhimg.com/b86e70e84d69b504854d7c8e4fbedb89.jpg"],"type":0,"id":8385278,"ga_prefix":"060112","title":"大误 · 英国贵族风《红楼梦》"},{"images":["http://pic2.zhimg.com/dbce87a8194793be2dd839da9a4b187d.jpg"],"type":0,"id":8381327,"ga_prefix":"060111","title":"越成功的人越自恋？那我为什么还没成功\u2026\u2026"},{"images":["http://pic3.zhimg.com/507bb9ffe6255732b05103ced9170e96.jpg"],"type":0,"id":8383417,"ga_prefix":"060110","title":"怎么租房子不上当？作为房产律师，我有这些建议"},{"images":["http://pic2.zhimg.com/d4e37ee807c87542a1c65f06281dfe45.jpg"],"type":0,"id":8381308,"ga_prefix":"060109","title":"离婚后「被负债」300 万，婚姻法的错？"},{"images":["http://pic1.zhimg.com/f5d9c95912a887b67f984ed5de1b07a4.jpg"],"type":0,"id":8385068,"ga_prefix":"060108","title":"人人都在看「大」数据，费力调查出的数据还有用吗？"},{"images":["http://pic4.zhimg.com/b540a94044847fc4595dc9e4fc17f6d7.jpg"],"type":0,"id":8382455,"ga_prefix":"060107","title":"哺乳期妈妈吃什么，对宝宝的影响其实没那么大"},{"images":["http://pic4.zhimg.com/8089b583d982abbdcdbcbb5eb47062d7.jpg"],"type":0,"id":8383133,"ga_prefix":"060107","title":"网红电商火得不要不要的，符合这些条件的才好拿钱"},{"title":"教你在城市里看日出，开启元气满满的一天","ga_prefix":"060107","images":["http://pic4.zhimg.com/56b192ac1fe1ff2a3063a869d8c7dbff.jpg"],"multipic":true,"type":0,"id":8377594},{"images":["http://pic4.zhimg.com/718cd59d3a4fa1e5a8fb645acf121b9b.jpg"],"type":0,"id":8385108,"ga_prefix":"060107","title":"读读日报 24 小时热门 TOP 5 · 把童年的动漫金曲串烧着弹了一遍"},{"images":["http://pic1.zhimg.com/0b432e9c361d9e785e1c3ee3a06ea138.jpg"],"type":0,"id":8382896,"ga_prefix":"060106","title":"瞎扯 · 熊孩子"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/d3b732cae295a563e5002c6c6f5531e8.jpg","type":0,"id":8387810,"ga_prefix":"060119","title":"在六月这个「骄傲月」，聊聊性与性别"},{"image":"http://pic4.zhimg.com/80e38fc631d7f1956660d1b50ca4ae9b.jpg","type":0,"id":8383075,"ga_prefix":"060115","title":"《进击的巨人》中优秀的新兵反而被分配去安全的团中，合理吗？"},{"image":"http://pic4.zhimg.com/a38c31e6dfc87a8bd7b288d50e08dc17.jpg","type":0,"id":8385108,"ga_prefix":"060107","title":"读读日报 24 小时热门 TOP 5 · 把童年的动漫金曲串烧着弹了一遍"},{"image":"http://pic4.zhimg.com/de0513f4fe7c34e4151effb66c704393.jpg","type":0,"id":8383133,"ga_prefix":"060107","title":"网红电商火得不要不要的，符合这些条件的才好拿钱"},{"image":"http://pic4.zhimg.com/40ed00f47a14baa77c29e886e6924d97.jpg","type":0,"id":8377594,"ga_prefix":"060107","title":"教你在城市里看日出，开启元气满满的一天"}]
     */

    private Date date;
    /**
     * images : ["http://pic3.zhimg.com/91125c9aebcab1c84f58ce4f8779551e.jpg"]
     * type : 0
     * id : 8387524
     * ga_prefix : 060122
     * title : 深夜惊奇 · 要穿内衣
     */

    private List<ArticleBean> stories;
    /**
     * image : http://pic1.zhimg.com/d3b732cae295a563e5002c6c6f5531e8.jpg
     * type : 0
     * id : 8387810
     * ga_prefix : 060119
     * title : 在六月这个「骄傲月」，聊聊性与性别
     */

    private List<ArticleBean> top_stories;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ArticleBean> getStories() {
        return stories;
    }

    public void setStories(List<ArticleBean> stories) {
        this.stories = stories;
    }

    public List<ArticleBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<ArticleBean> top_stories) {
        this.top_stories = top_stories;
    }

    @Override
    public String toString() {
        return "LatestResponse{" +
                "date=" + date +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
