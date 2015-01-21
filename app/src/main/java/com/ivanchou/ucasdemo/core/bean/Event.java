package com.ivanchou.ucasdemo.core.bean;

/**
 * Created by ivanchou on 1/21/2015.
 */
public class Event {

    private long id;// 事件 ID

    private String createdAt;// 事件创建时间

    private int jointed;// 用户对该事件的操作

    private User author;// 事件的发布者

    private String startAt;// 事件的开始时间

    private String content;// 事件的详细内容

    private long tags;// 事件的标签属性


}
