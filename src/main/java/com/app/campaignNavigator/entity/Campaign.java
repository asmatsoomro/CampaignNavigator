package com.app.campaignNavigator.entity;

import java.util.Arrays;
import java.util.Objects;

public class Campaign {

    private String name;
    private String[] segments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSegments() {
        return segments;
    }

    public void setSegments(String[] segments) {
        this.segments = segments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign)) return false;
        Campaign campaign = (Campaign) o;
        return Objects.equals(getName(), campaign.getName()) &&
                Arrays.equals(getSegments(), campaign.getSegments());
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(getName());
        result = 31 * result + Arrays.hashCode(getSegments());
        return result;
    }

}
