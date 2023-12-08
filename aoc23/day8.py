from collections import defaultdict
import datetime

start = datetime.datetime.now()

_input = []

with open("day8.txt", 'r') as f:
# with open("day8_test.txt", 'r') as f:
    for line in f.readlines():
        stripped_line = line.strip()
        if stripped_line:
            _input.append(stripped_line)

lr_instructions = _input[0]
_dict = defaultdict(list)

for map in range(1, len(_input)):
    begin = _input[map].split(" = (")[0]
    left = _input[map].split(" = (")[1].split(",")[0]
    right = _input[map].split(" = ")[1].split(", ")[1][:-1]
    _dict[begin] = [left, right]

current_location = "AAA"
steps = 0
index = 0
length = len(lr_instructions)

while current_location != "ZZZ":
    instruction = lr_instructions[index % length]  # Loop back to start using modulus
    if instruction == 'L':
        current_location = _dict[current_location][0]
    else:
        current_location = _dict[current_location][1]

    steps += 1
    index += 1

print("Steps:", steps)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)