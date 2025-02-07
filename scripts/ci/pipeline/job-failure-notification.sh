#!/usr/bin/env bash

# For any job which fails, this will be called with arguments <pipeline-name> <stage-name> <job-name> <go-pipeline-label>
# An example of how this information might be used to notify you of build failures using hipchat is shown below, but
# of course you can do whatever you want in this script - this is just an example.
#
# curl -X POST "https://api.hipchat.com/v1/rooms/message?format=json&auth_token=<the auth token goes here>" \
#      -d message="Job $3 for stage $2 in pipeline $1 failed ($4)" -d room_id="Tools Engineering" -d from="BuildBot"
error_msg="The following build broken: Pipeline $1 failed ($4).@here"
