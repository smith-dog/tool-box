package com.hunter.auto.mail.client.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @version 1.0.0
 * @author: hunter
 * @date: 2018/6/18 11:37
 * @description:
 */
@Data
@Builder
public class Subject {

    private String subjectMonth;

    private String subjectWeekOfMonth;

    private String subjectHead;

    private String subjectContent;

    private String subjectTail;

    @Override
    public String toString() {
        return subjectMonth + "月" + "第" + subjectWeekOfMonth + "周" + subjectHead + subjectContent + subjectTail;
    }
}
