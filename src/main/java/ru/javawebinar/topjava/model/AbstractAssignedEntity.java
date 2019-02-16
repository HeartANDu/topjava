package ru.javawebinar.topjava.model;

abstract public class AbstractAssignedEntity extends AbstractBaseEntity {

    protected int userId;

    protected AbstractAssignedEntity(Integer id, int userId) {
        super(id);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAssigned() {
        return userId > 0;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, assigned to '%s')", getClass().getName(), id, userId);
    }
}
