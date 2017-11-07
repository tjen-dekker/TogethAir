package com.realdolmen.togethair.domain;

public enum Region {
    AFRICA,
    ASIA,
    CENTRAL_AMERICA,
    EASTERN_EUROPE,
    WESTERN_EUROPE,
    MIDLE_EAST,
    NORTH_AMERICA,
    OCEANIA,
    SOUTH_AMERICA;

    @Override
    public String toString() {
        return super.toString().toLowerCase().replace('_','-');
    }
}
