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
start_end_pairs = []
source_map = []

current_map = ""
for i in range(1, len(_input)):
    if "map" in _input[i]:
        current_map = _input[i]
        _dict[current_map] = []
        continue
    current_list = _input[i].split(" ")
    _dict[current_map].append([int(x) for x in current_list])

print(_dict)

def generate_seed_start_end_pairs():
    i = 0
    while i < len(initial_seeds):
        _start = initial_seeds[i]
        _range = initial_seeds[i + 1]
        _end = _start + _range
        start_end_pairs.append([_start, _end])
        i += 2


generate_seed_start_end_pairs()

for key in _dict:
    source_map.append(_dict[key])
# print(start_end_pairs)
# print(source_map)

def transform_map(source_map):
    transformed_map = []
    for entry in source_map:
        dest_start, src_start, length = entry
        src_end = src_start + length - 1
        offset = dest_start - src_start   # Calculate the offset
        transformed_map.append([src_start, src_end, offset])
    return transformed_map


# start_end_pairs = [70, 115]
# source_map = [75, 112, 2]
# source_map = [[52, 50, 48], [52, 50, 48]]
# source_map = [[50, 98, 2], [52, 50, 48]]
# source_map = [[[50, 98, 2], [52, 50, 48]], [[0, 15, 37], [37, 52, 2], [39, 0, 15]]]
source_map = [[98, 99, -48], [50, 97, 2]]
start_end_pairs = [[79, 92]]

def go_through_one_map(transformed_source_map, _start_end_pairs):
    _seed_start = _start_end_pairs[0]
    _seed_end = _start_end_pairs[1]
    _source_start = transformed_source_map[0]
    _source_end = transformed_source_map[1]
    value_to_add = transformed_source_map[2]
    if _seed_start > _source_end or _seed_end < _source_start:
        return _start_end_pairs
    else:
        # seed [3,5] source [1, 10]
        if _seed_start >= _source_start and _seed_end <= _source_end:
            _start_end_pairs[0] += value_to_add
            _start_end_pairs[1] += value_to_add
            return [_start_end_pairs]
        # seed [3, 6] source [4, 10]
        elif _seed_start < _source_start and _seed_end < _source_end:
            list1 = [_seed_start, _source_start - 1]
            list2 = [_source_start, _seed_end]
            list2[0] += value_to_add
            list2[1] += value_to_add
            return [list1, list2]
        # seed [6, 12] source [4, 10]
        elif _seed_start >= _source_start and _seed_end > _source_end:
            list1 = [_seed_start, _source_end]
            list2 = [_source_end + 1, _seed_end]
            list1[0] += value_to_add
            list1[1] += value_to_add
            return [list1, list2]
        else:
            list1 = [_seed_start, _source_start - 1]
            list2 = [_source_start, _source_end]
            list2[0] += value_to_add
            list2[1] += value_to_add
            list3 = [_source_end + 1, _seed_end]
            return [list1, list2, list3]


# for i in range(len(source_map)):
#     transformed_maps = transform_map(source_map[i])
#     if i != 0:
#         new_pairs = []
#     start_end_pairs = temp
#     for _map in transformed_maps:
#         print(_map)
#         for pair in start_end_pairs:
#             new_pairs.append(go_through_one_map(_map, start_end_pairs))


def go_through_gardening_mappings(transformed_maps, _pairs):
    new_pairs = []
    for _map in transformed_maps:
        for pair in _pairs:
            new_pairs.append(go_through_one_map(_map, pair))
    return new_pairs

print(go_through_gardening_mappings(source_map, start_end_pairs))



# new_map = transform_map(_dict["seed-to-soil map:"])

# for j in range(len(new_map)):
#     print(new_map)
#     new_list = []
#     new_list = go_through_one_map(new_map[j], start_end_pairs)
# print(new_list)
# print(start_end_pairs)
#
# for key in _dict:
#     print(_dict[key])
#


# initial_seeds = [[79, 92]]
# for key in _dict:
#     new_map = transform_map(_dict[key])
#     for j in range(len(new_map)):
#         new_seeds = []
#         for k in range(len(initial_seeds)):
#             map_results = go_through_one_map(new_map[j], initial_seeds[k])
#             for l in range(len(map_results)):
#                 new_seeds.append(map_results[l])
#     initial_seeds = new_seeds
# print(initial_seeds)
        # new_list = go_through_one_map(new_map[j], start_end_pairs)
# print(new_list)
    # print(go_through_one_map(transform_map(_map), start_end_pairs[i]))