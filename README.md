# Stek: Your Multi-Application
During your learning career or free time, You've made a lot of small projects, but they are not enough to be included separately in portfolio?
Or maybe you just want to tidy up your applications?<br>
Now you can keep all of them in one place, put into your GitHub Account inside this one client, or just use whenever you want using simple GUI.
## Features
- Has built-in my 10 open-source games by default (written in Java and Python), which you can modify and play.
- Code written in one file- ```client.py```, embedded with easy comments to let you change whatever you want
- Easy and Quick App deployment process

## Photos
*Home*
![gameView](https://github.com/KarolKasperek/Monopoly/assets/105314335/15bb8b7c-bd62-4b42-a558-c4d227e9100a)
*Monopoly*
*Space Zone*
*Cross or Cie*
*Python*

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