import datetime

start = datetime.datetime.now()

_input = []

# with open("day11.txt", 'r') as f:
with open("day11_test.txt", 'r') as f:
    for line in f.readlines():
        stripped_line = line.strip()
        # print(stripped_line)
        if "#" in stripped_line:
            _input.append(stripped_line)
        else:
            _input.append(stripped_line)
            _input.append(stripped_line)


# print(_input)
new_input = []
expanded_columns = []
for i in range(len(_input[0])):
    value_exists = any(row[i] == "#" for row in _input)
    if not value_exists:
        expanded_columns.append(i)

for i in range(len(_input)):
    new_row = ""
    string_list = list(_input[i])
    for j, pos in enumerate(expanded_columns):
        adjusted_position = pos + j
        string_list.insert(adjusted_position, ".")
    new_input.append("".join(string_list))

print(_input)
print(expanded_columns)
print(new_input)

galaxy_coordinates = []

for i in range(len(new_input)):
    for j in range(len(new_input[i])):
        if new_input[i][j] == "#":
            galaxy_coordinates.append([i, j+1])
            print("i: ", i)

print(galaxy_coordinates)
_sum = 0
_counter = 0
for i in range(len(galaxy_coordinates)):
    for j in range(i if len(galaxy_coordinates) == i else i + 1, len(galaxy_coordinates)):
        pair_sum = abs(galaxy_coordinates[i][0] - galaxy_coordinates[j][0]) + abs(galaxy_coordinates[i][1] - galaxy_coordinates[j][1])
        print(pair_sum)
        _sum += pair_sum
        _counter += 1
print(_sum)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)