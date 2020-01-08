package io.hedwig.robot.jenkins.controllers;

import com.offbytwo.jenkins.JenkinsServer;
import io.hedwig.robot.jenkins.models.JenkinsInstance;

import java.util.Map;

/**
 * Created by patrick on 16/3/28.
 */
public abstract class JenkinsBaseController<T> {
    protected JenkinsInstance jenkinsInstance;
    protected JenkinsReplicator replicator;

    public JenkinsBaseController(JenkinsInstance jenkinsInstance) {
        this.jenkinsInstance = jenkinsInstance;
        this.replicator=new JenkinsReplicator(jenkinsInstance);
    }

    public JenkinsServer getJenkins() {
        return jenkinsInstance.getServer();
    }

    /**
     *
     * @param itemName: new item name to create
     * @param referenceItemName: the reference one when create the new item, same as copy
     * @return: new created item
     */
    public abstract T getOrCreate(String itemName,String referenceItemName,String parentNameOrNull);
    /**
     * copy to create a new Jenkins Item, and also try to copy all sub items
     * @param fromName : from item could be none, if so, create a maven item or create based on configuration
     * @param toName: the new Jenkins Item
     * @return : new created Jenkins Item
     */
    public abstract T copy(String fromName,String toName,String parentNameOrNull);

    /**
     * delete the items
     * @param name: delete item name
     * todo think about the recursive deletion flag, right now not quite sure if need to add a flag
     */
    public abstract void delete(String name);

    public abstract Map<String,T> getAll();

    public abstract T getByName(String name);


}
