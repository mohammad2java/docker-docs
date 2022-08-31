##Docker docs
---------------
	 docker is tool help create and run docker image or containers.
	 container vs VM
	 ------------------
	 VMs is result of virtualization of hardware with the help of hypervisor.
	 VMs share hardware and run with full fledge of os.(heavyweight and slow)
	 containers is result of virtualization of os (host os)
	 containers share host-os and run with mini appl needed libs.(lightweight and faster)

		layers of app with VM and with containers
		------------------------------------------
      1) hardware -> [hosts-os+hypervisor]->[VM1+VM2..etc]->VM-OS->apps..
      2) hardware -> HOST_OS ->[containers+,,etc]->apps..
      
            
   ##How to install doc
   ---------------------
   
	   see documentation page...for windows download docker desktop.	   
	   1) verifying if install or not
	   ----------------------------
	    docker --version
	    docker version 20.10.12, build e91ed57
       docker-compose --version
       docker-compose version 1.29.2, build 5becea4c
      
       container is virtual-os runs independently, so this is having few following challenges
       
       
       1) persisting permanent data is container stopped --there is volume options
       2) accessing service which is running inside container --there is port publishing & exposing 
       3) managing multiple container with single command.  ---there is docker-compose for this
       
       
       
       
      Basic process in containerization of apps
      ---------------------------------------
      apps+os -> docker-image->container(running of image)
      there is docker-registery where we store docker image & pull docker image
      docker-registery can be public(dockerhub) also can be private at organization level
      
 
 ##Working with Docker Containers
 ===============================
 
      1) Listing/searching for an image from registery or repository(public or private) default one is private dockerhub  
      docker search java8
      
      2) pulling or downloading image from docker-registory
      docker image pull [private-registry-url-with-port]/<name>[:tag]
       standard format of name is: <user-name>/<repo-name>:tag  TAG is optional default value is latest
       
       if you are searching with private registry  or Pull from a different registry
       -------------------------------------------------------
       docker pull myregistry.local:5000/testing/test-image
       myregistry.local:5000 --private registry url
       
        3) Listing image from local system(how many pulled or build)
        -----------------------------------------------------------
      docker image ls      
      C:\Users\mohd.amir>docker image ls
		REPOSITORY   TAG       IMAGE ID       CREATED        SIZE
		webapp2      latest    cf33c3c1364a   3 hours ago    633MB
		webapp       latest    cb4447bba9eb   3 hours ago    633MB
       
        
       4) running container (if you have image)
        docker container run  -p p1s:p1d -v v1s:v1d -d/-it imageName
        example:
        docker container run -p 8080:8080 -v D:/data/data_share:/dev2 -d webapp
        
        -d  -> detached running or background running
        -it -> iteractive window of container 
       
      5) listing running containers
      -------------------------------
      docker container ls       -- for running one
      docker container ls -a    --for all 
      
      6)Stopping a container
      docker container stop <imageName or containerID>
      
      7) Removing a container
      docker container rm <imageName or containerID>
      
      8)Removing all stopped containers
        docker container prune 
      
      
 ##Working with Docker Images
 ==========================
      
      
      There are 2 ways to build image in docker system. 1) using docker container 2) using Dockerfile
     1) Creating an image from a container
     ---------------------------------------
     docker container commit <containerID> <imageName>
     
     2) Building an image using a Dockerfile
     there are few Dockerfile commands it is non-case-sensitive but always written in capital.
     1)FROM  --this will run on host machine to pulled base image for ur image
     2)RUN  --this is run inside container 
     3)ADD/COPY --this will run on host & container to copy and paste.
     4)CMD -- this will run on container only during startup . it arguments can be override with run command
     5)ENTRYPOINT -this will run on container only during startup . it arguments cant be override with run command
     6)WORKDIR  --set working directory for executing commands
     7)EXPOSE  -- to expose/listen port inside vm
     8)ENV  --env variable creation
     9)VOLUME --mountingE
     
     deep drive abovee
     -------------------
     
     1) FROM: 
     This must be the first instruction of any Dockerfile, and sets the base image
	  for subsequent instructions. By default, the latest tag is assumed to be the following
		FROM <image
		Alternatively, consider the following tag
		FROM <images>:<tag
		If you want to use private or third-party images, then you have to includ
		them as follows
		[registry_hostname[:port]/][user_name/](repository_name:version_tag
		The following is an example using the preceding syntax
		FROM registry-host:5000/cookbook/apache2
       
      2)RUNN
      -----
      this is used to set command for container
      many task (installing package,creating dir,deleting it..so many )
      we can write mutliple run statement in single dockerfile 
      RUN mkdir fema
     
      CMD:
       The CMD instruction provides a default executable while starting a container.
		If the CMD instruction does not have any executable (parameter 2), then it will
		provide arguments to ENTRYPOINT
		CMD ["executable", "param1",...,"paramN" 
		CMD ["param1", ... , "paramN"
		CMD <command> <param1> ... <pamamN
		Only one CMD instruction is allowed in Dockerfile. If more than one i
       specified, then only the last one will be honored.
     
     ENTRYPOINT: This helps us configure the container as an executable. Similar t
		CMD, there can be a maximum of one instruction for ENTRYPOINT; if more the
		one is specified, then only the last one will be honored
		ENTRYPOINT ["executable", "param1",...,"paramN" 
		ENTRYPOINT <command> <param1> ... <pamamN
	  Once the parameters are defined with the ENTRYPOINT instruction, the
	  cannot be overwritten at runtime. However, ENTRYPOINT can be used a
	  CMD, if we want to use different parameters for ENTRYPOINT     
    
    
    Dockerfile example:
    ------------------
       FROM alpine:3.6
		LABEL maintainer="Jeeva S. Chelladhurai <sjeeva@gmail.com>"
		WORKDIR /home
		RUN mkdir data
		EXPOSE 80
		ENTRYPOINT ["/usr/sbin/httpd", "-D", "FOREGROUND"]
		
		
		Setting up a private index/registry
		--------------------------------------
		docker container run -d -p 5000:5000 --name registry registry:2
		    
       pushing image : 
       docker image push localhost:5000/apache2
       
       
       
       
 Docker Compose
 -------------------
  
	 it is tool to manage multiple container/services in one command(start stop with hug configuration)
	 as we knew it , docker container run command contains many configuration that is very tedious work to write 
	 all those configuration and again and again. we can its type of replace of run command for multiple container as well.
	 In the beginning was Fig. Fig was a powerful tool, created by a company called Orard
	 It was a Python tool that sat on top of Doer, and let you define entire
	 multi-container apps in a single YAML file. You could then deploy and manage the lifecycle of the app with the
	 fig command-line tool.
	
	In fact, it was so good, that Doer, Inc. acquired Orard and re-branded Fig as Doer Compose. e command-
	line tool was renamed from fig to docker-compose, and continues to be an external tool that gets bolted on
	top of the Docker Engine. Even though it’s never been fully integrated into the Doer Engine, it’s always been
	popular and widely used.
       
       default filename for docker-compose is docker-compose.yml
     
    
    lets understand with example:

    
	version: "3.8"
	services:
	  webapp:
	    image: webapp2:latest
	    command: java -jar /home/service-web-0.0.1-SNAPSHOT.jar
	    volumes:
	     - D:/data/data_share:/dev2
	    ports:
	      - "8080:8080"
      
      
      D:/data/data_share --> host  localtion
      /dev2 --container location
      
      "8080:8080"  --<host>:<container>
      
      
      command is kind of cmd or entrypoint
     webapp: --service name (any name as you want to attached with image)
     
     to run all service - docker-compose up // docker-compose -f filename up
     to down all service - docker-compose down // docker-compose -f filename down
     
     more details
     ------------
     D:\data\data_share>docker-compose -h
	Define and run multi-container applications with Docker.
	
	Usage:
	  docker-compose [-f <arg>...] [--profile <name>...] [options] [--] [COMMAND] [ARGS...]
	  docker-compose -h|--help
	
	Options:
	  -f, --file FILE             Specify an alternate compose file
	                              (default: docker-compose.yml)
	  -p, --project-name NAME     Specify an alternate project name
	                              (default: directory name)
	  --profile NAME              Specify a profile to enable
	  -c, --context NAME          Specify a context name
	  --verbose                   Show more output
	  --log-level LEVEL           Set log level (DEBUG, INFO, WARNING, ERROR, CRITICAL)
	  --ansi (never|always|auto)  Control when to print ANSI control characters
	  --no-ansi                   Do not print ANSI control characters (DEPRECATED)
	  -v, --version               Print version and exit
	  -H, --host HOST             Daemon socket to connect to


	Commands:
	  build              Build or rebuild services
	  config             Validate and view the Compose file
	  create             Create services
	  down               Stop and remove resources
	  events             Receive real time events from containers
	  exec               Execute a command in a running container
	  help               Get help on a command
	  images             List images
	  kill               Kill containers
	  logs               View output from containers
	  pause              Pause services
	  port               Print the public port for a port binding
	  ps                 List containers
	  pull               Pull service images
	  push               Push service images
	  restart            Restart services
	  rm                 Remove stopped containers
	  run                Run a one-off command
	  scale              Set number of containers for a service
	  start              Start services
	  stop               Stop services
	  top                Display the running processes
	  unpause            Unpause services
	  up                 Create and start containers
	  version            Show version information and quit
     
    
          
      
      
   
     