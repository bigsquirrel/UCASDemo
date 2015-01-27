package com.ivanchou.ucasdemo.core.model;

/**
 * Created by ivanchou on 1/21/2015.
 */
public class EventModel {

    public long eventId;// 事件 ID

    public String createdAt;// 事件创建时间

    public int jointed;// 用户对该事件的操作

    public MemberModel author;// 事件的发布者

    public String startAt;// 事件的开始时间

    public String title;// 事件的主题

    public String content;// 事件的详细内容

    public long tags;// 事件的标签属性

    public EventModel() {

    }


}
