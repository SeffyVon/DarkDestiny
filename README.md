DarkDestiny
===========
[![ScreenShot] (https://cloud.githubusercontent.com/assets/3908463/5158073/b11b8072-7328-11e4-9233-5c2fd8efcee3.gif)](http://youtu.be/1po4sz6yDoU.)
[![ScreenShot] (https://cloud.githubusercontent.com/assets/3908463/5158032/de1bacac-7326-11e4-8b84-60f7d24145f7.gif)](http://youtu.be/1po4sz6yDoU.)

##Introduction

This is a game developed in Java within 10 weeks with 3 other teammates: Han Jiang(https://github.com/Han-Jiang), Issac Lam and Hei Yin Wong.

* Game name: Dark Destiny
* Game type: Real Time Strategy game
* Game theme: zombie-themed survival game
* Language: Basic Java, without any additional library or game engine
* Development team: 4 people
* Development time: 10 weeks


##System requirement
1. JRE1.6_65 (**IMPORTANT**) as in other JRE there would be inconsistent functions
2. 1 GB free memory for JVM Mac 10.9 Mavericks 10.9.0 or above


##How to run
1. Change the JRE environment to Java 1.6_65 (**IMPORTANT**)
2. Allocate at least 1 GB memory to your JVM (i.e.: Add "-Xms1024m -Xmx1024m" to your JVM argument)
![Alt text](https://cloud.githubusercontent.com/assets/3908463/5157979/860e1a56-7324-11e4-9160-ebefef13ad25.png "configuration")
3. Click Run in your IDE, and choose the main class as com.darkdensity.main.Main

##Feature
* Control the sprites and run away from the zombie as easily as a single click
![Alt text](https://cloud.githubusercontent.com/assets/3908463/5158176/deb42a54-732b-11e4-975e-8714cc95684a.png "basic scene")

* Scrout the building for *food*, *other survivors* and *wood* to maintain health
![Alt text](https://cloud.githubusercontent.com/assets/3908463/5158143/f4d29650-732a-11e4-9bc2-442173c9b67e.png "scout building")

* Unite with other survivors
![Alt text](https://cloud.githubusercontent.com/assets/3908463/5158151/2a2a8d9e-732b-11e4-85b4-f95c293517cc.png "other survivors")

* Add Barricade (**gained from the scout**) to block the zombie
![Alt text](https://cloud.githubusercontent.com/assets/3908463/5158069/8a7cee88-7328-11e4-89a7-dbbd0a6f5270.png "block the zombie")


##Efficient Path-Finding Algorithm
Based on A* Algorithm, but we have some improvement on the performance:
1. Insead of finding every tiles (in our game, one tile is 16px *16px) to the destination, we first found the crossroads we need to go to the destination.
2. We smoothed the path where we don't neccessarily go to thoses crossing points, see the path on the testing. 
3. When there was some changed on the path, e.g. a barricade is added to the path, the path would be changed accordingly. There is a path manager to manage all the pahts, and it listens to the change of the path all the time.

**Testing Output**

![Alt text](https://cloud.githubusercontent.com/assets/3908463/5158058/4de779c0-7328-11e4-97c2-09c62f68bb3b.png "A* test 1")
![Alt text](https://cloud.githubusercontent.com/assets/3908463/5158059/51829ef2-7328-11e4-8400-88758e6766f1.png "A* test 2")



##Demo Video
Check out full one-minute game playing video on YouTube (http://youtu.be/1po4sz6yDoU) or check out this project on GitHub(https://github.com/SeffyVon/DarkDestiny)!

Thank you!

