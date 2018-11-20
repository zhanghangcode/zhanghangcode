package com.min.common.pojo;

import java.io.Serializable;

/**
 * @author Caden
 * @version 1.0.0
 * @ClassName EasyUITreeNode.java
 * @Description 展示商品节点的类
 * @createTime 2018年11月19日 19:21:00
 */

public class EasyUITreeNode implements Serializable {

    private Long id;
    private String text;
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
