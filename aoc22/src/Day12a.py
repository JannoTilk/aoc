import datetime

a = datetime.datetime.now()
input = []
score = 0
with open("day12.txt", 'r') as f:
    for line in f.readlines():
        input.append(line.replace("\n", ""))

maze = []

start, end = -1, -1

for i in range(0, len(input)):
    maze.append([])
    for j in range(0, len(input[i])):
        maze[i].append(ord(input[i][j]))
        if (ord(input[i][j]) == 83):
            start = i, j
        if (ord(input[i][j]) == 69):
            end = i, j

m = []
for i in range(0, len(input)):
    m.append([])
    for j in range(0, len(input[i])):
        m[-1].append(0)
i, j = start
m[i][j] = 1
maze[i][j] = ord('a')

e, t = end
maze[e][t] = ord('z')


def make_step(k):
    for i in range(len(m)):
        for j in range(len(m[i])):
            if m[i][j] == k:
                if i > 0 and m[i - 1][j] == 0 and maze[i - 1][j] - maze[i][j] <= 1:
                    m[i - 1][j] = k + 1
                if j > 0 and m[i][j - 1] == 0 and maze[i][j - 1] - maze[i][j] <= 1:
                    m[i][j - 1] = k + 1
                if i < len(maze) - 1 and m[i + 1][j] == 0 and maze[i + 1][j] - maze[i][j] <= 1:
                    m[i + 1][j] = k + 1
                if j < len(m[i]) - 1 and m[i][j + 1] == 0 and maze[i][j + 1] - maze[i][j] <= 1:
                    m[i][j + 1] = k + 1


k = 0
while m[end[0]][end[1]] == 0:
    k += 1
    make_step(k)

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


print(len(the_path))

b = datetime.datetime.now()
print("\ntime: ")
print(b - a)
