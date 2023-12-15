import datetime
from collections import defaultdict

start = datetime.datetime.now()

_input = ""

# Read the file and convert each line into a list of characters
with open("day15.txt", 'r') as f:
# with open("day15_test.txt", 'r') as f:
# with open("day15_test2.txt", 'r') as f:
    _input = f.read()

_dict = defaultdict(int)


_sum = 0
_input = _input.split(",")
print(_input)

for s in _input:
    sub_sum = 0
    is_dash = "-" in s
    if is_dash:
        for c in s[:-1]:
            _ascii = ord(c)
            sub_sum += _ascii
            sub_sum = sub_sum * 17
            sub_sum = sub_sum % 256
        if sub_sum in _dict:
            if s[:-1] in _dict[sub_sum]:
                del _dict[sub_sum][s[:-1]]
    else:
        s = s.split("=")
        for c in s[0]:
            _ascii = ord(c)
            sub_sum += _ascii
            sub_sum = sub_sum * 17
            sub_sum = sub_sum % 256
        if sub_sum in _dict:
            if s[0] in _dict[sub_sum]:
                _dict[sub_sum][s[0]] = int(s[1])
            else:
                _dict[sub_sum].update({s[0]: int(s[1])})
        else:
            _dict[sub_sum] = {s[0]: int(s[1])}

    _sum += sub_sum

print(_dict.values())
print(_dict.keys())
print(_sum)

_total_sum = 0

for box in _dict:
    if len(_dict[box]) > 0:
        for slot in range(len(_dict[box])):
            _current_lens_sum = (box + 1) * (slot + 1) * list(_dict[box].values())[slot]
            print(_current_lens_sum)
            _total_sum += _current_lens_sum

print(_total_sum)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)
