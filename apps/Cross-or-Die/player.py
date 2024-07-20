from turtle import Turtle

STARTING_POSITION = (0, -280)
MOVE_DISTANCE = 10
FINISH_LINE_Y = 280


class Player(Turtle):

    def __init__(self):
        super().__init__("turtle")
        self.penup()
        self.shape("apps/Cross-Or-Die/img/player.gif")
        self.left(90)
        self.sety(self.ycor()-345)

    def move_straight(self):
        self.setheading(90)
        self.forward(20)

    def move_left(self):
        self.setheading(180)
        if self.xcor() > -380:
            self.forward(20)

    def move_right(self):
        self.setheading(0)
        if self.xcor() < 360:
            self.forward(20)
