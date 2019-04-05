package org.liveshow.entity;

public class Tclass {
    private Integer id;

    private String className;

    private String classIntro;

    private String createrId;

    private Boolean teaching;

    public Tclass(Integer id, String className, String classIntro, String createrId, Boolean teaching) {
        this.id = id;
        this.className = className;
        this.classIntro = classIntro;
        this.createrId = createrId;
        this.teaching = teaching;
    }

    public Tclass() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassIntro() {
        return classIntro;
    }

    public void setClassIntro(String classIntro) {
        this.classIntro = classIntro == null ? null : classIntro.trim();
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId == null ? null : createrId.trim();
    }

    public Boolean getTeaching() {
        return teaching;
    }

    public void setTeaching(Boolean teaching) {
        this.teaching = teaching;
    }
}