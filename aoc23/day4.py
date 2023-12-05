import datetime

start = datetime.datetime.now()
_input = []

with open("day4.txt", 'r') as f:
# with open("day4_test.txt", 'r') as f:
    for line in f.readlines():
        _input.append(line.replace("\n", ""))

def get_winning_numbers(card_number):
    card_winning_numbers = 0
    card_index = card_number - 1
    winning = _input[card_index].split(": ")[1].split(" | ")[0].replace("  ", " ").split(" ")
    actual = _input[card_index].split(": ")[1].split(" | ")[1].replace("  ", " ").split(" ")
    for j in range(0, len(winning)):
        if winning[j] in actual:
            card_winning_numbers += 1
    return card_winning_numbers

def part1():
    _sum = 0
    for card_number in range(1, len(_input) + 1):
        card_winning_numbers = get_winning_numbers(card_number)
        if card_winning_numbers > 0:
            _sum += 2 ** (card_winning_numbers - 1)
    return _sum

def part2():
    _dict = {}
    for card_number in range(1, len(_input) + 1):
        card_winning_numbers = get_winning_numbers(card_number)

        _dict.setdefault(card_number, 0)
        _dict[card_number] += 1

        for k in range(0, card_winning_numbers):
            if card_number + k < len(_input):
                _dict.setdefault(card_number + 1 + k, 0)
                _dict[card_number + 1 + k] += _dict[card_number]
    return sum(_dict.values())

print("Part 1: ", part1())
print("Part 2: ", part2())

end = datetime.datetime.now()
print("\ntime: ")
print(end - start)