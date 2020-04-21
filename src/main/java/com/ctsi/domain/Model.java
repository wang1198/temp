package com.ctsi.domain;

import java.util.List;

public class Model {
    private String value;

    private String label;

    private List<Model> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Model> getChildren() {
        return children;
    }

    public void setChildren(List<Model> children) {
        this.children = children;
    }
}
