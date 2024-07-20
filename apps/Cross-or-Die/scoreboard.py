from turtle import Turtle
from time import time

RESULT_FONT = ("Courier", 60, "bold")
PROMPT_FONT = ("Courier", 16, "normal")
ALIGN = "center"


class Scoreboard(Turtle):

    def __init__(self):
        super().__init__()
        self.hideturtle()
        self.pencolor("#FF0000")
        self.start_time = time()

    def show_score(self, won=True):
        self.goto(0, 20)
        if won:
            self.write(f"You won!\nTime: {time()-self.start_time:.2f}", align=ALIGN, font=RESULT_FONT)
        else:
            self.write(f"GAME OVER!", align=ALIGN, font=RESULT_FONT)

        self.goto(0, 10)
        self.write("to quit Click somewhere xd", align=ALIGN, font=PROMPT_FONT)
