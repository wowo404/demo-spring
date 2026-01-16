package org.liu.mockito.support;

import java.time.ZoneId;

public class TimezoneContext {

    private static final ThreadLocal<ZoneId> currentZone = new ThreadLocal<>();

    public static void setZoneId(ZoneId zoneId) {
        currentZone.set(zoneId);
    }

    public static ZoneId getZoneId() {
        return currentZone.get();
    }

    public static void clear() {
        currentZone.remove();
    }

}
