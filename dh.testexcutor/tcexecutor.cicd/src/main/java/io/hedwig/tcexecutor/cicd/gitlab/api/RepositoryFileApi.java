package io.hedwig.tcexecutor.cicd.gitlab.api;

import retrofit2.Call;
import retrofit2.http.*;


import java.util.List;

public interface RepositoryFileApi {

    @GET("projects/{id}/repository/files/{filePath}")
    Call<String> getRepoFile(@Path("id") Integer id,
                                   @Path("filePath") String filePath,
                                   @Query("ref")String ref);

    @GET("projects/{id}/repository/files/{filePath}/raw")
    Call<String> getRawRepoFile(@Path("id") Integer id,
                                   @Path("filePath") String filePath,
                                   @Query("ref")String ref);

    @POST("projects/{id}/repository/files/{filePath}/raw")
    Call<String> createRawRepoFile(@Path("id") Integer id,
                                         @Path("filePath") String filePath,
                                         @Query("ref")String ref);

//    file_path (required) - Url encoded full path to new file. Ex. lib%2Fclass%2Erb
//    branch (required) - Name of the branch
//    start_branch (optional) - Name of the branch to start the new commit from
//    encoding (optional) - Change encoding to 'base64'. Default is text.
//            author_email (optional) - Specify the commit author's email address
//    author_name (optional) - Specify the commit author's name
//    content (required) - File content
//    commit_message (required) - Commit message
//    Update existing file in reposit

//    file_path (required) - Url encoded full path to new file. Ex. lib%2Fclass%2Erb
//    branch (required) - Name of the branch
//    start_branch (optional) - Name of the branch to start the new commit from
//    encoding (optional) - Change encoding to 'base64'. Default is text.
//            author_email (optional) - Specify the commit author's email address
//    author_name (optional) - Specify the commit author's name
//    content (required) - New file content
//    commit_message (required) - Commit message
//    last_commit_id (optional) - Last known file commit id

    @DELETE("projects/{id}/repository/files/{filePath}")
    Call<String> deleteRepoFile(@Path("id") Integer id,
                                   @Path("filePath") String filePath,
                                   @Query("ref")String ref);
//    file_path (required) - Url encoded full path to new file. Ex. lib%2Fclass%2Erb
//    branch (required) - Name of the branch
//    start_branch (optional) - Name of the branch to start the new commit from
//    author_email (optional) - Specify the commit author's email address
//    author_name (optional) - Specify the commit author's name
//    commit_message (required) - Commit message
//    last_commit_id (optional) - Last known file commit id
}
