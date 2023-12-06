import datetime
import sys

start = datetime.datetime.now()
_input = []

# with open("day5.txt", 'r') as f:
with open("day5_test.txt", 'r') as f:
    for line in f.readlines():
        stripped_line = line.strip()
        if stripped_line:
            _input.append(stripped_line)

seeds_string = _input[0].split("seeds: ")[1].split(" ")
initial_seeds = [int(x) for x in seeds_string]
source_map = []
for i in range(1, len(_input)):
    if "map" in _input[i]:
        current_list = []
        source_map.append(current_list)
        continue
    current_list.append([int(x) for x in _input[i].split(" ")])

start_end_pairs = []

def generate_seed_start_end_pairs():
    i = 0
    while i < len(initial_seeds):
        _start = initial_seeds[i]
        _range = initial_seeds[i + 1]
        _end = _start + _range
        start_end_pairs.append([_start, _end])
        i += 2
generate_seed_start_end_pairs()
def backwards_transformation(_source_map):
    transformed_map = []
    for entry in _source_map:
        dest_start, src_start, length = entry
        offset = src_start - dest_start
        src_end = src_start + length - offset
        src_start -= offset
        _transformed = [src_start, src_end, offset]
        transformed_map.append(_transformed)
    return transformed_map


def part2():
    global i
    for number_to_check in range(sys.maxsize):
        for i in range(len(source_map) - 1, -1, -1):
            transformed = backwards_transformation(source_map[i])

            for j in range(len(transformed)):
                if number_to_check >= transformed[j][0] and number_to_check <= transformed[j][1]:
                    number_to_check = number_to_check + transformed[j][2]
                    break
        print(number_to_check)
        for k in range(len(start_end_pairs)):
            if number_to_check >= start_end_pairs[k][0] and number_to_check <= start_end_pairs[k][1]:
                return number_to_check

print(part2())

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)