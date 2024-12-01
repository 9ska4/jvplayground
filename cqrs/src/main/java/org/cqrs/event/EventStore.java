package org.cqrs.event;

import java.util.ArrayList;
import java.util.List;

public interface EventStore {

    public void saveEvent(Event event);

    public List<Event> getEvents();
}
