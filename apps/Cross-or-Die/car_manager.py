from turtle import Turtle
from random import choice, randint

STARTING_MOVE_DISTANCE = 5
MOVE_INCREMENT = 10
ICON_PATH = "apps/Cross-Or-Die/img/cars/car{}.gif"
LEFT_START_POSITIONS_Y = (50, 155, 255)
RIGHT_START_POSITIONS_Y = (-250, -150)
START_POSITIONS_X = [-300, -100, 100, 300]


class CarFactory:

    def __init__(self):
        self.reset_free_positions()

    def create_car(self, site):
        car = Car(site)
        if not len(self.free_positions[site]) == 0:
            car_position = choice(self.free_positions[site])
            car.goto(car_position)
            self.free_positions[site].remove(car_position)
        return car

    def reset_free_positions(self):
        self.free_positions = {0: [], 1: []}
        for site in range(2):
            for ypos in (LEFT_START_POSITIONS_Y if site == 0 else RIGHT_START_POSITIONS_Y):
                for xpos in START_POSITIONS_X:
                    self.free_positions[site].append((xpos, ypos))


class Car(Turtle):

    def __init__(self, site):
        self.site = site
        super().__init__("square")
        self.penup()
        self.goto(-500, choice(LEFT_START_POSITIONS_Y))
        self.shape(ICON_PATH.format(randint(6, 10)))
        if self.site == 1:
            self.goto(500, choice(RIGHT_START_POSITIONS_Y))
            self.shape(ICON_PATH.format(randint(1, 5)))
            self.left(180)

    def drive(self):
        self.forward(randint(8, 18))
        if (self.xcor() >= 500 and self.site == 0) or (self.xcor() <= -500 and self.site == 1):
            match self.site:
                case 0: self.goto(-500, choice(LEFT_START_POSITIONS_Y))
                case _: self.goto(500, choice(RIGHT_START_POSITIONS_Y))
