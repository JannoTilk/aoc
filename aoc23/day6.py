import datetime
import re

_input = []

with open("day6.txt", 'r') as f:
# with open("day6_test.txt", 'r') as f:
    for line in f.readlines():
        stripped_line = line.strip()
        if stripped_line:
            _input.append(stripped_line)

splitted = []
for line in _input:
    splitted.append(re.split('\s+', line.split(":")[1]))

splitted[0].pop(0)
splitted[1].pop(0)

times = list(map(int, splitted[0]))
distances = list(map(int, splitted[1]))

_multiplied = 1
for i in range(len(times)):
    result = 0
    for j in range(times[i] - 1):
        time_to_move = times[i] - j
        speed = j
        if time_to_move * speed > distances[i]:
            result += 1
    _multiplied = _multiplied * result

print(_multiplied)