import datetime

start = datetime.datetime.now()

_input = []

with open("day9.txt", 'r') as f:
# with open("day9_test.txt", 'r') as f:
    for line in f.readlines():
        stripped_line = line.strip()
        if stripped_line:
            _input.append(stripped_line)

history = []

for line in _input:
    history_line = []
    history.append(line.split(" "))

def generate_next_number(history_test):
    histories = [history_test]

    while True:
        new_history = []
        for i in range(1, len(history_test)):
            new_history.append(history_test[i] - history_test[i - 1])
        histories.append(new_history)
        history_test = new_history
        last_element = 0
        if len(set(new_history)) == 1 and new_history[0] == 0:
            for i in range(len(histories) - 2, -1, -1):
                new_last_element = histories[i][0] - last_element
                last_element = new_last_element
            break
    return last_element
_sum = 0

for h in history:
    h = [int(x) for x in h]
    _sum += generate_next_number(h)

print(_sum)

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)


