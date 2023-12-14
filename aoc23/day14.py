import datetime
import numpy as np
def transpose(matrix):
    return list(map(list, zip(*matrix)))

start = datetime.datetime.now()

_input = []

# Read the file and convert each line into a list of characters
# with open("day14.txt", 'r') as f:
with open("day14_test.txt", 'r') as f:
# with open("day14_test2.txt", 'r') as f:
    for line in f.readlines():
        stripped_line = line.strip()
        if stripped_line:
            _input.append(list(stripped_line))

# Transpose the list to switch rows and columns
transposed_input = transpose(_input)

# Optionally, join the characters in each column back into strings
transposed_input = [''.join(column) for column in transposed_input]

print(transposed_input)
total_rows = len(transposed_input[0])
print(total_rows)
total_sum = 0

def calc(total, cube_location, round_rocks):
    sub_sum = 0
    if cube_location > 0:
        cube_location += 1
    for i in range(round_rocks):
        sub_sum += total - cube_location - i
    return sub_sum

for i in range(0, len(transposed_input)):
    print("transposed_input[i]:", transposed_input[i])
    _counter = 0
    cube_location = 0
    for j in range(len(transposed_input[i])):
        current = transposed_input[i][j]
        if current == "O":
            _counter += 1
        elif current == "#":
            if _counter > 0:
                sub_sum = calc(total_rows, cube_location, _counter)
                total_sum += sub_sum
                _counter = 0
            cube_location = j
            continue
        else:
            continue
    if _counter > 0:
        sub_sum = calc(total_rows, cube_location, _counter)
        total_sum += sub_sum

print("total_sum: ", total_sum)
