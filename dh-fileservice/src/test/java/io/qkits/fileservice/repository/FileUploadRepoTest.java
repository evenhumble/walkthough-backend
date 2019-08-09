package io.qkits.fileservice.repository;

import io.qkits.fileservice.BaseDAOTest;
import io.qkits.fileservice.domain.FileUpload;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

@FixMethodOrder(NAME_ASCENDING)
public class FileUploadRepoTest extends BaseDAOTest {
    @Autowired
    FileUploadRepo repo;

    static FileUpload fileUpload = new FileUpload();

    @Test
    @Rollback(false)
    public void a_testSaveFileUpload(){
        fileUpload.setFilePath("test");
        fileUpload.setOriginFileName("originName");
        fileUpload= repo.save(fileUpload);
    }

    @Test
    @Rollback(false)
    public void b_testGetFileById(){
        FileUpload upload= repo.getOne(fileUpload.getId());
        Assert.assertEquals(upload.getFilePath(),"test");
        Assert.assertNotNull(upload);
    }

    @Test
    @Rollback(false)
    public void c_deleteSavedEntity(){
        repo.delete(fileUpload);
    }
}