import re

_input = []
_sum = 0

with open("day4.txt", 'r') as f:
# with open("day4_test.txt", 'r') as f:
# with open("day4_test2.txt", 'r') as f:
    for line in f.readlines():
        _input.append(line.replace("\n", ""))

def get_winning_cards():
    global card_winning_numbers
    card_winning_numbers = 0
    winning = _input[i].split(": ")[1].split(" | ")[0].replace("  ", " ").split(" ")
    actual = _input[i].split(": ")[1].split(" | ")[1].replace("  ", " ").split(" ")
    for j in range(0, len(winning)):
        if winning[j] in actual:
            card_winning_numbers += 1

for i in range(0, len(_input)):
    get_winning_cards()
    if card_winning_numbers > 0:
        _sum += 2 ** (card_winning_numbers - 1)

_dict = {}

for i in range(0, len(_input)):
    get_winning_cards()

    if i + 1 in _dict:
        _dict[i + 1] += 1
    else:
        _dict.setdefault(i + 1, 1)

    for k in range(0, card_winning_numbers):
        if i + 1 + k < len(_input) + 1:
            _dict.setdefault(i + 2 + k, 0)
            _dict[i + 2 + k] += 1 if i == 0 else _dict[i+1]


print("Part 1: ", _sum)
print("Part 2: ", sum(_dict.values()))