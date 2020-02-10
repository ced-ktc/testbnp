package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Trips {
    private String stationStart;
    private String stationEnd;
    private int startedJourneyAt;
    private int costInCents;
    private int zoneFrom;
    private int zoneTo;

    public Trips() {
    }

    public Trips(String stationStart, String stationEnd, int startedJourneyAt, int costInCents, int zoneFrom, int zoneTo) {
        this.stationStart = stationStart;
        this.stationEnd = stationEnd;
        this.startedJourneyAt = startedJourneyAt;
        this.costInCents = costInCents;
        this.zoneFrom = zoneFrom;
        this.zoneTo = zoneTo;
    }

    public String getStationStart() {
        return stationStart;
    }

    public void setStationStart(String stationStart) {
        this.stationStart = stationStart;
    }

    public String getStationEnd() {
        return stationEnd;
    }

    public void setStationEnd(String stationEnd) {
        this.stationEnd = stationEnd;
    }

    public int getStartedJourneyAt() {
        return startedJourneyAt;
    }

    public void setStartedJourneyAt(int startedJourneyAt) {
        this.startedJourneyAt = startedJourneyAt;
    }

    public int getCostInCents() {
        return costInCents;
    }

    public void setCostInCents(int costInCents) {
        this.costInCents = costInCents;
    }

    public int getZoneFrom() {
        return zoneFrom;
    }

    public void setZoneFrom(int zoneFrom) {
        this.zoneFrom = zoneFrom;
    }

    public int getZoneTo() {
        return zoneTo;
    }

    public void setZoneTo(int zoneTo) {
        this.zoneTo = zoneTo;
    }
}
