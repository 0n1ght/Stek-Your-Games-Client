# Stek: Games Client
A bunch of simple Open-Source games written in Python and Java, with simple GUI Client.<br>
You can make changes in their source code, and edit whatever you want.
## Features
- 10 built-in Open-Source games which you can modify, and play
- Ability to add your own games, or apps. (and add to your portfolio as one big project, if your apps separated are too small)
## Screen-shots
**Home**
<br>
<br>
![home](https://github.com/user-attachments/assets/0b39acd6-38c5-4f6d-a62d-c8f3fa3e68ed)
<br>
<br>
**Included projects:**
<br>
<br>
![monopoly](https://github.com/user-attachments/assets/196d84f0-472b-4806-9c00-00fa93811e41)
<br>
<br>
![ss2 2](https://github.com/user-attachments/assets/c2e51cd4-b562-4b12-aa6a-370bb85efc79)
<br>
<br>
![ss2](https://github.com/user-attachments/assets/b225c80b-0d8a-4cd8-accd-a9abf53bb7dd)
<br>
<br>
![ss3](https://github.com/user-attachments/assets/87f28305-781b-461b-8f2c-6df8f1b4f6a7)

## Quick-start

<h3>1) <b>Download and Open</b><br></h3>
You can do this using GIT, or as a .jar file and unpack it.<br>
Make sure that you have python downloaded. If not, you can do this [here](https://www.python.org/downloads/)<br>
Open the Stek project in your code editor
<br>
<h3>2) <b>[optional] Add your projects</b></h3>
All apps that appears in client are inside "apps" folder. It this folder you will find 8 my own games. You can just delete them if you want, then they will disappear from client.
Now, you can put there your own within folders. Inside your project's folder, include file named ```client_config.json```<br>
This file will let client know, how to display the app in GUI, and how to run it when you click on the icon.<br>
File's content should look like this:
<pre>
{
"run_variable" : ["java", "-jar"],
"main_path" : "apps/Space-War/Main.py",
"title" : "Space War",
"icon_path" : "resources/img/app-icons/space_war_icon.png"
}
</pre>
Client will <title> and the icon (as a png image) from <icon_path> above. Tu run it, it is going to use following commands in OS's CLI: <run_variable...> <main_path><br>
For Example, the file above will display space_war_icon.png icon from resources/img/app-icons folder, and after click on this icon, it will run "java -jar apps/Space-War/Main.py" in Terminal/CMD
If you would like to make any design changes, you can find the entire client code in ```client.py```</em>
<br>
<h3>3) <b>Run</b></h3>
<em>Using CLI go into project folder and write ```pip install -r requirements.txt```.<br>
Now, run the app using ```python client.py``` after opening project's folder</em><br>
You can start modify and play games which you will find in "apps" folder.
  
If you want to run some default added app, make sure you have its dependencies and language downloaded.
<br>
<br>
This client is just a simple GUI Hub for your apps, you can put them into "apps" folder and include on your own GitHub.
You can treat its code like your own. You're welcome.
