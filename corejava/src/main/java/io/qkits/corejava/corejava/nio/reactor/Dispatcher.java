package io.qkits.corejava.corejava.nio.reactor;

/**
 * @author mazhiqiang
 */
public class Dispatcher {

    private Integer requestId;
    private Integer resourceId;
    private DemultiPlexer demultiPlexer;

    public Dispatcher(Integer requestId, Integer resourceId, DemultiPlexer demultiPlexer) {
        this.requestId = requestId;
        this.resourceId = resourceId;
        this.demultiPlexer = demultiPlexer;
    }

    public RequestHandler createRequestHandler() {
        return new RequestHandler(requestId, resourceId, this);
    }

    public synchronized void freeResource(Integer resourceId) {
        demultiPlexer.returnResource(resourceId);
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public DemultiPlexer getDemultiPlexer() {
        return demultiPlexer;
    }

    public void setDemultiPlexer(DemultiPlexer demultiPlexer) {
        this.demultiPlexer = demultiPlexer;
    }
}
