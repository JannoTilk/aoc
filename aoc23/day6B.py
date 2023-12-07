import datetime
import re

start = datetime.datetime.now()
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

times = int(''.join([str(x) for x in times]))
distances = int(''.join([str(x) for x in distances]))

result = 0

for j in range(times - 1):
    time_to_move = times - j
    speed = j
    if time_to_move * speed > distances:
        result += 1

print(result)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)