import pygame
import random

# Initialize Pygame
pygame.init()

# Set up the screen
SCREEN_WIDTH = 400
SCREEN_HEIGHT = 400
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption("2048")

# Colors
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
GRAY = (200, 200, 200)

# Font
font = pygame.font.SysFont(None, 48)

# Grid parameters
GRID_SIZE = 4
TILE_SIZE = SCREEN_WIDTH // GRID_SIZE

# Initialize grid
grid = [[0] * GRID_SIZE for _ in range(GRID_SIZE)]

def draw_grid():
    screen.fill(WHITE)
    for y in range(GRID_SIZE):
        for x in range(GRID_SIZE):
            pygame.draw.rect(screen, GRAY, (x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE), 3)
            value = grid[y][x]
            if value != 0:
                text_surface = font.render(str(value), True, BLACK)
                text_rect = text_surface.get_rect(center=(x * TILE_SIZE + TILE_SIZE // 2, y * TILE_SIZE + TILE_SIZE // 2))
                screen.blit(text_surface, text_rect)

def generate_tile():
    while True:
        x = random.randint(0, GRID_SIZE - 1)
        y = random.randint(0, GRID_SIZE - 1)
        if grid[y][x] == 0:
            grid[y][x] = random.choice([2, 4])
            break

def move_left():
    # Implement move left logic
    pass

def move_right():
    # Implement move right logic
    pass

def move_up():
    # Implement move up logic
    pass

def move_down():
    # Implement move down logic
    pass

# Main game loop
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                move_left()
            elif event.key == pygame.K_RIGHT:
                move_right()
            elif event.key == pygame.K_UP:
                move_up()
            elif event.key == pygame.K_DOWN:
                move_down()

    draw_grid()
    pygame.display.flip()

pygame.quit()
