import pygame
import os


pygame.font.init()
pygame.mixer.init()

WIDTH, HEIGHT = 900, 500
WIN = pygame.display.set_mode((WIDTH, HEIGHT))
ICON = pygame.image.load(os.path.join('apps/Space-Zone/assets', 'icon.ico'))
pygame.display.set_caption("Space Zone")
pygame.display.set_icon(ICON)

WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RIGHT = (255, 0, 0)
LEFT = (255, 255, 0)

BORDER = pygame.Rect(WIDTH//2 - 5, 0, 10, HEIGHT)

HEART_IMAGE = pygame.image.load(os.path.join('apps/Space-Zone/assets', 'heart.png'))
HEART_ICON = pygame.transform.scale(HEART_IMAGE, (40, 40))

WINNER_FONT = pygame.font.SysFont('comicsans', 100)

FPS = 60
VEL = 5
BULLET_VEL = 7
MAX_BULLETS = 3
SPACESHIP_WIDTH, SPACESHIP_HEIGHT = 55, 40

LEFT_HIT = pygame.USEREVENT + 1
RIGHT_HIT = pygame.USEREVENT + 2

LEFT_SPACESHIP_IMAGE = pygame.image.load(os.path.join('apps/Space-Zone/assets', 'spaceship.png'))
LEFT_SPACESHIP = pygame.transform.rotate(pygame.transform.scale(LEFT_SPACESHIP_IMAGE, (SPACESHIP_WIDTH, SPACESHIP_HEIGHT)), 270)

RIGHT_SPACESHIP_IMAGE = pygame.image.load(os.path.join('apps/Space-Zone/assets', 'spaceship.png'))
RIGHT_SPACESHIP = pygame.transform.rotate(pygame.transform.scale(RIGHT_SPACESHIP_IMAGE, (SPACESHIP_WIDTH, SPACESHIP_HEIGHT)), 90)

SPACE = pygame.transform.scale(pygame.image.load(os.path.join('apps/Space-Zone/assets', 'space.png')), (WIDTH, HEIGHT))


def draw_window(right, left, right_bullets, left_bullets, right_health, left_health):
    WIN.blit(SPACE, (0, 0))
    pygame.draw.rect(WIN, BLACK, BORDER)

    for i in range(right_health):
        WIN.blit(HEART_ICON, (WIDTH - (i + 1) * HEART_ICON.get_width() - 10, 10))
    for i in range(left_health):
        WIN.blit(HEART_ICON, (10 + i * HEART_ICON.get_width(), 10))

    WIN.blit(LEFT_SPACESHIP, (left.x, left.y))
    WIN.blit(RIGHT_SPACESHIP, (right.x, right.y))

    for bullet in right_bullets:
        pygame.draw.rect(WIN, RIGHT, bullet)

    for bullet in left_bullets:
        pygame.draw.rect(WIN, LEFT, bullet)

    pygame.display.update()


def LEFT_handle_movement(keys_pressed, left):
    if keys_pressed[pygame.K_a] and left.x - VEL > 0:  # LEFT
        left.x -= VEL
    if keys_pressed[pygame.K_d] and left.x + VEL + left.width < BORDER.x:  # RIGHT
        left.x += VEL
    if keys_pressed[pygame.K_w] and left.y - VEL > 0:  # UP
        left.y -= VEL
    if keys_pressed[pygame.K_s] and left.y + VEL + left.height < HEIGHT - 15:  # DOWN
        left.y += VEL


def right_handle_movement(keys_pressed, right):
    if keys_pressed[pygame.K_LEFT] and right.x - VEL > BORDER.x + BORDER.width:  # LEFT
        right.x -= VEL
    if keys_pressed[pygame.K_RIGHT] and right.x + VEL + right.width < WIDTH:  # RIGHT
        right.x += VEL
    if keys_pressed[pygame.K_UP] and right.y - VEL > 0:  # UP
        right.y -= VEL
    if keys_pressed[pygame.K_DOWN] and right.y + VEL + right.height < HEIGHT - 15:  # DOWN
        right.y += VEL


def handle_bullets(left_bullets, right_bullets, left, right):
    for bullet in left_bullets:
        bullet.x += BULLET_VEL
        if right.colliderect(bullet):
            pygame.event.post(pygame.event.Event(RIGHT_HIT))
            left_bullets.remove(bullet)
        elif bullet.x > WIDTH:
            left_bullets.remove(bullet)

    for bullet in right_bullets:
        bullet.x -= BULLET_VEL
        if left.colliderect(bullet):
            pygame.event.post(pygame.event.Event(LEFT_HIT))
            right_bullets.remove(bullet)
        elif bullet.x < 0:
            right_bullets.remove(bullet)


def draw_winner(text):
    draw_text = WINNER_FONT.render(text, 1, WHITE)
    WIN.blit(draw_text, (WIDTH/2 - draw_text.get_width() / 2, HEIGHT/2 - draw_text.get_height()/2))
    pygame.display.update()
    pygame.time.delay(1000)


def main():
    right = pygame.Rect(700, 300, SPACESHIP_WIDTH, SPACESHIP_HEIGHT)
    left = pygame.Rect(100, 300, SPACESHIP_WIDTH, SPACESHIP_HEIGHT)

    right_bullets = []
    left_bullets = []

    right_health = 4
    left_health = 4

    clock = pygame.time.Clock()
    run = True
    while run:
        clock.tick(FPS)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                run = False
                pygame.quit()

            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LCTRL and len(left_bullets) < MAX_BULLETS:
                    bullet = pygame.Rect(left.x + left.width, left.y + left.height//2 - 2, 10, 5)
                    left_bullets.append(bullet)
                    # BULLET_FIRE_SOUND.play()

                if event.key == pygame.K_RCTRL and len(right_bullets) < MAX_BULLETS:
                    bullet = pygame.Rect(right.x, right.y + right.height//2 - 2, 10, 5)
                    right_bullets.append(bullet)
                    # BULLET_FIRE_SOUND.play()

            if event.type == RIGHT_HIT:
                right_health -= 1
                # BULLET_HIT_SOUND.play()

            if event.type == LEFT_HIT:
                left_health -= 1
                # BULLET_HIT_SOUND.play()

        winner_text = ""
        if right_health <= 0:
            winner_text = "Left Wins!"

        if left_health <= 0:
            winner_text = "Right Wins!"

        if winner_text != "":
            draw_winner(winner_text)
            break

        keys_pressed = pygame.key.get_pressed()
        LEFT_handle_movement(keys_pressed, left)
        right_handle_movement(keys_pressed, right)

        handle_bullets(left_bullets, right_bullets, left, right)

        draw_window(right, left, right_bullets, left_bullets, right_health, left_health)

    main()


if __name__ == "__main__":
    main()
