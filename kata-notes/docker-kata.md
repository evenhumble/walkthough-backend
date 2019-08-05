# Docker Kata

## Scenario1: Using Docker

- Search Redis
```sh
docker search redis
```
- Run Redis in background
```sh
docker run -d redis:latest 
```

- Finding Running Container
```sh
docker inspect redis
docker logs <container_id>
```
- Accessing Redis and find out which port redis used
```sh
docker run -d redis:latest -p 6739:6738 -name redisWithPort
docker port redisWithPort 6379 
```
- Remove docker container
```sh
docker ps -aq | xargs docker stop
docker ps -aq | xargs docker rm 
```
- Persisting Data 
```sh
docker run -d --name redisMapped -v $PWD/data:/data/redis
```
- Running Container in the front

```sh
docker run ubuntu ps
docker run ubuntu bash 
```

## Scenario 2: deploy static website
Learn how to create a Docker image with a static html website behind
nginx.

- Create a Dockfile

```sh
FROM nginx:alphine
COPY . /usr/share/nginx/html
```

- Build Docker image

```sh
docker build -t docker-static-html:v1 .
```

- Run and check it
```sh
docker run -d -p 80:8080 web-image:v1 
curl docker
```

## Build Docker image

Docker images are built based on Dockfile,A Dockfile defines all the steps required to building docker image. Once the docker image is built, this image could be run in any platform supported docker

- Base Image
- Command for build image
- Expose Port
- Default Statup command

```sh
FROM nginx:1.11-alpine  #base image
COPY . /usr/share/nginx/html # commands
EXPOSE 80 # expose port
CMD ["nginx", "-g", "daemon off;"] # default command
```

## Scenario 3: Running Nodejs App
- Dockerfile
```sh
FROM node:10-alpine
RUN mkdir -p /src/app
WORKDIR /src/app
COPY package.json /src/app/package.json
RUN npm install
COPY . /src/app
EXPOSE 3000
CMD [ "npm", "start" ]
```
- Run the image with port 
```sh
docker run -d --name my-production-running-app -e NODE_ENV=production -p 3000:3000 my-nodejs-app
```