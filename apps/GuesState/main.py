from tkinter import PhotoImage
from turtle import Screen, shape, Turtle
import pandas

screen = Screen()
screen.title("GuesState")
icon = PhotoImage(file="apps/GuesState/icon.png")
screen.getcanvas().winfo_toplevel().iconphoto(True, icon)
image = "apps/GuesState/blank_states_img.gif"
screen.addshape(image)
shape(image)

data = pandas.read_csv("apps/GuesState/50_states.csv")
all_states = data.state.to_list()
guessed_states = []

while len(guessed_states) < 50:
    answer_state = screen.textinput(title=f"{len(guessed_states)}/50",
                                    prompt="State's name:").title()
    if answer_state == "Exit":
        missing_states = []
        for state in all_states:
            if state not in guessed_states:
                missing_states.append(state)
        new_data = pandas.DataFrame(missing_states)
        new_data.to_csv("apps/GuesState/states_to_learn.csv")
        break
    if answer_state in all_states:
        guessed_states.append(answer_state)
        t = Turtle()
        t.hideturtle()
        t.penup()
        state_data = data[data.state == answer_state]
        t.goto(int(state_data.x), int(state_data.y))
        t.write(answer_state)
