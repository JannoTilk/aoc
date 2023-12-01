import datetime
import re

start = datetime.datetime.now()
input = []
sum = 0
sum2 = 0

with open("day1.txt", 'r') as f:
# with open("day1_test.txt", 'r') as f:
# with open("day1_test2.txt", 'r') as f:
# with open("day1_test3.txt", 'r') as f:
    for line in f.readlines():
        input.append(line.replace("\n", ""))

for i in range(0, len(input)):
    strs = re.findall(r'\d', input[i])
    s = strs[0] + strs[-1]
    sum += int(s)

for i in range(0, len(input)):
    modified = re.sub(r'one', "o1e", input[i])
    modified = re.sub(r'two', "t2o", modified)
    modified = re.sub(r'three', "t3e", modified)
    modified = re.sub(r'four', "f4r", modified)
    modified = re.sub(r'five', "f5e", modified)
    modified = re.sub(r'six', "s6x", modified)
    modified = re.sub(r'seven', "s7n", modified)
    modified = re.sub(r'eight', "e8t", modified)
    modified = re.sub(r'nine', "n9e", modified)

    strs = re.findall(r'\d', modified)
    s = strs[0] + strs[-1]
    sum2 += int(s)

print("Part 1: ", sum)
print("Part 2: ", sum2)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)
