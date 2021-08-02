package com.test.meetroom.dto;

public class EventDtoResponse extends EventDtoRequest {

    private Long id;

    private Boolean canEdit;

    public EventDtoResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }
}
