
# Easy Connect
- - -
## Authentication framework for content networks prototype 
## CS540 Spring 2021
- - -
## Authors
- - -
- Linfeng Li
- Joe Karime
- Claudio Jimenez

## Introduction
- - -
The following code implements a high fidelity prototype of our
CDN authorization framework idea  
**"Easy connect"**  
The prototype is divided in 3 separates 
java projects;
1. server 
2. server2 
3. client

The code simulates the connection in a CDN cluster between two 
authentication servers (AS) and a client. In our simulation as in real world 
conditions both servers need to be running before the client tries to
connect.

The prototype implements the following:
- A client connection to an authentication server.
- The sharing of clients security object between the servers after
  client login.
- The servers serving content to the client.
- Client switching content servers.
- Client log out and removal of clients security object in 
  authentication servers.
  


## Project folder content
- - -
```
./client -> client project folder
./server -> server project folder
./server2 -> server2 project folder
cs540_project.iml
project_documentation 
README.md
```

## How to run from command line
- - -
1. Clone github repository
`git clone https://github.com/baobaollf/cs540_project.git`
2. Open cs540_project folder
`cd cs540_project`   
3. Open server/src/main
   `cd server/src/main`
4. Compile
`javac *.java`
5. Go up one folder to server/src
`cd ..`
6. Run code
`java main.Main`
7. Open new terminal
8. Go back to cs540_project folder 
9. Open server2/src/main
`cd server2/src/main`
10. Repeat step 4 through 8
11. Open client/src/main
`cd client/src/main`
12. Repeat step 4 through 8

## How to run routine on IntelliJ with Java 12
- - -
1. Open project server
2. Open project server2
3. Open project client   
3. Run **server** Main class `/server/src/main/Main.java`
4. Run **server2** Main class `/server2/src/main/Main.java`
5. Run **client** Main class `/client/src/main/Main.java`  
For detailed explanation of the functionality, see prototype code slides below
- - - 
## Prototype code slides can be found at [Joe's slides](???)
## Project documentation can be found at [Google Docs](https://docs.google.com/document/d/1WNN0oufbqCAFiISOD6adxGTJDJFcAy25TC9lBR0bhPI/edit?usp=sharing)
## Prototype source code can be found at [Github](https://github.com/baobaollf/cs540_project.git)


- - -
