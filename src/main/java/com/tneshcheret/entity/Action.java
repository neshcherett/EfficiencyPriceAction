package com.tneshcheret.entity;

public class Action {
    private RequestAction requestAction;
    private Integer effectPromo;

    public void setRequestAction(RequestAction requestAction) {
        this.requestAction = requestAction;
    }

    public RequestAction getRequestAction() {
        return requestAction;
    }

    public Integer getEffectPromo() {
        return effectPromo;
    }

    @Override
    public String toString() {
        return "Action{" +
                "requestAction=" + requestAction +
                ", effectPromo=" + effectPromo +
                '}';
    }
}
