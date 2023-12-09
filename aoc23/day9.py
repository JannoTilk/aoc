from collections import defaultdict
import datetime

start = datetime.datetime.now()

_input = []

# with open("day9.txt", 'r') as f:
with open("day9_test.txt", 'r') as f:
    for line in f.readlines():
        stripped_line = line.strip()
        if stripped_line:
            _input.append(stripped_line)

history = []
# print(_input)


for line in _input:
    history_line = []
    history.append(line.split(" "))

history_test = [int(x) for x in history[2]]

print(history_test)

histories = [history_test]

while True:
    new_history = []
    for i in range(1, len(history_test)):
        new_history.append(history_test[i] - history_test[i - 1])
    histories.append(new_history)
    history_test = new_history
    last_element = 0
    if len(set(new_history)) == 1 and new_history[0] == 0:
        print(histories)
        for i in range(len(histories) - 1, 0, -1):
            print("last element: ", last_element)
            print("histories[i][-2] ", histories[i][-2])
            new_last_element = last_element + histories[i][-1]
            last_element = new_last_element
            print(last_element)
            print(histories[i])
        print(last_element)
        print(histories[0][-1])
        print(histories[0][-1] + last_element)
        break

print(histories)

print(history)




