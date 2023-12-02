import datetime
import re

start = datetime.datetime.now()
input = []
summed = 0
summed2 = 0
red_max = 12
green_max = 13
blue_max = 14

with open("day2.txt", 'r') as f:
# with open("day2_test.txt", 'r') as f:
    for line in f.readlines():
        input.append(line.replace("\n", ""))

for i in range(0, len(input)):
    blue = re.findall("\\d+ blue", input[i])
    red = re.findall("\\d+ red", input[i])
    green = re.findall("\\d+ green", input[i])
    blue_count = max(int(item.split()[0]) for item in blue)
    red_count = max(int(item.split()[0]) for item in red)
    green_count = max(int(item.split()[0]) for item in green)
    if blue_count <= blue_max and red_count <= red_max and green_count <= green_max:
        summed += i + 1
    summed2 += (blue_count * red_count * green_count)

print("Part 1: ", summed)
print("Part 2: ", summed2)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)