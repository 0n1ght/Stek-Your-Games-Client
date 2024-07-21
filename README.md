# Stek: Your Multi-Application
During your learning career or free time, You've made a lot of small projects, but they are not enough to be included separately in portfolio?
Or maybe you just want to tidy up your applications?<br>
Now you can keep all of them in one place, put into your GitHub Account inside this one client, or just use whenever you want using simple GUI.
## Features
- Has built-in my 10 open-source games by default (written in Java and Python), which you can modify and play.
- Code written in one file- ```client.py```, embedded with easy comments to let you change whatever you want
- Easy and Quick App deployment process

## Screen-shots
![home](https://github.com/user-attachments/assets/0b39acd6-38c5-4f6d-a62d-c8f3fa3e68ed)
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
1. <b>Download the Project</b>
<br>
<em>You can do this using GIT, or as a .jar file and unpack it.
<br>
Make sure that you have python downloaded. If not, you can do this [here](https://www.python.org/downloads/)
<br>
Open the Stek project in your code editor</em>

2. <b>Add your own apps and Make changes</b>
<br>
<em>Put your project into "apps" folder. In its main folders, include ```client_config.json``` files<br>
This file let client know, how to display the app in GUI, and run.<br>
File's content should look like this:
<pre>
{
"run_variable" : ["java", "-jar"],
"main_path" : "apps/Space-War/Main.py",
"title" : "Space War",
"icon_path" : "resources/img/app-icons/space_war_icon.png"
}
</pre>
Client will display an image from <icon_path> and <title> of your App. Tu run it, it is going to use following commands in OS's CLI: <run_variable...> <main_path><br>
If you would like to make any design changes, you can find the entire client code in
```client.py```</em>
3. <b>Install requirements and Run</b>
<br>
<em>Using CLI go into project folder and write ```pip install -r requirements.txt```.<br>
Now, You can Run the app using ```python client.py```</em>
<br>
<br>
And have Fun, Including the client code with your own apps inside "apps" folder, you can treat its code like your own.<br>You're welcome!