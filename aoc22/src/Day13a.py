import datetime
from collections import deque

a = datetime.datetime.now()
input = []
score = 0
# with open("day13.txt", 'r') as f:
with open("day13_test.txt", 'r') as f:
    for line in f.readlines():
        input.append(line.replace("\n", ""))

def create_stack(packet):
    stack = []
    for i in range(0, len(packet)):
        if (packet[i] != ','):
            stack.append(packet[i])
    return stack

counter = 0
sum = 0

for i in range(0, len(input) - 1, 2):
    print(input[i])
    print(input[i+1])
    left_stack = create_stack(input[i][1:len(input[i])-1])
    right_stack = create_stack(input[i + 1][1:len(input[i])-1])
    while left_stack and right_stack:
        if not right_stack:
            continue


        if right_stack:
            right = right_stack.pop(0)
        else:
            break

        if left_stack:
            left = left_stack.pop(0)
        else:
            sum += i + 1
            continue
        print(left)
        print(right)

        while not left.isdigit() and left_stack:
            left = left_stack.pop(0)
            if not left_stack:
                break

        while not right.isdigit() and right_stack:
            right = right_stack.pop(0)
            if not right_stack:
                print(left_stack)
                print("left stack is bigger than ")
                print(right_stack)

                sum += i + 1
                break


        # if left == '[':
        #     left = left_stack.pop(0) if left_stack else None
        if right.isdigit() and left.isdigit() and right > left:
            print(right + " > " + left)
            sum += i + 1
            break




# test = []
# test.append(3)
# if (test):
#     print("True")
# test.pop(0)
# if (not test):
#     print("True")


print(sum)


# print(input)
# maze = []