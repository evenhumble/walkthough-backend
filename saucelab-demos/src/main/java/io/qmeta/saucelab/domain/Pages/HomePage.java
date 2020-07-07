package io.qmeta.saucelab.domain.Pages;

import java.util.HashMap;

/**
 * Created by neil on 5/27/17.
 */
public interface HomePage {
    public void visitPage(HashMap<String, String> parameterMap);

    public void goToLogin() throws Exception;
}
