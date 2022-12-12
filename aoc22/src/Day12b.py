import datetime
import sys

a = datetime.datetime.now()
input = []
score = 0
with open("day12.txt", 'r') as f:
    for line in f.readlines():
        input.append(line.replace("\n", ""))

maze = []

start = []
end = -1, -1

for i in range(0, len(input)):
    maze.append([])
    for j in range(0, len(input[i])):
        if (ord(input[i][j]) == 69):
            end = i, j
        if (ord(input[i][j]) == 97):
            start.append((i, j))
        maze[i].append(ord(input[i][j]))

e, t = end
maze[e][t] = ord('z')
minimum = sys.maxsize

def make_step(k, trig):
    counter = 0
    for i in range(len(m)):
        if (trig == 1):
            break
        for j in range(len(m[i])):
            if m[i][j] == k:
                if i > 0 and m[i - 1][j] == 0 and maze[i - 1][j] - maze[i][j] <= 1:
                    m[i - 1][j] = k + 1
                    counter += 1
                if j > 0 and m[i][j - 1] == 0 and maze[i][j - 1] - maze[i][j] <= 1:
                    m[i][j - 1] = k + 1
                    counter += 1
                if i < len(maze) - 1 and m[i + 1][j] == 0 and maze[i + 1][j] - maze[i][j] <= 1:
                    m[i + 1][j] = k + 1
                    counter += 1
                if j < len(m[i]) - 1 and m[i][j + 1] == 0 and maze[i][j + 1] - maze[i][j] <= 1:
                    m[i][j + 1] = k + 1
                    counter += 1
    if counter == 0:
        global trigger
        trigger = 1

for p in range(0, len(start)):
    m = []
    for i in range(0, len(input)):
        m.append([])
        for j in range(0, len(input[i])):
            m[-1].append(0)

    i, j = start[p]
    m[i][j] = 1
    trigger = 0

    k = 0
    while m[end[0]][end[1]] == 0:
        k += 1
        make_step(k, trigger)
        if (trigger == 1):
            break
    if (trigger == 1):
        continue

    i, j = end
    k = m[i][j]
    the_path = []
    while k > 1:
        if i > 0 and m[i - 1][j] == k - 1:
            i, j = i - 1, j
            the_path.append((i, j))
            k -= 1
        elif j > 0 and m[i][j - 1] == k - 1:
            i, j = i, j - 1
            the_path.append((i, j))
            k -= 1
        elif i < len(m) - 1 and m[i + 1][j] == k - 1:
            i, j = i + 1, j
            the_path.append((i, j))
            k -= 1
        elif j < len(m[i]) - 1 and m[i][j + 1] == k - 1:
            i, j = i, j + 1
            the_path.append((i, j))
            k -= 1

    minimum = min(minimum, len(the_path))

print("\nresult:", end=' ')
print(minimum)

b = datetime.datetime.now()
print("\ntime: ")
print(b - a)
