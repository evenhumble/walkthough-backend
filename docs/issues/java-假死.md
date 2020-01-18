# JAVA Stop the world

- jstack -F <process_id>
- sudo jstat -gcutil  20683 2000
- jmap -dump:format=b,file=heap.bin <pid>
- netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}' 
