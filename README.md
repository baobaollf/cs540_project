
# Easy Connect

## Authentication framework for content networks prototype 
## CS540 Spring 2021

## Authors

- Linfeng Li
- Joe Karime
- Claudio Jimenez

## Prototype code slides can be found at [here](https://github.com/baobaollf/cs540_project/blob/main/Easy%20Connect.pdf)
## Project documentation can be found at [here](https://github.com/baobaollf/cs540_project/blob/main/CDN's%20Authorization%20Framework%20and%20Content.pdf)
## Prototype source code can be found at [Github](https://github.com/baobaollf/cs540_project.git)
## Introduction

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

```
./client -> client project folder
./server -> server project folder
./server2 -> server2 project folder
cs540_project.iml
CDN's Authorization Framework and Content.pdf
Easy Connect.pdf
README.md
```

## How to run from command line

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
13. For client program  
    1.username = username  
    2.password = password 

## How to run routine on IntelliJ with Java 1.8

1. Open project server
2. Open project server2
3. Open project client   
3. Run **server** Main class `/server/src/main/Main.java`
4. Run **server2** Main class `/server2/src/main/Main.java`
5. Run **client** Main class `/client/src/main/Main.java`  
For detailed explanation of the functionality, see prototype code slides [prototype code slides](???)

## How to run the unit test

1. Open the project in IntelliJ
2. Right click on the `test` directroy under `${project_name}/src/test` and select `Run Tests in test` like so:
![image](https://user-images.githubusercontent.com/55776365/117084650-bb1b9e80-ad15-11eb-99c4-0d127a2f5467.png)
3. Note that all tests should pass. 
   
