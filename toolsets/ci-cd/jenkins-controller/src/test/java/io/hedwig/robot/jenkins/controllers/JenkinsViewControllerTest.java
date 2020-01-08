package io.hedwig.robot.jenkins.controllers;

import com.offbytwo.jenkins.model.View;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by patrick on 16/3/29.
 */
public class JenkinsViewControllerTest extends JenkinsBaseTest {

    private JenkinsViewController viewController = new JenkinsViewController(super.jenkinsInstance);

    @Test
    public void testGetOrCreate_get() throws Exception {
        View view = viewController.getOrCreate("test_view", "test_view", null);
        assertThat("check view name", view.getName(), is("test_view"));
    }

    @Test
    public void testGetOrCreate_create() throws Exception {
        View view = viewController.getOrCreate("test_view1", "test_view", null);
        assertThat("check view name", view.getName(), is("test_view1"));
        viewController.delete(view.getName());

    }

    @Test
    public void testCopy_nonSubJob() throws Exception {
        View view = viewController.copy("test_view", "test_view3", null);
        assertThat("check views", view.getName(), is("test_view3"));
        viewController.delete("test_view3");
    }

    @Test
    public void testCopy_SubJobs() throws Exception {
        View view = viewController.copy("test_view", "test_view3", null);
        assertThat("check views", view.getName(), is("test_view3"));
        viewController.delete(view.getName());

        assertThat("check existing jobs", view.getJobs().size(), greaterThan(1));
    }


    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testGetByName() throws Exception {

    }
}
