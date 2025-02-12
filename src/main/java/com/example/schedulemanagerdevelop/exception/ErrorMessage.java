package com.example.schedulemanagerdevelop.exception;

public enum ErrorMessage {
    SESSION_NOT_FOUND("세션을 찾을 수 없습니다. 다시 로그인해주세요."),
    USER_NOT_FOUND("해당 사용자가 존재하지 않습니다."),
    SCHEDULE_NOT_FOUND("해당 일정이 존재하지 않습니다."),
    TITLE_OR_CONTENT_REQUIRED("제목과 내용은 필수 입력 사항입니다."),
    UNAUTHORIZED_ACTION("해당 일정에 대한 수정 또는 삭제 권한이 없습니다."),
    EMAIL_NOT_FOUND("해당 이메일을 사용하는 사용자를 찾을 수 없습니다."),
    INCORRECT_PASSWORD("비밀번호가 일치하지 않습니다."),
    COMMENT_CONTENTS_REQUIRED("댓글 내용은 필수 입력 사항입니다."),
    NO_COMMENTS_FOUND("등록된 댓글이 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
