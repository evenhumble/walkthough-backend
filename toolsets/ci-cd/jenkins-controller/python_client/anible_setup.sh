#!/usr/bin/env bash
# MAC OS
brew install ansible

# pip 
pip install ansible


ssh-agent bash
 
ssh-add ~/.ssh/id_rsa
#永久生效,添加代码到.bashrc
if [ -f ~/.agent.env  ]; then
    . ~/.agent.env >/dev/null
    if ! kill -s 0 $SSH_AGENT_PID >/dev/null 2>&1; then
        echo "Stale agent file found. Spawning new agent..."
        eval `ssh-agent |tee ~/.agent.env` ssh-add
    fielse
    echo "Starting ssh-agent..."
    eval `ssh-agent |tee ~/.agent.env`
    ssh-add
fi

ssh-keygen
ssh-copy-id -i ~/.ssh/id_rsa.pub root@192.168.0.4
