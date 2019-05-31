package org.liveshow.dto;

import org.liveshow.entity.ClassComment;
import org.liveshow.entity.Tclass;

public class EvaluateVO {
    private Tclass tclass;
    private ClassComment classComment;

    public Tclass getTclass() {
        return tclass;
    }

    public void setTclass(Tclass tclass) {
        this.tclass = tclass;
    }

    public ClassComment getClassComment() {
        return classComment;
    }

    public void setClassComment(ClassComment classComment) {
        this.classComment = classComment;
    }
}
