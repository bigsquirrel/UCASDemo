package com.ivanchou.ucasdemo.core.bean;

/**
 * Created by ivanchou on 1/21/2015.
 */
public class Event {

    public long eventId;// 事件 ID

    public String createdAt;// 事件创建时间

    public int jointed;// 用户对该事件的操作

    public User author;// 事件的发布者

    public String startAt;// 事件的开始时间

    public String title;// 事件的主题

    public String content;// 事件的详细内容

    public long tags;// 事件的标签属性

    public Event() {

    }

    public Event(long eventId, String createdAt, int jointed, User author, String startAt, String title, String content, long tags) {
        this.eventId = eventId;
        this.createdAt = createdAt;
        this.jointed = jointed;
        this.author = author;
        this.startAt = startAt;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", createdAt='" + createdAt + '\'' +
                ", jointed=" + jointed +
                ", author=" + author +
                ", startAt='" + startAt + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                '}';
    }
}
