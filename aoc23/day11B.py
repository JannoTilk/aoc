import datetime

start = datetime.datetime.now()

_input = []

galaxy_expansion = 999999

with open("day11.txt", 'r') as f:
# with open("day11_test.txt", 'r') as f:
    for line in f.readlines():
        stripped_line = line.strip()
        _input.append(stripped_line)

new_input = []
expanded_columns = []
expanded_rows = []
for i in range(len(_input[0])):
    value_exists = any(row[i] == "#" for row in _input)
    if not value_exists:
        expanded_columns.append(i)

for i, row in enumerate(_input):
    if not any(item == "#" for item in row):
        expanded_rows.append(i)

galaxy_coordinates = []

for i, row in enumerate(_input):
    for j in range(len(row)):
        if row[j] == "#":
            galaxy_coordinates.append([i, j])

_sum = 0
_counter = 0
for i in range(len(galaxy_coordinates)):
    for j in range(i if len(galaxy_coordinates) == i else i + 1, len(galaxy_coordinates)):
        pair_sum = 0
        for expanded_row in expanded_rows:
            if galaxy_coordinates[i][0] < expanded_row < galaxy_coordinates[j][0] or galaxy_coordinates[i][0] > expanded_row > galaxy_coordinates[j][0]:
                pair_sum += galaxy_expansion
        for expanded_column in expanded_columns:
            if galaxy_coordinates[i][1] < expanded_column < galaxy_coordinates[j][1] or galaxy_coordinates[i][1] > expanded_column > galaxy_coordinates[j][1]:
                pair_sum += galaxy_expansion
        pair_sum += abs(galaxy_coordinates[i][0] - galaxy_coordinates[j][0]) + abs(galaxy_coordinates[i][1] - galaxy_coordinates[j][1])
        print("galaxy_coordinates[i]", galaxy_coordinates[i])
        print("galaxy_coordinates[j]", galaxy_coordinates[j])
        print("pair_sum: ", pair_sum)
        _sum += pair_sum
        _counter += 1
print(_sum)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)