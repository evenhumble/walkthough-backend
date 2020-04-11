package io.qkits.walkthough.netty.adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import io.qkits.walkthough.netty.common.DataEntry;
import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.longpolling.api.DataEvent;
import io.qkits.walkthough.netty.longpolling.api.DataEventHandler;
import io.qkits.walkthough.netty.longpolling.api.DataEventType;
import io.qkits.walkthough.netty.shortpolling.api.DataOperatorContext;
import io.qkits.walkthough.netty.shortpolling.api.DataOperatorFactory;
import lombok.Data;
import lombok.NonNull;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Data
public class DataIncidentEntry<T> implements DataEventHandler {

  private final static Log logger = LogFactory.get(DataIncidentEntry.class);
  private final static String BAK_DIR = System.getProperty("huskar.bak.dir");
  private final Set<DataIncidentHandler<T>> handlers = new HashSet<>();
  private final Map<String, DataEntry> caches = new ConcurrentHashMap<>();
  private final DataAdapterContext<T> dataContext;
  private final String service;
  private final String cluster;
  private final File file;
  private final File tmpFile;

  public DataIncidentEntry(@NonNull DataAdapterContext<T> dataContext, @NonNull String service,
                           @NonNull String cluster) throws NettyWalkthroughException {
    this.dataContext = dataContext;
    this.service = service;
    this.cluster = cluster;
    String
        bakDir =
        String.format("soa-backup/%s/%s", dataContext.getDataType().name().toLowerCase(), service);
    if (BAK_DIR != null && !BAK_DIR.isEmpty()) {
      bakDir = new File(BAK_DIR).getAbsolutePath() + "/" + bakDir;
    }
    File path = new File(bakDir).getAbsoluteFile();
    path.mkdirs();
    this.file = new File(String.format("%s/%s.json", path.getAbsolutePath(), cluster));
    this.tmpFile =
        new File(String.format("%s/%s.%d-%d", path.getAbsolutePath(), cluster,
                               Thread.currentThread().getId(), System.currentTimeMillis()));
    load();
  }

  private void load() throws NettyWalkthroughException {
    Map<String, DataEntry> tmp = null;
    try {
      tmp =
          DataOperatorFactory
              .newInstance(new DataOperatorContext(dataContext.getDataType(), service))
              .getMap(cluster);
    } catch (NettyWalkthroughException e1) {
      logger.warn("Fail to load from HUSKAR!", e1);
      try {
        tmp = new ObjectMapper().readValue(file, new TypeReference<Map<String, DataEntry>>() {
        });
      } catch (IOException e2) {
        logger.warn("Fail to load from FILE!", e2);
        throw new NettyWalkthroughException("Fail to load!");
      }
    }

    DataEvent event = new DataEvent();
    event.setType(DataEventType.ALL);
    event.setService(service);
    event.setCluster(cluster);
    event.setData(tmp);
    handle(event);
  }

  public synchronized void register(DataIncidentHandler<T> handler) {
    if (handler != null && !handlers.contains(handler)) {
      Set<DataIncidentHandler<T>> notifyHandler = new HashSet<>();
      notifyHandler.add(handler);
      caches.forEach((key, value) -> {
        notify(DataIncidentType.UPDATE, key, value, notifyHandler);
      });
      handlers.add(handler);
    }
  }

  @Override
  public synchronized void handle(DataEvent event) {
    String service = event.getService();
    if (!service.equals(this.service)) {
      throw new IllegalArgumentException("Invalid service(" + service + ")!");
    }

    DataEventType type = event.getType();
    if (type == null) {
      throw new NullPointerException("Invalid type!");
    }

    Map<String, DataEntry> data = event.getData();
    if (data == null) {
      throw new NullPointerException("Invalid data!");
    }

    final Handler handler;
    switch (type) {
      case ALL:
        handler = this::handleAll;
        break;
      case UPDATE:
        handler = this::handleUpdate;
        break;
      case DELETE:
        handler = this::handleDelete;
        break;
      default:
        return;
    }

    handler.handle(data);
    syncFile();
  }

  private void syncFile() {
    if (!tmpFile.exists()) {
      try {
        tmpFile.createNewFile();
      } catch (IOException e) {
        logger.warn("Fail to create file(" + tmpFile.getPath() + ")!", e);
        return;
      }
    }

    try (FileOutputStream os = new FileOutputStream(tmpFile)) {
      os.write(new ObjectMapper().writeValueAsBytes(caches));
    } catch (IOException e) {
      logger.warn("Fail to write file(" + tmpFile.getPath() + ")!", e);
    }

    tmpFile.renameTo(file);
  }

  @SuppressWarnings("serial")
  private void handleAll(Map<String, DataEntry> data) {
    handleUpdate(data);
    handleDelete(new HashMap<String, DataEntry>() {
      {
        new HashSet<String>(caches.keySet()) {
          {
            removeAll(data.keySet());
          }
        }.forEach((key) -> {
          put(key, null);
        });
      }
    });
  }

  private void handleUpdate(Map<String, DataEntry> data) {
    data.entrySet().forEach((entry) -> {
      String key = entry.getKey();
      DataEntry value = entry.getValue();
      DataEntry currentValue = caches.get(key);
      if (currentValue != null) {
        if (value.getValue() == null) {
          value.setValue(currentValue.getValue());
        }

        if (value.getRuntime() == null) {
          value.setRuntime(currentValue.getRuntime());
        }

        if (currentValue.equals(value)) {
          return;
        }
      }
      caches.put(key, value);
      if (value.getValue() != null) {
        notify(DataIncidentType.UPDATE, key, value, handlers);
      }
    });
  }

  private void handleDelete(Map<String, DataEntry> data) {
    data.forEach((key, value) -> {
      if (caches.containsKey(key)) {
        caches.remove(key);
        notify(DataIncidentType.DELETE, key, null, handlers);
      }
    });
  }

  private void notify(DataIncidentType type, String key, DataEntry value,
                      Set<DataIncidentHandler<T>> handlers) {
    try {
      DataIncident<T> dataIncident = new DataIncident<>();
      dataIncident.setType(type);
      dataIncident.setService(service);
      dataIncident.setCluster(cluster);
      dataIncident.setKey(key);
      dataIncident.setValue(dataContext.getDataParser().parse(value));
      handlers.forEach((handler) -> {
        handler.handle(dataIncident);
      });
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @FunctionalInterface
  private static interface Handler {

    void handle(Map<String, DataEntry> data);
  }
}
