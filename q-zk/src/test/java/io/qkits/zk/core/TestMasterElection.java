/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.qkits.zk.core;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.qkits.zk.core.client.ZkMaster;

public class TestZkMasterElection extends BaseTestCase {    
    private static final Logger LOG = LoggerFactory.getLogger(TestZkMasterElection.class);
    
    @Test(timeout=50000)
    public void electZkMaster() 
    throws Exception {
        ZkMaster m = new ZkMaster("localhost:" + port);
        m.();
        
        while(!m.isConnected()){
            Thread.sleep(500);
        }
        
        m.bootstrap();
        m.runForZkMaster();
        
        while(m.getState() == ZkMasterStates.RUNNING){
            Thread.sleep(100);
        }
        
        Assert.assertTrue("ZkMaster not elected.", m.getState() == ZkMasterStates.ELECTED);
        m.close();
    }
    
    @Test(timeout=50000)
    public void reElectZkMaster() 
    throws Exception {
        ZkMaster m = new ZkMaster("localhost:" + port);
        m.startZK();
        
        while(!m.isConnected()){
            Thread.sleep(500);
        }
        
        m.bootstrap();
        m.runForZkMaster();
        
        while(m.getState() == ZkMasterStates.RUNNING){
            Thread.sleep(100);
        }
        
        m.close();
        
        ZkMaster bm = new ZkMaster("localhost:" + port);
        bm.startZK();
        
        while(!bm.isConnected()){
            Thread.sleep(500);
        }
        
        bm.bootstrap();
        bm.runForZkMaster();
        
        while(bm.getState() == ZkMasterStates.RUNNING){
            Thread.sleep(100);
        }
          
        Assert.assertTrue("ZkMaster not elected.", bm.getState() == ZkMasterStates.ELECTED);
        bm.close();
    }
    
    @Test(timeout=50000)
    public void electSingleZkMaster() 
    throws Exception {
        ZkMaster m = new ZkMaster("localhost:" + port);
        ZkMaster bm = new ZkMaster("localhost:" + port);
        m.startZK();
        bm.startZK();
        
        while(!m.isConnected() || !bm.isConnected()){
            Thread.sleep(500);
        }
        
        m.bootstrap();
        bm.bootstrap();
        
        m.runForZkMaster();
        bm.runForZkMaster();
        
        while((m.getState() == ZkMasterStates.RUNNING) ||
                (bm.getState() == ZkMasterStates.RUNNING)){
            Thread.sleep(100);
        }
        
        boolean singleZkMaster = (((m.getState() == ZkMasterStates.ELECTED) 
                        && (bm.getState() != ZkMasterStates.ELECTED)) 
                || ((m.getState() != ZkMasterStates.ELECTED) 
                        && (bm.getState() == ZkMasterStates.ELECTED)));
        Assert.assertTrue("ZkMaster not elected.", singleZkMaster);
        m.close();
    }
    
    @Test(timeout=50000)
    public void testZkMasterExists() 
    throws Exception {
        ZkMaster m = new ZkMaster("localhost:" + port);

        m.startZK();
        
        while(!m.isConnected()){
            Thread.sleep(500);
        }
        
        m.bootstrap();
        m.ZkMasterExists();
        
        int attempts = 10;
        boolean elected = true;
        while((m.getState() == ZkMasterStates.RUNNING)){
            Thread.sleep(200);
            if(attempts-- == 0) {
                LOG.info("Breaking...");
                elected = false;
                break;
            }
        }
        
        Assert.assertTrue("ZkMaster not elected.", elected);
        m.close();
    }
    
}
