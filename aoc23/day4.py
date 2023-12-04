_input = []

with open("day4.txt", 'r') as f:
# with open("day4_test.txt", 'r') as f:
    for line in f.readlines():
        _input.append(line.replace("\n", ""))

def get_winning_numbers(card_index):
    card_winning_numbers = 0
    winning = _input[card_index].split(": ")[1].split(" | ")[0].replace("  ", " ").split(" ")
    actual = _input[card_index].split(": ")[1].split(" | ")[1].replace("  ", " ").split(" ")
    for j in range(0, len(winning)):
        if winning[j] in actual:
            card_winning_numbers += 1
    return card_winning_numbers

def part1():
    _sum = 0
    for card_index in range(0, len(_input)):
        card_winning_numbers = get_winning_numbers(card_index)
        if card_winning_numbers > 0:
            _sum += 2 ** (card_winning_numbers - 1)
    return _sum

def part2():
    _dict = {}
    for card_index in range(0, len(_input)):
        card_winning_numbers = get_winning_numbers(card_index)

        if card_index + 1 in _dict:
            _dict[card_index + 1] += 1
        else:
            _dict.setdefault(card_index + 1, 1)

        for k in range(0, card_winning_numbers):
            if card_index + 1 + k < len(_input) + 1:
                _dict.setdefault(card_index + 2 + k, 0)
                _dict[card_index + 2 + k] += 1 if card_index == 0 else _dict[card_index + 1]
    return sum(_dict.values())

print("Part 1: ", part1())
print("Part 2: ", part2())