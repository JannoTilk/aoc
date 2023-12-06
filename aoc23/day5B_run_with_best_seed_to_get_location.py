import datetime
import sys

start = datetime.datetime.now()
_input = []

with open("day5.txt", 'r') as f:
# with open("day5_test.txt", 'r') as f:
    for line in f.readlines():
        stripped_line = line.strip()
        if stripped_line:
            _input.append(stripped_line)

_dict = {}
seeds_string = _input[0].split("seeds: ")[1].split(" ")
# last output from day5B (wrong)
# initial_seeds = [2561416828]
# one before last output from day5B (correct)
initial_seeds = [1949783811]

current_map = ""
for i in range(1, len(_input)):
    if "map" in _input[i]:
        current_map = _input[i]
        _dict[current_map] = []
        continue
    current_list = _input[i].split(" ")
    _dict[current_map].append([int(x) for x in current_list])

def map_parser(maps, seed):
    next_value = seed
    for i in range(0, len(maps)):
        if seed >= maps[i][1] and seed <= maps[i][1] + maps[i][2]:
            next_value = seed - maps[i][1] + maps[i][0]
            break
    return next_value

def go_through_gardening_mappings(_dict, seed):
    converted_value = seed
    for key in _dict:
        converted_value = map_parser(_dict[key], converted_value)
    return converted_value

def part1(seeds):
    location_min = sys.maxsize
    for i in range(len(seeds)):
        location = go_through_gardening_mappings(_dict, seeds[i])
        if (location < location_min):
            location_min = location
    return location_min

print(part1(initial_seeds))

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)
