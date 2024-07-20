import tkinter as tk
from PIL import Image, ImageTk
import subprocess
from os import walk, path
from json import load

APP_IMGS = []
next_col = 0
next_row = 0


# Methods to run Apps
def run_app(run_variable, main_path):
    # subprocess.run([run_variable, main_path], shell=True)
    run_variable.append(main_path)
    subprocess.run(run_variable, shell=True)


# Resize image function
def resize_image(image_path, size):
    with Image.open(image_path) as img:
        img = img.resize(size)
        return ImageTk.PhotoImage(img)


# Add app to the interface
def add_app(run_variable, main_path, title, icon_path):
    global next_col, next_row
    app_img = resize_image(icon_path, (100, 100))
    APP_IMGS.append(app_img)

    # Create button with resized image
    tk.Button(root,
              image=APP_IMGS[-1],
              command=lambda: run_app(run_variable, main_path),
              borderwidth=4,
              relief='raised',
              activebackground='#433D8B',
              bg="#2E236C").grid(row=next_row, column=next_col, padx=10, pady=10)

    # Create label for the app title
    tk.Label(root, text=title, font=('Courier', 12, "bold"), bg="#17153B", fg="#C8ACD6").grid(row=next_row + 1, column=next_col)

    # Update column and row indices for next button
    next_col += 1
    if next_col == 6:
        next_col = 0
        next_row += 2


# Process JSON files to add apps
def process_json_files(base_folder):
    for root, dirs, files in walk(base_folder):
        if 'client_config.json' in files:
            json_path = path.join(root, 'client_config.json')
            with open(json_path, 'r') as file:
                data = load(file)
                run_variable = data.get('run_variable')
                main_path = data.get('main_path')
                title = data.get('title')
                icon_path = data.get('icon_path')

                if all([run_variable, main_path, title, icon_path]):
                    add_app(run_variable, main_path, title, icon_path)
                else:
                    print(f"Missing data in {json_path}")


# Main window
root = tk.Tk()
root.title("Stek - Your Multi-Application")
root.iconphoto(False, tk.PhotoImage(file="resources/img/icon.png"))
root.geometry("780x500")
root.configure(bg="#17153B")
root.resizable(False, False)


# Run the application
process_json_files("apps")
root.mainloop()
