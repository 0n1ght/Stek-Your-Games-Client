# Stek: Your Apps Client
During learning programming or in the free time, You've made a lot of small projects, but they are not enough to be included separately into portfolio?

Or maybe you just want to tidy up your own apps?

Now, you can keep all of them in one place, put into your GitHub within this simple client, or just use whenever you want with simple GUI manager.
## Features
- Has built-in my 10 open-source games by default (written in Java and Python), which you can modify, play, or just delete xd.
- Code written in one file- ```client.py```, embedded with easy comments to let you change whatever you want (colors, sizes, shapes)
- Easy and Quick App deployment process
## Screen-shots
**Home**
<br>
![home](https://github.com/user-attachments/assets/0b39acd6-38c5-4f6d-a62d-c8f3fa3e68ed)
<br>
Included projects:
**Monopoly**
<br>
![monopoly](https://github.com/user-attachments/assets/196d84f0-472b-4806-9c00-00fa93811e41)
<br>
**Space-Zone**
<br>
![ss2 2](https://github.com/user-attachments/assets/c2e51cd4-b562-4b12-aa6a-370bb85efc79)
<br>
**Cross-or-Die**
<br>
![ss2](https://github.com/user-attachments/assets/b225c80b-0d8a-4cd8-accd-a9abf53bb7dd)
<br>
**Retro Snake**
<br>
![ss3](https://github.com/user-attachments/assets/87f28305-781b-461b-8f2c-6df8f1b4f6a7)

## Quick-start

<h3><b>Download and Open</b><br></h3>
You can do this using GIT, or as a .jar file and unpack it.<br>
Make sure that you have python downloaded. If not, you can do this [here](https://www.python.org/downloads/)<br>
Open the Stek project in your code editor
<br>
<h3><b>Add your projects</b></h3>
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
<h3><b>Run</b></h3>
<em>Using CLI go into project folder and write ```pip install -r requirements.txt```.<br>
Now, You can Run the app using ```python client.py```</em>
  
If you want to run some default added app, make sure you have its dependencies and language downloaded.
<br>
<br>
This client is just a simple GUI Hub for your apps, you can put them into "apps" folder and include on your own GitHub.
You can treat its code like your own. You're welcome.
