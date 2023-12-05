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

_dict = {}
seeds_string = _input[0].split("seeds: ")[1].split(" ")
initial_seeds = [int(x) for x in seeds_string]

current_map = ""
for i in range(1, len(_input)):
    if "map" in _input[i]:
        current_map = _input[i]
        _dict[current_map] = []
        continue
    current_list = _input[i].split(" ")
    _dict[current_map].append([int(x) for x in current_list])

current_map = ""
for i in range(1, len(_input)):
    if "map" in _input[i]:
        current_map = _input[i]
        _dict[current_map] = []
        continue
    current_list = _input[i].split(" ")
    _dict[current_map].append([int(x) for x in current_list])

source_map = []




def generate_source_map_list():
    for _map in _dict["seed-to-soil map:"]:
        _start = _map[1]
        _end = _start + _map[2]
        _needed = _map[0]
        source_map.append([_start, _end, _needed])


generate_source_map_list()
print(source_map)


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

print(start_end_pairs)


def go_through_one_map():
    for k in range(len(source_map)):
        for j in range(len(start_end_pairs)):
            _seed_start = start_end_pairs[j][0]
            _seed_end = start_end_pairs[j][1]
            _source_start = source_map[k][0]
            _source_end = source_map[k][1]
            value_to_add = source_map[k][2] - _source_start

            if _seed_start >= _source_start and _seed_end <= _source_end:
                start_end_pairs[j][0] += value_to_add
                start_end_pairs[j][1] += value_to_add
                # print(value_to_add)
                # print(source_map[k])
                # print(start_end_pairs[j])
            elif _seed_start <= _source_end <= _seed_end:
                list1 = [_seed_start, _source_end]
                list2 = [_source_end + 1, _seed_end]
                list1[0] += value_to_add
                list1[1] += value_to_add
                start_end_pairs[j] = list1
                start_end_pairs.append(list2)
            elif _seed_start <= _source_start <= _seed_end:
                print(_seed_start, _seed_end, _source_start, _source_end, value_to_add)

                list1 = [_seed_start, _source_start - 1]
                list2 = [_source_start, _seed_end]
                list2[0] += value_to_add
                list2[1] += value_to_add
                start_end_pairs[j] = list2
                start_end_pairs.append(list1)


go_through_one_map()


print(start_end_pairs)
