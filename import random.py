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
    for row in grid:
        # Merge tiles
        for i in range(GRID_SIZE - 1):
            if row[i] == row[i + 1]:
                row[i] *= 2
                row[i + 1] = 0
        # Move tiles
        row[:] = [tile for tile in row if tile != 0] + [0] * row.count(0)

def move_right():
    for row in grid:
        # Merge tiles
        for i in range(GRID_SIZE - 1, 0, -1):
            if row[i] == row[i - 1]:
                row[i] *= 2
                row[i - 1] = 0
        # Move tiles
        row[:] = [0] * row.count(0) + [tile for tile in row if tile != 0]

def move_up():
    # Transpose grid, move left, transpose back
    global grid
    grid = [list(row) for row in zip(*grid)]
    move_left()
    grid = [list(row) for row in zip(*grid)]

def move_down():
    # Transpose grid, move right, transpose back
    global grid
    grid = [list(row) for row in zip(*grid)]
    move_right()
    grid = [list(row) for row in zip(*grid)]

def is_game_over():
    # Check if grid is full
    for row in grid:
        if 0 in row:
            return False
    # Check if adjacent tiles can be merged
    for y in range(GRID_SIZE):
        for x in range(GRID_SIZE - 1):
            if grid[y][x] == grid[y][x + 1] or grid[x][y] == grid[x + 1][y]:
                return False
    return True

def draw_game_over():
    game_over_text = font.render("Game Over", True, BLACK)
    game_over_rect = game_over_text.get_rect(center=(SCREEN_WIDTH // 2, SCREEN_HEIGHT // 2))
    screen.blit(game_over_text, game_over_rect)

# Generate initial tiles
generate_tile()
generate_tile()

# Main game loop
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                move_left()
                generate_tile()
            elif event.key == pygame.K_RIGHT:
                move_right()
                generate_tile()
            elif event.key == pygame.K_UP:
                move_up()
                generate_tile()
            elif event.key == pygame.K_DOWN:
                move_down()
                generate_tile()

    draw_grid()
    if is_game_over():
        draw_game_over()
    pygame.display.flip()

pygame.quit()
