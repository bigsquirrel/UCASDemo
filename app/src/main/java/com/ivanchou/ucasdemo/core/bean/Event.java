package com.ivanchou.ucasdemo.core.bean;

/**
 * Created by ivanchou on 1/21/2015.
 */
public class Event {

    private long eventId;// 事件 ID

    private String createdAt;// 事件创建时间

    private int jointed;// 用户对该事件的操作

    private User author;// 事件的发布者

    private String startAt;// 事件的开始时间

    private String content;// 事件的详细内容

    private long tags;// 事件的标签属性

    public Event(long eventId, String createdAt, int jointed, User author, String startAt, String content, long tags) {
        this.eventId = eventId;
        this.createdAt = createdAt;
        this.jointed = jointed;
        this.author = author;
        this.startAt = startAt;
        this.content = content;
        this.tags = tags;
    }

    /*-------------------------------------------- get and set ----------------------------------------*/

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getJointed() {
        return jointed;
    }

    public void setJointed(int jointed) {
        this.jointed = jointed;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTags() {
        return tags;
    }

    public void setTags(long tags) {
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
                ", content='" + content + '\'' +
                ", tags=" + tags +
                '}';
    }
}
