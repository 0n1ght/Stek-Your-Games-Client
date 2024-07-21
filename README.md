# Stek: Your Multi-Application
During your learning career or free time, You've made a lot of small projects, but they are not enough to be included separately in portfolio?
Or maybe you just want to tidy up your applications?<br>
Now you can keep all of them in one place, put into your GitHub Account inside this one client, or just use whenever you want using simple GUI.
## Features
- Has built-in my 10 open-source games by default (written in Java and Python), which you can modify and play.
- Code written in one file- ```client.py```, embedded with easy comments to let you change whatever you want
- Easy and Quick App deployment process

## Sample Photos
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

1. **Download the Project**
You can do this using GIT, or as a .jar file and unpack it.  
Make sure that you have Python downloaded. If not, you can do this [here](https://www.python.org/downloads/) (To run Java games like Chess or Monopoly you should also have [Java](https://www.oracle.com/java/technologies/downloads/) installed).  
Open the Stek project in your code editor.

2. **Add your own apps and Make changes**
*Put your project into the "apps" folder. In its main folders, include `client_config.json` files.*  
This file lets the client know how to display the app in the GUI, and run.  
File's content should look like this:

<pre>
{
"run_variable" : ["java", "-jar"],
"main_path" : "apps/Space-War/Main.py",
"title" : "Space War",
"icon_path" : "resources/img/app-icons/space_war_icon.png"
}
</pre>

Client will display an image from <icon_path> and <title> of your App. To run it, it is going to use the following commands in the OS's CLI: <run_variable...> <main_path>.  
If you would like to make any design changes, you can find the entire client code in `client.py`.

3. **Install requirements and Run**
Using CLI, go into the project folder and write `pip install -r requirements.txt`.  
Now, you can run the app using `python client.py`.  

And have fun! Including the client code with your own apps inside the "apps" folder, you can treat its code like your own.  
You're welcome!
