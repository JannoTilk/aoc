import datetime
import re

start = datetime.datetime.now()
input = []
sum = 0
sum2 = 0

with open("day1.txt", 'r') as f:
# with open("day1t.txt", 'r') as f:
# with open("day1_test2.txt", 'r') as f:
# with open("day1_test3.txt", 'r') as f:
    for line in f.readlines():
        input.append(int(line.replace("\n", "")))

print(input)
initial = input[0]
sum = 0

for i in input:
    if i > initial:
        sum += 1
    initial = i
    print(i)

# for i in range(0, len(input)):
#     strs = re.findall(r'\d', input[i])
#     s = strs[0] + strs[-1]
#     sum += int(s)



print("Part 1: ", sum)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)
