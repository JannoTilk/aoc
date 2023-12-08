from collections import defaultdict
import datetime
from math import lcm


start = datetime.datetime.now()

_input = []

with open("day8.txt", 'r') as f:
# with open("day8_test2.txt", 'r') as f:
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

length = len(lr_instructions)

current_nodes = []

for key in _dict:
    if key[2] == "A":
        current_nodes.append(key)


def do_all_end_with_z(nodes):
    for node in nodes:
        if node[2] != "Z":
            return False
    return True

steps_for_all = []

#USE LCM BETWEEN ALL STEPS FOR EACH NODE ENDING WITH A
for node in current_nodes:
    steps = 0
    index = 0
    current_location = node
    while current_location[2] != "Z":
        instruction = lr_instructions[index % length]  # Loop back to start using modulus
        if instruction == 'L':
            current_location = _dict[current_location][0]
        else:
            current_location = _dict[current_location][1]
        steps += 1
        index += 1
    steps_for_all.append(steps)

least_common_multiple = lcm(*steps_for_all)

print("Steps:", least_common_multiple)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)