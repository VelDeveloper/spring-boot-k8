package com.sample.kubernetes.model;

import org.springframework.hateoas.Link;

import javax.xml.bind.annotation.XmlAttribute;

public class SuperLink extends Link {
    @XmlAttribute
    private String type;

    @XmlAttribute
    private String method;

    @XmlAttribute
    private String template;

    @XmlAttribute
    private String describedBy;


    public SuperLink(Link link, String type, String method) {
        super(link.getHref(), link.getRel());
        this.type = type;
        this.method = method;
    }

    public SuperLink(Link link, String type, String method,String template,String describedBy) {
        super(link.getHref(), link.getRel());
        this.type = type;
        this.method = method;
        this.template = link.getHref()+template;
        this.describedBy = link.getHref()+describedBy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public void setTemplate(String template) {
        this.template = this.getHref()+template;
    }

    public String getDescribedBy() {
        return describedBy;
    }

    public void setDescribedBy(String describedBy) {
        this.describedBy = this.getHref()+describedBy;
    }
}
