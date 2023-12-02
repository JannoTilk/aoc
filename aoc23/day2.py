import datetime
import re

start = datetime.datetime.now()
_input = []
_sum = 0
_sum2 = 0
red_total = 12
green_total = 13
blue_total = 14

with open("day2.txt", 'r') as f:
# with open("day2_test.txt", 'r') as f:
    for line in f.readlines():
        _input.append(line.replace("\n", ""))

for i in range(0, len(_input)):
    blue = re.findall("\\d+ blue", _input[i])
    red = re.findall("\\d+ red", _input[i])
    green = re.findall("\\d+ green", _input[i])
    blue_max = max(int(item.split()[0]) for item in blue)
    red_max = max(int(item.split()[0]) for item in red)
    green_max = max(int(item.split()[0]) for item in green)
    if blue_max <= blue_total and red_max <= red_total and green_max <= green_total:
        _sum += i + 1
    _sum2 += (blue_max * red_max * green_max)

print("Part 1: ", _sum)
print("Part 2: ", _sum2)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)
