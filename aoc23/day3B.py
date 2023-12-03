import re

_input = []
_sum = 0

with open("day3.txt", 'r') as f:
# with open("day3_test.txt", 'r') as f:
# with open("day3_test2.txt", 'r') as f:
    for line in f.readlines():
        _input.append(line.replace("\n", ""))

def is_gear(cell):
    return cell == "*"

def get_adjacent_gear_location(matrix, row_index, col_index, number_length):
    for delta_row in range(-1, 2):
        for delta_col in range(-1, 2):
            # Skip the current cell itself
            if delta_row == 0 and delta_col == 0:
                continue

            # Check adjacent cells for symbols
            for offset in range(number_length):
                adjacent_row = row_index + delta_row
                adjacent_col = col_index + delta_col + offset

                # Ensure we are within the matrix boundaries
                if 0 <= adjacent_row < len(matrix) and 0 <= adjacent_col < len(matrix[0]):
                    if is_gear(matrix[adjacent_row][adjacent_col]):
                        return adjacent_row, adjacent_col
    return None

gear_location_dict = {}

# Find all numbers using regex
for i, row in enumerate(_input):
    for match in re.finditer(r'\d+', row):
        number = int(match.group())
        start_index = match.start()
        gear_coordinates = get_adjacent_gear_location(_input, i, start_index, len(str(number)))

        if gear_coordinates:
            key = tuple(gear_coordinates)
            if key in gear_location_dict:
                gear_location_dict[key].append(number)
            else:
                gear_location_dict[key] = [number]

for key in gear_location_dict:
    if len(gear_location_dict[key]) == 2:
        _sum += gear_location_dict[key][0] * gear_location_dict[key][1]

print(_sum)
