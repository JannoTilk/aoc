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
initial = input[0] + input[1] + input[2]
sum = 0

for i in range(3, len(input)):
    current = initial + input[i] - input[i - 3]
    if current > initial:
        sum += 1
    initial = current
    print(current)


print("Part 2: ", sum)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)
