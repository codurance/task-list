package com.codurance.training.tasks;

public class Project {
    private final String name;

    public Project(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(name, project.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
