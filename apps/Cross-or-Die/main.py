from time import sleep
from tkinter import PhotoImage
from turtle import Screen
from player import Player
from car_manager import CarFactory
from scoreboard import Scoreboard


screen = Screen()
screen.title("Cross Or Die")
icon = PhotoImage(file="apps/Cross-Or-Die/img/icon.png")
screen.getcanvas().winfo_toplevel().iconphoto(True, icon)
screen.setup(width=800, height=798)
screen.tracer(0)

screen.bgpic("apps/Cross-Or-Die/img/map.gif")
screen.addshape("apps/Cross-Or-Die/img/player.gif")
for i in range(1, 11):
    screen.addshape(f"apps/Cross-Or-Die/img/cars/car{i}.gif")

player = Player()
screen.listen()
screen.onkeypress(player.move_straight, "Up")
screen.onkeypress(player.move_left, "Left")
screen.onkeypress(player.move_right, "Right")

CAR_PAIRS_NUM = 3
carFactory = CarFactory()
CAR_PAIRS = tuple((carFactory.create_car(0), carFactory.create_car(1)) for _ in range(CAR_PAIRS_NUM))


def is_distance_ok(car, site):
    for cars_pair in CAR_PAIRS:
        if (car.distance(cars_pair[site]) <= 200
                and (car.xcor() < cars_pair[site].xcor() if car.site == 0 else car.xcor() > cars_pair[site].xcor())):
            return False
    return True


def check_collision():
    for car_l, car_r in CAR_PAIRS:
        if min(player.distance(car_l), player.distance(car_r)) < 50:
            return True
    return False


game_is_on = True
scoreboard = Scoreboard()
while game_is_on:
    sleep(0.1)
    for pair in CAR_PAIRS:
        for side in range(2):
            if is_distance_ok(pair[side], side):
                pair[side].drive()
    screen.update()

    if player.ycor() > 340:
        game_is_on = False
        scoreboard.show_score()
    elif check_collision():
        game_is_on = False
        scoreboard.show_score(won=False)


screen.exitonclick()
