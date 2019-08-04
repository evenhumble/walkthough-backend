package io.qkits.walkthough.netty.adapter;

public interface DataIncidentHandler<T> {
    void handle(DataIncident<T> incident);
}
