package com.jlc.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: 密保问题实体类
 * </p>
 * <p>
 * Description: T_USER_SECURITY(密保问题)
 * </p>
 * <p>
 * Company: jyd
 * </p>
 * 
 * @author shenhe
 * @date 2013-11-25
 */
public class MemberSecurityDO implements Serializable{

    private static final long serialVersionUID = 1L;

    private MemberDO memberDO;// 用户(参见表t_user)
    private String quizA;// 问题一
    private String answerA;// 问题一答案
    private String quizB;// 问题二
    private String answerB;// 问题二答案
    private String quizC;// 问题三
    private String answerC;// 问题三答案
    private Date createTime;// 创建时间

    public MemberDO getMemberDO() {
        return memberDO;
    }

    public void setMemberDO(MemberDO memberDO) {
        this.memberDO = memberDO;
    }

    public String getQuizA() {
        return quizA;
    }

    public void setQuizA(String quizA) {
        this.quizA = quizA;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getQuizB() {
        return quizB;
    }

    public void setQuizB(String quizB) {
        this.quizB = quizB;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getQuizC() {
        return quizC;
    }

    public void setQuizC(String quizC) {
        this.quizC = quizC;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
